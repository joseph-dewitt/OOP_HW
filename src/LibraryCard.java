import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by The Dark Avenger on 2/7/2017.
 */

public class LibraryCard {
  private
    long UID;
    String name;
    ArrayList<BookCopy> borrowed = new ArrayList<BookCopy>();
    PrintWriter writer;
    BigDecimal fine = new BigDecimal("0.00");
    boolean testing;
    //if testing is true, duedates will be random within +/- 6 months

  public
    LibraryCard (PrintWriter writer, String name, boolean testing) {
      UUID id = UUID.randomUUID();
      UID = id.getMostSignificantBits();
      this.writer = writer;
      this.name = name;
      BigDecimal increase = new BigDecimal("0.10");
      fine = fine.add(increase);
      this.testing = testing;
    }

    long        getUID() {
        return this.UID;
    }
    String      getName() { return name; }
    void        setName(String name) { this.name = name; }
    BigDecimal  getFine() { return fine; }
    void        setFine(BigDecimal fine) { this.fine = fine; }
    boolean     getTesting() { return testing; }
    void        setTesting(boolean testing) { this.testing = testing; }
    String      getIndexTitle(int i) { return borrowed.get(i).getBook(); }

    void printBorrowed() {
        Collections.sort(borrowed);
        for (int i = 0; i < borrowed.size(); i++) {
            writer.println(borrowed.get(i).getBook() + " is due " + borrowed.get(i).getDueDate());
        }
    }

    void borrowBook(BookCopy b) {
        if (b.isOut()) {
            writer.println("That book ain't here.");
            return;
        }
        borrowed.add(b);
        b.isBorrowed(this);
    }

    BigDecimal returnBook(int i) {
        BookCopy b = borrowed.get(i);

        if(borrowed.remove(b)) {
            return b.isReturned(this);
        } else {
            writer.println("Where did you get this? It certainly isn't yours.\n");
            return BigDecimal.valueOf(0.00);
        }
    }

    void renewBook(BookCopy b) {
        b.setDueDate(LocalDate.now().plusWeeks(2));
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
