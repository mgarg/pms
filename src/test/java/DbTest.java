import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbTest {
  @Test
  public void testName() throws Exception {
    try (Connection connection = new Db().connection();
         PreparedStatement stmt = connection.prepareStatement("select * from country");
         ResultSet rs = stmt.executeQuery()) {
      while (rs.next()) {
        String anInt = rs.getString(2);
        System.out.println(anInt);
      }
    }

  }
}
