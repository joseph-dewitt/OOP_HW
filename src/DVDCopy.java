import java.math.BigDecimal;

/**
 * Created by Joseph DeWitt on 4/5/2017.
 */
public class DVDCopy extends LibraryMaterialCopy {
	public static final int BORROWING_WEEKS = 2;
	public static final BigDecimal FINE = new BigDecimal("1.00");
	private DVD dvd;

	public DVDCopy(DVD dvd) {
		this.dvd = dvd;
	}

	void print() {
		super.print();
		dvd.print();
	}

	@Override
	public LibraryMaterial getLibraryMaterial() {
		return dvd;
	}

	@Override
	protected String getTitle () { return dvd.getTitle(); }


	@Override
	protected BigDecimal getFinePerDay() {
		return FINE;
	}

	@Override
	protected int getBorrowingWeeks() {
		return BORROWING_WEEKS;
	}

}
