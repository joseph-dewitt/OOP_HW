import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by The Dark Avenger on 4/5/2017.
 */
public abstract class LibraryMaterialCopy {


	protected LibraryCard borrower;
	protected boolean isOut;
	protected LocalDate dueDate;
	public abstract LibraryMaterial getLibraryMaterial();
	protected abstract BigDecimal getFinePerDay();
	protected abstract int getBorrowingWeeks();

	LibraryMaterialCopy() {
		isOut = false;
		borrower = null;
	}

	boolean isOut() { return isOut;	}
	LocalDate getDueDate() { return dueDate; }


	long getBorrower() {
		if (isOut()) {
			return borrower.getUID();
		} else {
			return 0;
		}
	}

	BigDecimal isReturned() {
		borrower = null;
		isOut = false;

		if (LocalDate.now().isBefore(dueDate) || LocalDate.now().equals(dueDate))
			return BigDecimal.valueOf(0.00);
		long x = ChronoUnit.DAYS.between(dueDate, LocalDate.now());

		return getFinePerDay().multiply(BigDecimal.valueOf(x));
	}

	void isBorrowed(LibraryCard c) {
		isBorrowed(c, LocalDate.now());
	}

	void isBorrowed(LibraryCard c, LocalDate date) {
		borrower = c;
		isOut = true;
		dueDate = date.plusWeeks(getBorrowingWeeks());
	}
}
