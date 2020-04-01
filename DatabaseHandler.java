import java.sql.*;

public class DatabaseHandler {
    private Connection myDatabase;

    public DatabaseHandler() {

    }

    /**
     * @return the myDatabase
     */
    public Connection getMyDatabase() {

        try {
            myDatabase = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/inventorysystem?user=root&password=1234");

        } catch (final Exception e) {
            e.getStackTrace();
        }
        return myDatabase;
    }
}