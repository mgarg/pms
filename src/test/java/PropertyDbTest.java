import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by SG0224789 on 3/3/2016.
 */
public class PropertyDbTest {
  @Test
  public void testDb() throws SQLException {
    PropertyDb pd = new PropertyDb(new Db());
    assertEquals(true, pd.exists(111111));

  }

}
