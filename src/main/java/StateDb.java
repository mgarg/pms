import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SG0224789 on 3/2/2016.
 */
public class StateDb {
  final Db db;

  public StateDb(Db db) {
    this.db = db;
  }
  boolean exists(String stateCode) throws SQLException {  // find whether or not the property exists in the db..
    Connection con = db.connection();
    PreparedStatement stmt = con.prepareStatement("select * from state where code = ?");
    stmt.setString(1,stateCode);
    ResultSet rs = stmt.executeQuery();
    return (rs.next());
  }
}
