import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SG0224789 on 3/2/2016.
 */
public class CityDb {
  final Db db;

  public CityDb(Db db) {
    this.db = db;
  }
  boolean exists(String cityName) throws SQLException {
    Connection con = db.connection();
    PreparedStatement stmt = con.prepareStatement("select * from property p,city c where p.cityid = c.id and cityname= ?");
    stmt.setString(1,cityName);
    ResultSet rs = stmt.executeQuery();
    if (rs.next())
      return true;
    else
    {
      System.out.println("city name does not match");
      return false;
    }
  }
}
