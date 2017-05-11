import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/**
 * Created by Joseph DeWitt on 2/7/2017.
 */

public class LibraryCard {
    public static final int RENEWAL_WEEKS = 2;
    private long UID;
    private String name;
    private ArrayList<LibraryMaterialCopy> borrowed = new ArrayList<LibraryMaterialCopy>();
    private PrintWriter writer;

    public LibraryCard (PrintWriter writer, String name, LibraryCardManager manager) {
		UUID id = UUID.randomUUID();
		UID = (long)(Math.abs(id.getMostSignificantBits()) / Math.pow(10,10));
		this.writer = writer;
		this.name = name;
		System.out.println("New card with name " + name + " and UID " + UID);
	}

    long        getUID() {
        return UID;
    }
    String      getName() { return name; }
    void        setName(String name) { this.name = name; }

    void printBorrowed() {
        Collections.sort(borrowed);
        for (int i = 0; i < borrowed.size(); i++) {
            writer.println(borrowed.get(i).getTitle() + " is due " + borrowed.get(i).getDueDate());
			System.out.println(borrowed.get(i).getTitle() + " is due " + borrowed.get(i).getDueDate());
        }
    }

    void borrowBook(LibraryMaterialCopy b) {

        borrowBook(b, LocalDate.now());
    }

    void borrowBook(LibraryMaterialCopy b, LocalDate date){
        if (b.isOut()) {
            writer.println("That book ain't here.");
            return;
        }
        borrowed.add(b);
        b.isBorrowed(this, date);
    }

    BigDecimal returnBook(LibraryMaterialCopy b) {

        if(borrowed.remove(b)) {
            return b.isReturned();
        } else {
            writer.println("Where did you get this? It certainly isn't yours.\n");
            return BigDecimal.valueOf(0.00);
        }
    }

    BigDecimal returnAll() {
		BigDecimal sum = BigDecimal.valueOf(0);
		for(LibraryMaterialCopy b: borrowed){
			sum = sum.add(returnBook(b));
		}
		return sum;
	}

    void renewMaterial(LibraryMaterialCopy b) {
        if(b instanceof BookCopy)
			b.setDueDate(LocalDate.now().plusWeeks(RENEWAL_WEEKS));
		if(b instanceof DVDCopy)
			System.out.println("You may not renew DVDs");
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

    public LibraryMaterialCopy isCheckedOut(String s) {
		for(LibraryMaterialCopy b: borrowed) {
			if(s.equals(b.getTitle())) {
				return b;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		return (int)(Math.abs(getUID() / Math.pow(10,14)));
	}


}
