import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.sql.Timestamp;
import java.util.Date;

public class Book extends Media {
  private String author;
  public static final String DATABASE_TYPE = "book";

  public Book(String title, String author, int patronId) {
    this.title = title;
    this.author = author;
    this.patronId = patronId;
    type = DATABASE_TYPE;
    // this.checkOutDate = null;
    // this.dueDate = null;
  }

  public Timestamp getCheckOutDate(){
    return checkOutDate;
  }

  public Timestamp getDueDate(){
    return dueDate;
  }


  public String getAuthor() {
    return author;
  }



  // @Override
  // public boolean equals(Object otherBook){
  //   if(!(otherBook instanceof Book)){
  //     return false;
  //   }else{
  //     Book newBook = (Book) otherBook;
  //     return this.getTitle().equals(newBook.getTitle()) &&  this.getAuthor().equals(newBook.getAuthor());
  //   }
  // }




  public static List<Book> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM media;";
      return con.createQuery(sql).executeAndFetch(Book.class);
    }
  }

  public static Book find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM media where id=:id";
      return con.createQuery(sql)
        .addParameter("id", id).throwOnMappingFailure(false)
        .executeAndFetchFirst(Book.class);
    }
  }

  public void update(String title, String author) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE media SET title = :title, author = :author, patronId = null WHERE id = :id";
      con.createQuery(sql)
        .addParameter("title", title)
        .addParameter("author", author)
        .addParameter("patronId", patronId)
        .addParameter("id", id).throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

  //
  // public static List<Book> searchBooks(String search){
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT title FROM media WHERE lower(title) LIKE :title";
  //     search = "%" + search + "%";
  //     search = search.toLowerCase();
  //     return con.createQuery(sql).addParameter("title",search).executeAndFetch(Book.class);
  //   }
  // }
  //
  // public static List<Book> searchAuthors(String search){
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT author FROM media WHERE lower(author) LIKE :author";
  //     search = "%" + search + "%";
  //     search = search.toLowerCase();
  //     return con.createQuery(sql).addParameter("author",search).executeAndFetch(Book.class);
  //   }
  // }

}
