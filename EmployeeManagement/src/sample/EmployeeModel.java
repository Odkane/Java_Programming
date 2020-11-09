package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeModel {
    private final String TABLE_EMPLOYEE= "employee";
    private final String QUERY_DATA_FROM_TABLE = "SELECT * FROM "+ TABLE_EMPLOYEE;
   // private final String QUERY = "SELECT MAX(emp_id) FROM " + TABLE_EMPLOYEE;

    public Integer maxID(Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT MAX(emp_id) FROM " + TABLE_EMPLOYEE);
        Integer max=0;
        while (result.next())
            max = result.getInt(1);

        System.out.printf(String.valueOf(max));
        return max+1;
    }

    public boolean checkEmployee(Statement statement, String firstName, String lastName) throws SQLException{
        ResultSet result = statement.executeQuery("SELECT COUNT(first_name) FROM " + TABLE_EMPLOYEE + " WHERE first_name= '" + firstName + "' and last_name= '" + lastName +"'");
        Integer count=0;
        while (result.next())
            count = result.getInt(1);

        if (count>0)
            return false;
        return true;
    }

    public void createEmployee(Statement statement, String firstName, String lastName, String birthday, String sex, Integer salary ) throws SQLException{
        statement.execute("INSERT INTO " + TABLE_EMPLOYEE + " VALUES (" + maxID(statement) + ", '" + firstName + "', '" + lastName + "', '" + birthday + "', '" + sex + "', " + salary +", NULL, NULL)");
        //statement.close();
    }

    public void deleteEmployee(Statement statement, String firstName, String lastName) throws SQLException{
        statement.execute("DELETE FROM "+ TABLE_EMPLOYEE + " WHERE first_name= '" + firstName + "' and last_name= '" + lastName +"'");
        //statement.close();
    }

    public void updateEmployee(){

    }



    public ObservableList<Employee> loadEmployees(Statement statement, ObservableList<Employee> list) throws SQLException {
        list = FXCollections.observableArrayList();

        ResultSet result = statement.executeQuery(QUERY_DATA_FROM_TABLE);
        //ResultSet result2 = statement.executeQuery((QUERY));
       // int max = result2.getInt(1);

        while(result.next()){
            int emp_id = result.getInt(1);
            String firstName = result.getString(2);
            String lastName = result.getString(3);
            String birthday = result.getString(4);
            String sex = result.getString(5);
            int salary = result.getInt(6);
            //max++;
            list.add(new Employee(emp_id,firstName,lastName,birthday,sex,salary));
        }

        //statement.close();
        return  list;
    }
}
