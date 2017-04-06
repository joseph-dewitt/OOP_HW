import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/**
 * Created by The Dark Avenger on 2/7/2017.
 */

public class LibraryCard {
    public static final int RENEWAL_WEEKS = 2;
    private long UID;
    private String name;
    private ArrayList<LibraryMaterialCopy> borrowed = new ArrayList<LibraryMaterialCopy>();
    private PrintWriter writer;

    public LibraryCard (PrintWriter writer, String name) {
      UUID id = UUID.randomUUID();
      UID = id.getMostSignificantBits();
      this.writer = writer;
      this.name = name;
    }

    long        getUID() {
        return UID;
    }
    String      getName() { return name; }
    void        setName(String name) { this.name = name; }
    String      getIndexTitle(int i) { return borrowed.get(i).getLibraryMaterial().toString(); }

    void printBorrowed() {
        Collections.sort(borrowed);
        for (int i = 0; i < borrowed.size(); i++) {
            writer.println(borrowed.get(i).getTitle() + " is due " + borrowed.get(i).getDueDate());
        }
    }

    void borrowBook(BookCopy b) {

        borrowBook(b, LocalDate.now());
    }

    void borrowBook(BookCopy b, LocalDate date){
        if (b.isOut()) {
            writer.println("That book ain't here.");
            return;
        }
        borrowed.add(b);
        b.isBorrowed(this, date);
    }

    BigDecimal returnBook(int i) {
        LibraryMaterialCopy b = borrowed.get(i);

        if(borrowed.remove(b)) {
            return b.isReturned();
        } else {
            writer.println("Where did you get this? It certainly isn't yours.\n");
            return BigDecimal.valueOf(0.00);
        }
    }

    void renewBook(LibraryMaterialCopy b) {
        b.setDueDate(LocalDate.now().plusWeeks(RENEWAL_WEEKS));
    }

    ArrayList<LibraryMaterialCopy> dueBy(LocalDate date) {
        ArrayList<LibraryMaterialCopy> dueMaterial = new ArrayList<LibraryMaterialCopy>();

        for(LibraryMaterialCopy b : borrowed) {
            if(b.getDueDate().isBefore(date)) {
                dueMaterial.add(b);
            }
        }
        return dueMaterial;
    }


}
