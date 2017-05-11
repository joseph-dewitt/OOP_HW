import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joseph DeWitt on 4/5/2017.
 */
public class Catalog {
    HashMap<LibraryMaterial, ArrayList<LibraryMaterialCopy>> map;
	private ArrayList<LibraryMaterial> keys = new ArrayList<LibraryMaterial>();

    public Catalog () {
      map = new HashMap<LibraryMaterial, ArrayList<LibraryMaterialCopy>>();
    }

    public ArrayList<LibraryMaterial> getKeys() { return keys; }

    public void put(LibraryMaterial title, ArrayList<LibraryMaterialCopy> copies) {
      	map.put(title, copies);
		keys.add(title);
    }

    public LibraryMaterialCopy get(LibraryMaterial title) {
		title.print();
		System.out.println(map.containsKey(title));
		System.out.println(map.get(title).get(0).getClass());
		return map.get(title).get(0);
    }

	public LibraryMaterialCopy get(String s) {
		return get(findTitle(s));
	}

    public LibraryMaterial findTitle(String s) {
        for (LibraryMaterial key: map.keySet()) {
            if(s.equals(key.getTitle()))
                return key;
        }
        return null;
    }


	public ArrayList<LibraryMaterialCopy> findAvailable(LibraryMaterial s) {
		ArrayList<LibraryMaterialCopy> temp = new ArrayList<LibraryMaterialCopy>();
		for(LibraryMaterialCopy t: map.get(s)) {
			temp.add(t);
		}
		return temp;
	}

}
