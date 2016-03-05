import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SG0224789 on 3/2/2016.
 */
public class ChainDb {
  final Db db;

  public ChainDb(Db db) {
    this.db = db;
  }

  boolean exists(String chainCode) throws SQLException {  // find whether or not the property exists in the db..
      Connection con = db.connection();
      PreparedStatement stmt = con.prepareStatement("select * from property p,chain c where chainid = c.id and code = ?");
      stmt.setString(1,chainCode);
      ResultSet rs = stmt.executeQuery();
    if (rs.next())
      return true;
    else
    {
      System.out.println("chain code does not match");
      return false;
    }
  }
}
