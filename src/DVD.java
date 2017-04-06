/**
 * Created by The Dark Avenger on 4/5/2017.
 */
public class DVD extends LibraryMaterial {
	private String actor;

	public DVD(String title, String actor) {
		super(title);
		this.actor = actor;
	}

	String getActor() { return actor; }

	void print() {
		super.print();
		System.out.println("Lead actor: " + actor);
	}
}
