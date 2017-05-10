import java.util.HashMap

/**
 * Created by Joseph DeWitt on 4/5/2017.
 */
public class Catalog {
    HashMap<LibraryMaterial, LibraryMaterialCopy[]> map;

    public Catalog () {
      map = new HashMap<LibraryMaterial, LibraryMaterialCopy[]>();
    }

    public put(LibraryMaterial title, LibraryMaterialCopy[] copies) {
      map.put(title, copies);
    }

    public get(LibraryMaterial title) {
      return map.get(title);
    }

}
