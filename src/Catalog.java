import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joseph DeWitt on 4/5/2017.
 */
public class Catalog {
    HashMap<LibraryMaterial, ArrayList<BookCopy>> map;

    public Catalog () {
      map = new HashMap<LibraryMaterial, ArrayList<BookCopy>>();
    }

    public void put(LibraryMaterial title, ArrayList<BookCopy> copies) {
      map.put(title, copies);
    }

    public BookCopy get(LibraryMaterial title) {
      return map.get(title).get(0);
    }

}
