import java.sql.SQLException;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class Database {

    private String databaseName;
    private JdbcConnectionSource connection;

    public Database(String databaseName) {
        this.databaseName = databaseName;
    }

    public JdbcConnectionSource getConnection() throws SQLException {

        if (databaseName == null) {
            throw new SQLException("Database name is null");
        }

        if (connection == null) {
            connection = new JdbcConnectionSource(
                "jdbc:sqlite:" + databaseName
            );

            System.out.println("Opened database successfully");
        }

        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}