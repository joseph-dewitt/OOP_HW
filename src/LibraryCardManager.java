import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joseph DeWitt on 5/10/2017.
 */
public class LibraryCardManager {
	HashMap<Long, LibraryCard> map;

	public LibraryCardManager() { map = new HashMap<Long, LibraryCard>(); }

	public void put(LibraryCard c) {
		map.put(c.getUID(),c);
	}


}
