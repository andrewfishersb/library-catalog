import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.text.DateFormat;
import java.sql.Timestamp;
import java.util.Date;

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

  @Test
  public void searchBooks_SearchesForPartOfAName(){
    Book firstBook = new Book("Harry Potter","JK Rowling", 1);
    firstBook.save();
    Book secondBook = new Book("Lord of The Rings", "JR Tolkien", 1);
    secondBook.save();
    Book thirdBook = new Book("Hunger Games", "Collins", 1);
    thirdBook.save();
    assertEquals("Lord of The Rings", Book.searchBooks("H").get(1).getTitle());
  }

  @Test
  public void searchAuthors_SearchesForPartOfAuthor(){
    Book firstBook = new Book("Harry Potter","JK Rowling", 1);
    firstBook.save();
    Book secondBook = new Book("Lord of The Rings", "JR Tolkien", 1);
    secondBook.save();
    Book thirdBook = new Book("Hunger Games", "Collins", 1);
    thirdBook.save();
    assertEquals("Collins", Book.searchAuthors("C").get(0).getAuthor());
  }

  @Test
  public void checkout_UpdatesTheCurrentRenter(){
    Patron firstPatron = new Patron("Tequila");
    firstPatron.save();
    Patron secondPatron = new Patron("Jorge");
    secondPatron.save();
    Book theBook = new Book("Harry Potter","JK",firstPatron.getId());
    theBook.save();
    theBook.updatePatron(secondPatron.getId());
    assertEquals("Jorge",Patron.find(theBook.getPatronId()).getName());
  }

  @Test
  public void checkout_GivesUsTheProperCheckoutAndDueDate(){
    Patron firstPatron = new Patron("Tequila");
    firstPatron.save();
    Book theBook = new Book("Harry Potter","JK",firstPatron.getId());
    theBook.save();
    theBook.checkOut();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    long addTwoWeeks = rightNow.getTime()+1209600033;
    Timestamp expectedDueDate = new Timestamp(addTwoWeeks);
    assertEquals(rightNow.getDay(),theBook.getCheckOutDate().getDay());
    assertEquals(expectedDueDate.getDay(),theBook.getDueDate().getDay());
  }

}
