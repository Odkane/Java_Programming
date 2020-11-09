package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
    public SimpleIntegerProperty emp_id;
    public SimpleStringProperty firstName;
    public SimpleStringProperty lastName;
    public SimpleStringProperty birthday;
    public SimpleStringProperty sex;
    public SimpleIntegerProperty salary;

    public Employee(int emp_id, String firstName, String lastName, String birthday, String sex, int salary) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = new SimpleStringProperty(birthday);
        this.sex = new SimpleStringProperty(sex);
        this.salary = new SimpleIntegerProperty(salary);
        this.emp_id = new SimpleIntegerProperty(emp_id);
    }

    public int getEmp_id() {
        return emp_id.get();
    }

    public SimpleIntegerProperty emp_idProperty() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id.set(emp_id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public SimpleStringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public int getSalary() {
        return salary.get();
    }

    public SimpleIntegerProperty salaryProperty() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
    }
}
