import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by The Dark Avenger on 2/7/2017.
 */
public class main {

	public static void main(String[] args) throws IOException {
		File file = new File("output.txt");
		FileWriter output = new FileWriter(file);
		PrintWriter writer = new PrintWriter(output);

		ArrayList<BookCopy> library = new ArrayList<BookCopy>();
		ArrayList<Book> titles = new ArrayList<Book>();

		String title;
		String author;

		LibraryCard card = new LibraryCard(writer, "Joe");

		BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
		int bookNum = Integer.parseInt(reader.readLine());

		for (int i = 0; i < bookNum; i++) {
			title = reader.readLine();
			author = reader.readLine();

			titles.add(new Book(title, author));
			library.add(new BookCopy(titles.get(i)));
			library.add(new BookCopy(titles.get(i)));
		}

		for (int i = 0; i < bookNum; i += 2) {
			card.borrowBook(library.get(i));
		}

		card.printBorrowed();

		if (library.get(0).isOut() && library.get(1).isOut())
			writer.println("There are currently no copies of angry grapes available.");
		else if (library.get(0).isOut() || library.get(1).isOut()) {
			writer.println("There is at least one copy left of Wrath's Grapes.");
		}

		ArrayList<BookCopy> dueTomorrow = new ArrayList<BookCopy>();
		dueTomorrow = card.dueBy(LocalDate.now().plusDays(1));
		for (BookCopy b : dueTomorrow) {
			writer.println(b.getBook() + " is due tomorrow.");
		}

		for (int i = 0; i < 3; i++) {
			writer.println(card.getIndexTitle(i) + " is being returned,");
			writer.println("the late fee is " + card.returnBook(i) + ".");
		}

		output.flush();
		output.close();

		return;
	}
}
