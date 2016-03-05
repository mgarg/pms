import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SG0224789 on 3/2/2016.
 */

public class CountryDb {
  final Db db;

  public CountryDb(Db db) {
    this.db = db;
  }

  boolean exists(String countryCode) throws SQLException {  // find whether or not the property exists in the db..
    Connection con = db.connection();
    PreparedStatement stmt = con.prepareStatement("select * from country where name = ?");
    stmt.setString(1,countryCode);
    ResultSet rs = stmt.executeQuery();
    if (rs.next())
      return true;
    else
    {
      System.out.println("country code does not match");
      return false;
    }
  }


}
