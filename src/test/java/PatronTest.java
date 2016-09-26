import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class PatronTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void patron_instantiatesCorrectly_true() {
    Patron testPatron = new Patron("Henry");
    assertEquals(true, testPatron instanceof Patron);
  }
  @Test
  public void getName_patronInstantiatesWithName_Henry() {
    Patron testPatron = new Patron("Henry");
    assertEquals("Henry", testPatron.getName());
  }



  @Test
  public void equals_returnsTrueIfNamesAreTheSame_true() {
   Patron firstPatron = new Patron("Henry");
   Patron anotherPatron = new Patron("Henry");
   assertTrue(firstPatron.equals(anotherPatron));
 }

 @Test
  public void save_insertsObjectIntoDatabase_Patron() {
    Patron testPatron = new Patron("Henry");
    testPatron.save();
    assertTrue(Patron.all().get(0).equals(testPatron));
  }

  @Test
  public void all_returnsAllInstancesOfPatron_true() {
    Patron firstPatron = new Patron("Henry");
    firstPatron.save();
    Patron secondPatron = new Patron("Harriet");
    secondPatron.save();
    assertEquals(true, Patron.all().get(0).equals(firstPatron));
    assertEquals(true, Patron.all().get(1).equals(secondPatron));
  }

  @Test
  public void save_assignsIdToObject() {
    Patron testPatron = new Patron("Henry");
    testPatron.save();
    Patron savedPatron = Patron.all().get(0);
    assertEquals(testPatron.getId(), savedPatron.getId());
  }

  @Test
  public void find_returnsPatronWithSameId_secondPatron() {
    Patron firstPatron = new Patron("Henry");
    firstPatron.save();
    Patron secondPatron = new Patron("Harriet");
    secondPatron.save();
    assertEquals(Patron.find(secondPatron.getId()), secondPatron);
  }

  @Test
  public void getBooks_retrievesAllBooksFromDatabase_booksList() {
    Patron testPatron = new Patron("Henry");
    testPatron.save();
    Book firstBook = new Book("Harry Potter","Jk Rowling", testPatron.getId());
    firstBook.save();
    Book secondBook = new Book("I'm Hungry Games","One hit wonder", testPatron.getId());
    secondBook.save();
    Book[] books = new Book[] { firstBook, secondBook };
    assertTrue(testPatron.getBooks().containsAll(Arrays.asList(books)));
  }

  @Test
  public void searchAuthors_SearchesForPartOfAuthor(){
    Patron firstPatron = new Patron("Harry");
    firstPatron.save();
    Patron secondPatron = new Patron("Lorde");
    secondPatron.save();
    Patron thirdPatron = new Patron("Collin");
    thirdPatron.save();
    assertEquals("Collin", Patron.searchPatrons("L").get(1).getName());
  }
}
