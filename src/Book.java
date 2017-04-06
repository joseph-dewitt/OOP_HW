import java.util.UUID;

/**
 * Created by The Dark Avenger on 2/7/2017.
 */
public class Book extends LibraryMaterial {
	private String author;

	public Book(String title, String author) {
		super(title);
		this.author = author;
	}

	String getAuthor() 	{ return this.author; }

	void print() {
		super.print();
		System.out.println("Author : " + author);
	}
}
