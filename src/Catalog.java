import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joseph DeWitt on 4/5/2017.
 */
public class Catalog {
    HashMap<LibraryMaterial, ArrayList<LibraryMaterialCopy>> map;

    public Catalog () {
      map = new HashMap<LibraryMaterial, ArrayList<LibraryMaterialCopy>>();
    }

    public void put(LibraryMaterial title, ArrayList<LibraryMaterialCopy> copies) {
      map.put(title, copies);
    }

    public LibraryMaterialCopy get(LibraryMaterial title) {

      title.print();
      System.out.println(map.containsKey(title));
      System.out.println(map.get(title).getClass());
      return map.get(title).get(0);
    }

}
