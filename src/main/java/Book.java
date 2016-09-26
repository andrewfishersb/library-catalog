import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.sql.Timestamp;
import java.util.Date;

public class Book {
  private String title;
  private String author;
  private int patronId;
  private int id;
  private Timestamp checkOutDate;
  private Timestamp dueDate;

  public static final int MAX_RENEWALS = 2;

  public Book(String title, String author, int patronId) {
    this.title = title;
    this.author = author;
    this.patronId = patronId;
    this.checkOutDate = null;
    this.dueDate = null;
  }

  public Timestamp getCheckOutDate(){
    return checkOutDate;
  }

  public Timestamp getDueDate(){
    return dueDate;
  }

  public void checkOut(){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE books SET checkout =:checkout, duedate=:duedate WHERE id=:id";
      checkOutDate =  new Timestamp(new Date().getTime());
      long addTwoWeeks = checkOutDate.getTime()+1209600033;
      dueDate = new Timestamp(addTwoWeeks);
      con.createQuery(sql).addParameter("duedate",this.dueDate)
      .addParameter("checkout",this.checkOutDate)
      .addParameter("id",this.id).executeUpdate();
    }
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getId(){
    return id;
  }

  public int getPatronId(){
    return patronId;
  }

  @Override
  public boolean equals(Object otherBook){
    if(!(otherBook instanceof Book)){
      return false;
    }else{
      Book newBook = (Book) otherBook;
      return this.getTitle().equals(newBook.getTitle()) &&  this.getAuthor().equals(newBook.getAuthor());
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO books (title, author, patronId) VALUES (:title, :author, :patronId)";
      this.id = (int) con.createQuery(sql,true).addParameter("title",this.title).addParameter("author",this.author).addParameter("patronId",this.patronId).executeUpdate().getKey();
    }
  }

  public static List<Book> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM books";
      return con.createQuery(sql).executeAndFetch(Book.class);
    }
  }

  public static Book find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM books where id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Book.class);
    }
  }

  public void update(String title, String author) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE books SET title = :title, author = :author, patronId = null WHERE id = :id";
      con.createQuery(sql)
        .addParameter("title", title)
        .addParameter("author", author)
        .addParameter("patronId", patronId)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updatePatron(int patronId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE books SET patronId = :patronId WHERE id = :id";
      con.createQuery(sql)
        .addParameter("patronId", patronId)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM books WHERE id=:id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Book> searchBooks(String search){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT title FROM books WHERE lower(title) LIKE :title";
      search = "%" + search + "%";
      search = search.toLowerCase();
      return con.createQuery(sql).addParameter("title",search).executeAndFetch(Book.class);
    }
  }

  public static List<Book> searchAuthors(String search){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT author FROM books WHERE lower(author) LIKE :author";
      search = "%" + search + "%";
      search = search.toLowerCase();
      return con.createQuery(sql).addParameter("author",search).executeAndFetch(Book.class);
    }
  }

}
