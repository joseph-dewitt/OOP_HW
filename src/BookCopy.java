import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * Created by The Dark Avenger on 2/7/2017.
 */
public class BookCopy implements Comparable<BookCopy> {
  private
    Book book;
    LibraryCard borrower;
    boolean isOut;
    LocalDate dueDate;
  public
    BookCopy(Book book) {
      this.book = book;
      borrower = null;
      isOut = false;
    }

    boolean isOut() { return isOut; }
    String getBook() {
      return book.getTitle();
    }
    LocalDate getDueDate() { return dueDate; }
    void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    long getBorrower() {
      if ( isOut() ) {
        return borrower.getUID();
      }
      else {
        return 0;
      }
    }

    BigDecimal isReturned(LibraryCard c) {
      borrower = null;
      isOut = false;
      BigDecimal multiplicand = new BigDecimal("0.10");

      if (LocalDate.now().isBefore(dueDate) || LocalDate.now().equals(dueDate))
        return BigDecimal.valueOf(0.00);
      long x = ChronoUnit.DAYS.between(dueDate, LocalDate.now());

      return multiplicand.multiply(BigDecimal.valueOf(x));
    }

    void isBorrowed(LibraryCard c) {
      borrower = c;
      isOut = true;
      if(c.testing) {
        Random random = new Random();

        long time = -15778476000L + System.currentTimeMillis() + (Math.abs(random.nextLong()) % (1L*365*24*60*60*1000));
        dueDate = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDate();
      }
      else {
        dueDate = LocalDate.now().plusWeeks(3);
      }
    }

    @Override
    public int compareTo(BookCopy b) {
      return dueDate.compareTo(b.getDueDate());
    }
}
