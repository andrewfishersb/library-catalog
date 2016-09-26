import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void book_instantiatesCorrectly_true() {
    Book testBook = new Book("Harry Potter","JK Rowling", 1);
    assertEquals(true, testBook instanceof Book);
  }

  @Test
  public void Book_instantiatesWithName_String() {
    Book testBook = new Book("Harry Potter","JK Rowling", 1);
    assertEquals("Harry Potter", testBook.getTitle());
  }
  @Test
  public void Book_instantiatesWithPatronId_int() {
    Book testBook = new Book("Harry Potter","JK Rowling", 1);
    assertEquals(1, testBook.getPatronId());
  }
  @Test
  public void equals_returnsTrueIfNameAndAuthorAndPatronIdAreSame_true() {
    Book testBook = new Book("Harry Potter","JK Rowling", 1);
    Book anotherBook = new Book("Harry Potter","JK Rowling", 1);
    assertTrue(testBook.equals(anotherBook));
  }
  @Test
  public void save_returnsTrueIfTitlesAretheSame() {
    Book testBook = new Book("Harry Potter","JK Rowling", 1);
    testBook.save();
    assertTrue(Book.all().get(0).equals(testBook));
  }

  @Test
  public void all_returnsAllInstancesOfBook_true() {
    Book firstBook = new Book("Harry Potter","JK Rowling", 1);
    firstBook.save();
    Book secondBook = new Book("Lord of The Rings", "JR Tolkien", 1);
    secondBook.save();
    assertEquals(true, Book.all().get(0).equals(firstBook));
    assertEquals(true, Book.all().get(1).equals(secondBook));
  }

  @Test
  public void find_returnsBookWithSameId_secondBook() {
    Book firstBook = new Book("Harry Potter","JK Rowling", 1);
    firstBook.save();
    Book secondBook = new Book("Lord of The Rings", "JR Tolkien", 1);
    secondBook.save();
    assertEquals(Book.find(secondBook.getId()), secondBook);
  }

  @Test
  public void save_savesPatronIdIntoDB_true() {
    Patron testPatron = new Patron("Henry");
    testPatron.save();
    Book testBook = new Book("Harry Potter","JK Rowling", testPatron.getId());
    testBook.save();
    Book savedBook = Book.find(testBook.getId());
    assertEquals(savedBook.getPatronId(), testPatron.getId());
  }


}
