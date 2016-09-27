import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.sql.Timestamp;
import java.util.Date;

public class CD extends Media{
  private String artist;
  private boolean adultRating;
  public static final String DATABASE_TYPE = "cd";


  public CD(String title, String artist, boolean adultRating, int patronId){
      this.title = title;
      this.artist = artist;
      this.adultRating = adultRating;
      this.patronId = patronId;
      type = DATABASE_TYPE;
  }

  public String getArtist(){
    return artist;
  }

  public boolean over18(){
    return adultRating;
  }

  //all find update

  public List<CD> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM media WHERE type=:type";
      return con.createQuery(sql).addParameter("type",this.type).throwOnMappingFailure(false).executeAndFetch(CD.class);
    }
  }

  public CD find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM media WHERE id=:id";
      return con.createQuery(sql).addParameter("id",this.id).throwOnMappingFailure(false).executeAndFetchFirst(CD.class);
    }
  }

  public void update(){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE media SET title=:title, artist=:artist,adultRating = :adultRating WHERE id=:id";
      con.createQuery(sql).addParameter("title",this.title).addParameter("artist",this.artist).addParameter("adultRating",this.adultRating).addParameter("id",this.id).throwOnMappingFailure(false).executeUpdate();
    }
  }
}
