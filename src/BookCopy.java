import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * Created by Joseph DeWitt on 2/7/2017.
 */
public class BookCopy extends LibraryMaterialCopy{
	public static final int BORROWING_WEEKS = 3;
	public static final BigDecimal FINE = new BigDecimal("0.10");

	private Book book;

	public BookCopy(Book book) {
		this.book = book;
	}

	void print() {
		super.print();
		book.print();
	}

	@Override
	public LibraryMaterial getLibraryMaterial () {
		return book;
	}

	@Override
	protected String getTitle () { return book.getTitle(); }

	@Override
	protected BigDecimal getFinePerDay() {
		return FINE;
	}

	@Override
	protected int getBorrowingWeeks() {
		return BORROWING_WEEKS;
	}






}
