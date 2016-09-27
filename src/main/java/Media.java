import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.sql.Timestamp;
import java.util.Date;

public abstract class Media {
  public String title;
  public int patronId;
  public int id;
  public Timestamp checkOutDate;
  public Timestamp dueDate;
  public String type;
  public static final int MAX_RENEWALS = 2;



  public Timestamp getCheckOutDate(){
    return checkOutDate;
  }

  public Timestamp getDueDate(){
    return dueDate;
  }
  public String getType(){
    return type;
  }

  public void mediaReturn(){
      try(Connection con = DB.sql2o.open()){
        patronId =1;
        String sql = "UPDATE media SET checkout =null, duedate = null, patronid=1 WHERE id=:id";
        con.createQuery(sql).addParameter("id",this.id).throwOnMappingFailure(false).executeUpdate();
      }
  }


  public void checkOut(int renter){
    try(Connection con = DB.sql2o.open()){
      patronId = renter;
      String sql = "UPDATE media SET checkout =:checkout, duedate=:duedate, patronid=:patronid WHERE id=:id";
      checkOutDate =  new Timestamp(new Date().getTime());
      long addTwoWeeks = checkOutDate.getTime()+1209600033;
      dueDate = new Timestamp(addTwoWeeks);
      con.createQuery(sql).addParameter("duedate",this.dueDate)
      .addParameter("checkout",this.checkOutDate)
      .addParameter("patronid",this.patronId)
      .addParameter("id",this.id).executeUpdate();
    }
  }

  public String getTitle() {
    return title;
  }

  public int getId(){
    return id;
  }

  public int getPatronId(){
    return patronId;
  }

  @Override
  public boolean equals(Object otherMedia){
    if(!(otherMedia instanceof Media)){
      return false;
    }else{
      Media newMedia = (Media) otherMedia;
      return this.getTitle().equals(newMedia.getTitle()) && this.getPatronId()==newMedia.getPatronId();
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO media (title, patronId,type) VALUES (:title, :patronId,:type)";
      this.id = (int) con.createQuery(sql,true).addParameter("title",this.title).addParameter("patronId",this.patronId).addParameter("type",this.type).executeUpdate().getKey();
    }
  }

  // public static List<Media> all(){
  //   try(Connection con = DB.sql2o.open()){
  //     String sql = "SELECT * FROM media";
  //     return con.createQuery(sql).executeAndFetch(Media.class);
  //   }
  // }
  //
  // public static Media find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM media where id=:id";
  //     return con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Media.class);
  //   }
  // }

  // public void update(String title, String author) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE media SET title = :title, author = :author, patronId = null WHERE id = :id";
  //     con.createQuery(sql)
  //       .addParameter("title", title)
  //       .addParameter("author", author)
  //       .addParameter("patronId", patronId)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }

  // public void updatePatron(int patronId) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE media SET patronId = :patronId WHERE id = :id";
  //     con.createQuery(sql)
  //       .addParameter("patronId", patronId)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM media WHERE id=:id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Media> searchMedia(String search){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT title FROM media WHERE lower(title) LIKE :title";
      search = "%" + search + "%";
      search = search.toLowerCase();
      return con.createQuery(sql).addParameter("title",search).executeAndFetch(Media.class);
    }
  }

  // public static List<Media> searchAuthors(String search){
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT author FROM media WHERE lower(author) LIKE :author";
  //     search = "%" + search + "%";
  //     search = search.toLowerCase();
  //     return con.createQuery(sql).addParameter("author",search).executeAndFetch(Media.class);
  //   }
  //}

}
