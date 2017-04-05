import java.util.UUID;

/**
 * Created by The Dark Avenger on 2/7/2017.
 */
public class Book {
  private
    String title;
    String author;
    long isbn;

  public
    Book (String title, String author) {
      this.title = title;
      this.author = author;
      UUID id = UUID.randomUUID();
      this.isbn = id.getMostSignificantBits();
    }
    long getIsbn() {
      return this.isbn;
    }
    String getTitle() {
      return this.title;
    }
    String getAuthor() { return this.author; }
}
