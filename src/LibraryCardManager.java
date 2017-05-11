import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joseph DeWitt on 5/10/2017.
 */
public class LibraryCardManager {
	HashMap<Long, LibraryCard> map;
	ArrayList<LibraryCard> cards = new ArrayList<LibraryCard>();

	public LibraryCardManager() {
		map = new HashMap<Long, LibraryCard>();
	}

	public void put(LibraryCard c) {
		map.put(c.getUID(),c);
		newCard(c);
	}

	public long newCard(LibraryCard c) {
		cards.add(c);
		long id = cards.get(cards.size()-1).getUID();
		map.put(id, cards.get(cards.size()-1));
		return id;
	}

	public LibraryCard get(long id) {
		System.out.println("looking in table for id");
		return map.get(id);
	}

	public LibraryCard getCard(int i) {
		return cards.get(i);
	}


}
