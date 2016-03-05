import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SG0224789 on 3/2/2016.
 */
public class PropertyDb {
  final Db db;

  public PropertyDb(Db db) {
    this.db = db;
  }
  boolean exists(int propertyId) throws SQLException {
    Connection con = db.connection();
    PreparedStatement stmt = con.prepareStatement("select * from property where id = ?");
    stmt.setInt(1,propertyId);
    ResultSet rs = stmt.executeQuery();
    if (rs.next())
      return true;
    else
    {
      System.out.println("property id does not match");
      return false;
    }
  }
}
