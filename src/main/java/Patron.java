import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Patron {
  private String name;
  private id;


  public Patron(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }


  @Override
  public boolean equals(Object otherPatron) {
    if (!(otherPatron instanceof Patron)) {
      return false;
    } else {
      Patron patron = (Patron) otherPatron;
      return this.getName().equals(patron.getName()) &&
             this.getId() == patron.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patrons(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public List<Patron> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM patrons";
      con.createQuery(sql).executeAndFetch(Patron.class);
    }
  }

  public Patron find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM patrons WHERE id=:id";
      return con.createQuery(sql).addParameter("id",id)
      .executeAndFetchFirst(Patron.class);
    }
  }

  public void update(String name){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE patrons SET nane = :name WHERE id =:id";
      con.createQuery(sql).addParameter("name",name).addParameter("id",id).executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM patrons WHERE id=:id";
      con.createQuery(sql).addParameter("id",id).executeUpdate();
    }
  }

  public List<Book> getBooks(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM books WHERE patronId=:id";
      return con.createQuery(sql).addParameter("id",id).executeAndFetch(Book.class);
    }
  }

}
