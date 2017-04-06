import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by The Dark Avenger on 4/5/2017.
 */
public abstract class LibraryMaterialCopy  implements Comparable<LibraryMaterialCopy> {


	protected LibraryCard borrower;
	protected boolean isOut;
	protected LocalDate dueDate;
	public abstract LibraryMaterial getLibraryMaterial();
	protected abstract BigDecimal getFinePerDay();
	protected abstract int getBorrowingWeeks();
	protected abstract String getTitle();

	LibraryMaterialCopy() {
		isOut = false;
		borrower = null;
	}

	boolean isOut() { return isOut;	}
	LocalDate getDueDate() { return dueDate; }

	void print() {
		System.out.println("Borrower ID " + borrower.getUID());
		System.out.println("Borrow name " + borrower.getName());
		System.out.println(" Due by " + getDueDate());
	}

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

	void setDueDate(LocalDate dueDate) 	{ this.dueDate = dueDate; }

	@Override
	public int compareTo(LibraryMaterialCopy b) {
		return dueDate.compareTo(b.getDueDate());
	}
}
