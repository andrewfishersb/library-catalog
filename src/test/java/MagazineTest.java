// import org.junit.*;
// import static org.junit.Assert.*;
// import org.sql2o.*;
// import java.text.DateFormat;
// import java.sql.Timestamp;
// import java.util.Date;
//
// public class MagazineTest {
//
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void book_instantiatesCorrectly_true() {
//     Magazine testMagazine = new Magazine("Harry Potter","JK Rowling", 1);
//     assertEquals(true, testMagazine instanceof Magazine);
//   }
//
//   @Test
//   public void Magazine_instantiatesWithName_String() {
//     Magazine testMagazine = new Magazine("Harry Potter","JK Rowling", 1);
//     assertEquals("Harry Potter", testMagazine.getTitle());
//   }
//   @Test
//   public void Magazine_instantiatesWithPatronId_int() {
//     Magazine testMagazine = new Magazine("Harry Potter","JK Rowling", 1);
//     assertEquals(1, testMagazine.getPatronId());
//   }
//   @Test
//   public void equals_returnsTrueIfNameAndAuthorAndPatronIdAreSame_true() {
//     Magazine testMagazine = new Magazine("Harry Potter","JK Rowling", 1);
//     Magazine anotherMagazine = new Magazine("Harry Potter","JK Rowling", 1);
//     assertTrue(testMagazine.equals(anotherMagazine));
//   }
//   @Test
//   public void save_returnsTrueIfTitlesAretheSame() {
//     Magazine testMagazine = new Magazine("Harry Potter","JK Rowling", 1);
//     testMagazine.save();
//     assertTrue(Magazine.all().get(0).equals(testMagazine));
//   }
//
//   @Test
//   public void all_returnsAllInstancesOfMagazine_true() {
//     Magazine firstMagazine = new Magazine("Harry Potter","JK Rowling", 1);
//     firstMagazine.save();
//     Magazine secondMagazine = new Magazine("Lord of The Rings", "JR Tolkien", 1);
//     secondMagazine.save();
//     assertEquals(true, Magazine.all().get(0).equals(firstMagazine));
//     assertEquals(true, Magazine.all().get(1).equals(secondMagazine));
//   }
//
//   @Test
//   public void find_returnsMagazineWithSameId_secondMagazine() {
//     Magazine firstMagazine = new Magazine("Harry Potter","JK Rowling", 1);
//     firstMagazine.save();
//     Magazine secondMagazine = new Magazine("Lord of The Rings", "JR Tolkien", 1);
//     secondMagazine.save();
//     assertEquals(Magazine.find(secondMagazine.getId()), secondMagazine);
//   }
//
//   @Test
//   public void save_savesPatronIdIntoDB_true() {
//     Patron testPatron = new Patron("Henry");
//     testPatron.save();
//     Magazine testMagazine = new Magazine("Harry Potter","JK Rowling", testPatron.getId());
//     testMagazine.save();
//     Magazine savedMagazine = Magazine.find(testMagazine.getId());
//     assertEquals(savedMagazine.getPatronId(), testPatron.getId());
//   }
// }
