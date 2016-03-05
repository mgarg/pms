import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by SG0224789 on 3/2/2016.
 */
public class CountryDbTest {
  @Test
  public void testDb() throws SQLException {
    CountryDb cd = new CountryDb(new Db());
    assertEquals(true,cd.exists("IN"));
  }

}
