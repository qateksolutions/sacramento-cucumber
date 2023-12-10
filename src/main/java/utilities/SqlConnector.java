package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SqlConnector {
    private static final String User = ReadConfigFiles.getPropertyValues("DbUser");
    private static final String Password = ReadConfigFiles.getPropertyValues("DbPassword");
    private static final String Host = ReadConfigFiles.getPropertyValues("DBHost");
    private static final String Port = ReadConfigFiles.getPropertyValues("DBPort");
    private static final String DBName = ReadConfigFiles.getPropertyValues("DbName");

    private static final Logger LOGGER = LogManager.getLogger(SqlConnector.class);

    /**
     * jdbc:postgresql://<database_host>:<port>/<database_name>
     * Example: "jdbc:postgresql://localhost:5432/mortgage_calculator"
     */
    private static final String ConnectionUrl = "jdbc:postgresql://" + Host + ":" + Port + "/" + DBName;
    // Alternative Implementation:
    // private static final String ConnectionUrl = String.format("jdbc:postgresql://%s:%s/%s", Host, Port, DBName);

    /**
     * Connect to the postgres database
     * @return a Connection Object
     * @throws Exception if failed to connect to the database
     */
    private static Connection connect() throws Exception {
        LOGGER.info(ConnectionUrl);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(ConnectionUrl, User, Password);
            if (conn != null) {
                LOGGER.debug("Database connection is successful!!!");
            }
        } catch (SQLException e) {
            throw new Exception("SQL Connection Exception is: " + e.getMessage());
        }
        return conn;
    }

    /**
     * Reading Data from the database
     * @param SQL is used to send the SQL query
     * @return a ResultSet Object
     * @throws Exception if failed to execute queries
     */
    public static ResultSet readDate(String SQL) throws Exception {
        ResultSet rs = null;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e) {
            throw new Exception("SQL Result Set Exception: " + e.getMessage());
        }
        return rs;
    }
}
