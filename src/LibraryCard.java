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
    private ArrayList<BookCopy> borrowed = new ArrayList<BookCopy>();
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
    String      getIndexTitle(int i) { return borrowed.get(i).getBook(); }

    void printBorrowed() {
        Collections.sort(borrowed);
        for (int i = 0; i < borrowed.size(); i++) {
            writer.println(borrowed.get(i).getBook() + " is due " + borrowed.get(i).getDueDate());
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
        BookCopy b = borrowed.get(i);

        if(borrowed.remove(b)) {
            return b.isReturned();
        } else {
            writer.println("Where did you get this? It certainly isn't yours.\n");
            return BigDecimal.valueOf(0.00);
        }
    }

    void renewBook(BookCopy b) {
        b.setDueDate(LocalDate.now().plusWeeks(RENEWAL_WEEKS));
    }

    ArrayList<BookCopy> dueBy(LocalDate date) {
        ArrayList<BookCopy> dueBooks = new ArrayList<BookCopy>();

        for(BookCopy b : borrowed) {
            if(b.getDueDate().isBefore(date)) {
                dueBooks.add(b);
            }
        }
        return dueBooks;
    }


}
