import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Db {
    final OracleDataSource ds;

    public Db() throws SQLException {
        ds = new OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
        ds.setUser("system");
        ds.setPassword("a");
    }

    Connection connection() throws SQLException {
        return  ds.getConnection();
    }
}
