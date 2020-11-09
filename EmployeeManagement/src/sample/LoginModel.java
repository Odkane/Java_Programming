package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel {

    public boolean isLogin(String username, String password, Connection connection) throws Exception{
        PreparedStatement statement = null;
        ResultSet result = null;
        //if (connection) {
           // String query = "SELECT * FROM Users where username = " + username + " and password = " + password;

        String query = "SELECT * FROM Users where username = ? and password = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,username);
            statement.setString(2,password);

            result = statement.executeQuery();
            if (result.next())
                return true;
            return false;
        } catch (Exception e) {
            return false;

        } finally {
            statement.close();
           // connection.close();
        }

        //}
    }
}
