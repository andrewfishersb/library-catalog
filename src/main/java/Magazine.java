import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;

public class Magazine extends Media{
  private int issueNumber;
  public static final String DATABASE_TYPE = "magazine";


  public Magazine(String title, int issueNumber, int patronId){
      this.title = title;
      this.issueNumber = issueNumber;
      this.patronId = patronId;
      type = DATABASE_TYPE;
  }

  public int getIssueNumber(){
    return issueNumber;
  }

  public List<Magazine> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM media WHERE type=:type";
      return con.createQuery(sql).addParameter("type",this.type).throwOnMappingFailure(false).executeAndFetch(Magazine.class);
    }
  }

  public Magazine find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM media WHERE id=:id";
      return con.createQuery(sql).addParameter("id",this.id).throwOnMappingFailure(false).executeAndFetchFirst(Magazine.class);
    }
  }

  public void update(){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE media SET title=:title, issueNumber=:issueNumber WHERE id=:id";
      con.createQuery(sql).addParameter("title",this.title).addParameter("issueNumber",this.issueNumber).addParameter("id",this.id).throwOnMappingFailure(false).executeUpdate();
    }
  }
}
