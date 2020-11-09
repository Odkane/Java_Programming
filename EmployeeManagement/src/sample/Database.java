package sample;

import java.sql.*;

public class Database {

    private static final String dbName = "TutorialDB";
    private static final String host = "jdbc:mysql://localhost:3306/" + dbName;
    private static final String user = "root";
    private static final String pwd = "wFFVspMQUnfbvX4WGndvX6Ue";

    private Connection connection;
    private Statement statement;

    public boolean open() {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(host, user, pwd);
            statement = connection.createStatement();
            //System.out.println("Erfolgreich");
            return true;
        } catch (
                SQLException e) {
           // System.out.println(e.getMessage());
            return false;
        }
    }

    public Connection getConnection() {

        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
