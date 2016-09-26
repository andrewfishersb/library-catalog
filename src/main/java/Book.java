import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Book {
  private String title;
  private String author;
  private int patronId;
  private int id;

  public Book(String title, String author, int patronId) {
    this.title = title;
    this.author = author;
    this.patronId = patronId;
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
      return this.getName().equals(newBook.getName()) &&  this.getAuthor.equals(newBook.getAuthor());
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
      con.createQuery(sql).executeAndFetch(Book.class);
    }
  }

  public static Book find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks where id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Book.class);
    }
  }

  public void update(String title, String author, int patronId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tasks SET title = :title, author = :author, patronId = :patronId WHERE id = :id";
      con.createQuery(sql)
        .addParameter("title", title)
        .addParameter("author", author)
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



}
