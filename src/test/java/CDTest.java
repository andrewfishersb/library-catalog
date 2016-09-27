// import org.junit.*;
// import static org.junit.Assert.*;
// import org.sql2o.*;
// import java.text.DateFormat;
// import java.sql.Timestamp;
// import java.util.Date;
//
// public class CDTest {
//
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void book_instantiatesCorrectly_true() {
//     CD testCD = new CD("Blackholes","Muse", false,1);
//     assertEquals(true, testCD instanceof CD);
//   }
//
//   @Test
//   public void CD_instantiatesWithName_String() {
//     CD testCD = new CD("Blackholes","Muse", false,1);
//     assertEquals("Blackholes", testCD.getTitle());
//   }
//   @Test
//   public void CD_instantiatesWithPatronId_int() {
//     CD testCD = new CD("Blackholes","Muse", false,1);
//     assertEquals(1, testCD.getPatronId());
//   }
//   @Test
//   public void equals_returnsTrueIfNameAndAuthorAndPatronIdAreSame_true() {
//     CD testCD = new CD("Blackholes","Muse", false,1);
//     CD anotherCD = new CD("Harry Potter","JK Rowling", 1);
//     assertTrue(testCD.equals(anotherCD));
//   }
//   @Test
//   public void save_returnsTrueIfTitlesAretheSame() {
//     CD testCD = new CD("Blackholes","Muse", false,1);
//     testCD.save();
//     assertTrue(CD.all().get(0).equals(testCD));
//   }
//
//   @Test
//   public void all_returnsAllInstancesOfCD_true() {
//     CD firstCD = new CD("Harry Potter","JK Rowling", 1);
//     firstCD.save();
//     CD secondCD = new CD("Lord of The Rings", "JR Tolkien", 1);
//     secondCD.save();
//     assertEquals(true, CD.all().get(0).equals(firstCD));
//     assertEquals(true, CD.all().get(1).equals(secondCD));
//   }
//
//   @Test
//   public void find_returnsCDWithSameId_secondCD() {
//     CD firstCD = new CD("Harry Potter","JK Rowling", 1);
//     firstCD.save();
//     CD secondCD = new CD("Lord of The Rings", "JR Tolkien", 1);
//     secondCD.save();
//     assertEquals(CD.find(secondCD.getId()), secondCD);
//   }
//
//   @Test
//   public void save_savesPatronIdIntoDB_true() {
//     Patron testPatron = new Patron("Henry");
//     testPatron.save();
//     CD testCD = new CD("Harry Potter","JK Rowling", testPatron.getId());
//     testCD.save();
//     CD savedCD = CD.find(testCD.getId());
//     assertEquals(savedCD.getPatronId(), testPatron.getId());
//   }
// }
