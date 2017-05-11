import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Joseph DeWitt on 2/7/2017.
 */
public class main {

	public static void main(String[] args) throws IOException {
		Catalog cat = new Catalog();
		File file = new File("output.txt");
		FileWriter output = new FileWriter(file);
		PrintWriter writer = new PrintWriter(output);
		Scanner keyboard = new Scanner(System.in);
		int input;

		ArrayList<BookCopy> library = new ArrayList<BookCopy>();
		ArrayList<Book> titles = new ArrayList<Book>();

		String title;
		String author;

		LibraryCardManager manager = new LibraryCardManager();
		LibraryCard card = new LibraryCard(writer, "Joe", manager);
		manager.put(card);

		BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
		int bookNum = Integer.parseInt(reader.readLine());

		for (int i = 0; i < bookNum; i++) {
			ArrayList<LibraryMaterialCopy> thing = new ArrayList<LibraryMaterialCopy>();

			title = reader.readLine();
			author = reader.readLine();
			titles.add(new Book(title, author));
			thing.add(new BookCopy(titles.get(i)));
			thing.add(new BookCopy(titles.get(i)));

			library.add(new BookCopy(titles.get(i)));
			library.add(new BookCopy(titles.get(i)));

			cat.put(titles.get(i), thing);
		}

		while(true) {

			System.out.println("1. Add new title to the catalog.");
			System.out.println("2. Create new library card.");
			System.out.println("3. View all titles in the catalog.");
			System.out.println("4. Check out a copy from the catalog. (Must have library card ready.)");
			System.out.println("5. View all copies card has checked out.");
			System.out.println("6. Return copy.");
			System.out.println("7. Return all copies.");
			System.out.println("8. Renew copy.");
			System.out.println("9. Quit.");

			input = keyboard.nextInt();
			keyboard.nextLine();
			if(input == 9)
				break;

			switch (input) {
				case 1: //Add new title to the catalog
					System.out.println("Please enter title and author:");
					System.out.println("Title:");
					String tempTitle = keyboard.nextLine();
					System.out.println("Author:");
					String tempAuthor = keyboard.nextLine();
					titles.add(new Book(tempTitle, tempAuthor));
					break;

				case 2: //Create new library card
					System.out.println("Please enter card holder's name.");
					System.out.println("Name:");
					String tempName = keyboard.nextLine();
					card = new LibraryCard(writer, tempName, manager);
					System.out.println("UID for this card is: " + card.getUID());
					System.out.println("Please save this number.");
					break;

				case 3: //View all titles in the catalog
					for (LibraryMaterial x : cat.getKeys()) {
						x.print();
					}
					break;

				case 4: //Check out a copy from the catalog. (Must have library card ready.)
					System.out.println("Please enter UID: ");
					long tempID = keyboard.nextLong();
					keyboard.nextLine();
					System.out.println("Please enter title: ");
					tempTitle = keyboard.nextLine();
					LibraryMaterialCopy temp = cat.get(tempTitle);
					manager.get(tempID).borrowBook(temp);
					break;

				case 5: //View all copies card has checked out
					System.out.println("Please enter UID: ");
					tempID = keyboard.nextLong();
					manager.get(tempID).printBorrowed();
					break;

				case 6: //Return copy
					System.out.println("Please enter UID: ");
					tempID = keyboard.nextLong();
					System.out.println("Please enter title: ");
					tempTitle = keyboard.nextLine();
					System.out.println("The late fee is:");
					System.out.println("$" + manager.get(tempID).returnBook(cat.get(tempTitle)));
					break;

				case 7: //Return all copies
					System.out.println("Please enter UID: ");
					tempID = keyboard.nextLong();
					System.out.println("The late fee is:");
					System.out.println("$" + manager.get(tempID).returnAll());
					break;

				case 8: //Renew copy
					System.out.println("Please enter UID: ");
					tempID = keyboard.nextLong();
					System.out.println("Please enter title: ");
					tempTitle = keyboard.nextLine();
					manager.get(tempID).renewMaterial(cat.get(tempTitle));
					break;

			}
		}



		for (int i = 0; i < bookNum; i += 2) {
			card.borrowBook(library.get(i), LocalDate.now().minusWeeks(7-i));
		}

		card.printBorrowed();

		if (library.get(0).isOut() && library.get(1).isOut())
			writer.println("There are currently no copies of angry grapes available.");
		else if (library.get(0).isOut() || library.get(1).isOut()) {
			writer.println("There is at least one copy left of Wrath's Grapes.");
		}

		ArrayList<LibraryMaterialCopy> dueTomorrow = new ArrayList<LibraryMaterialCopy>();
		dueTomorrow = card.dueBy(LocalDate.now().plusDays(1));
		for (LibraryMaterialCopy b : dueTomorrow) {
			writer.println(b.getTitle() + " is due tomorrow.");
		}

		writer.println((int)(Math.abs(card.getUID()) / Math.pow(10,16)));
		card.isCheckedOut("Sandy Man").print();

		cat.get(titles.get(0)).print();

		output.flush();
		output.close();

		return;
	}
}
