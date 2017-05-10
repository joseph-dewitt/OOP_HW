import java.util.UUID;

/**
 * Created by Joseph DeWitt on 4/5/2017.
 */
public class LibraryMaterial {
	protected String title;
	protected long ISBN;

	public LibraryMaterial(String title) {
		this.title = title;
		UUID id = UUID.randomUUID();
		this.ISBN = id.getMostSignificantBits();
	}

	String getTitle() 	{ return title; }

	long getIsbn() 		{ return ISBN; }

	void print() {
		System.out.println("Title: " + title);
		System.out.println("ISBN: " + ISBN);
	}
}
