import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * Created by The Dark Avenger on 2/7/2017.
 */
public class BookCopy extends LibraryMaterialCopy implements Comparable<BookCopy> {
	public static final int BORROWING_WEEKS = 3;
	public static final BigDecimal FINE = new BigDecimal("0.10");

	private Book book;

	public BookCopy(Book book) {
		this.book = book;
	}

	String getBook() { return book.getTitle(); }

	void print() {
		super.print();
		book.print();
	}

	@Override
	public LibraryMaterial getLibraryMaterial () {
		return book;
	}

	@Override
	protected BigDecimal getFinePerDay() {
		return FINE;
	}

	@Override
	protected int getBorrowingWeeks() {
		return BORROWING_WEEKS;
	}

	void setDueDate(LocalDate dueDate) 	{ this.dueDate = dueDate; }



	@Override
	public int compareTo(BookCopy b) {
		return dueDate.compareTo(b.getDueDate());
	}
}
