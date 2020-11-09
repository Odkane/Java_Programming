package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private DatePicker birthdayField;

    @FXML
    private RadioButton rbMale;

    @FXML
    private ToggleGroup sexGroup;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbDivers;

    @FXML
    private TextField salaryField;

    @FXML
    private Button createBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;


    @FXML
    private Label dbStatusLabel;

    @FXML
    private Circle dbStatusCircle;

    private Tooltip tooltip;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, String> firstNameColumn;

    @FXML
    private TableColumn<Employee, String> lastNameColumn;

    @FXML
    private TableColumn<Employee, String> birthdayColumn;

    @FXML
    private TableColumn<Employee, String> sexColumn;

    @FXML
    private TableColumn<Employee, Integer> salaryColumn;
    //Variablen
    private Database database = new Database();
    private EmployeeModel employeeModel = new EmployeeModel();

    private ObservableList<Employee> employees;

    @FXML
    void clearBtnTapped(ActionEvent event) {
        clearAll();
        clearBtn.setDisable(true);
    }

    public void clearAll(){
        firstNameField.clear();
        lastNameField.clear();
        sexGroup.selectToggle(rbMale);
        salaryField.clear();
        birthdayField.getEditor().clear();
        birthdayField.setValue(null);
        createBtn.setDisable(true);
    }

    @FXML
    void createBtnTapped(ActionEvent event) {
        createEmployee();
    }

    @FXML
    void deleteBtnTapped(ActionEvent event) {
        deleteEmployee();
        loadEmployee();
    }

    @FXML
    void updateBtnTapped(ActionEvent event) {

    }

//    void tableViewTapped(ActionEvent event) {
//       // deleteBtn.setDisable(false);
//    }


    @FXML
    void keyReleaseProperty(KeyEvent event) {

        String firstName="";
        String lastName="";
        String salaryText="";
        String birthday="";
        int salary=0;


       try {
           firstName = firstNameField.getText();
           lastName = lastNameField.getText();
           salaryText = salaryField.getText();
           birthday = birthdayField.getValue().toString();
       }
       catch (Exception e){
           // e.printStackTrace();
       }
       try{
           salary = Integer.parseInt(salaryText);
       }
       catch (Exception e) {
           // e.printStackTrace();
       }


        boolean createBtnDisable = (firstName.isEmpty() || firstName.trim().isEmpty()) ||
                (lastName.isEmpty() || lastName.trim().isEmpty()) ||
                (salary==0) ||
                (birthday.isEmpty() || birthday.trim().isEmpty());

        boolean clearBtnDisable = (firstName.isEmpty() || firstName.trim().isEmpty()) &&
                (lastName.isEmpty() || lastName.trim().isEmpty()) &&
                (salary==0) &&
                (birthday.isEmpty() || birthday.trim().isEmpty());

        if (!clearBtnDisable)
            clearBtn.setDisable(false);
        else
            clearBtn.setDisable(true);

        if (!createBtnDisable)
            createBtn.setDisable(false);
        else
            createBtn.setDisable(true);
    }

    public void loadEmployee(){
        try {
            employees = employeeModel.loadEmployees(database.getStatement(),employees);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("birthday"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("sex"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salary"));

        employeeTableView.setItems(employees);
        /*createBtn.setDisable(true);
        clearBtn.setDisable(true);*/
    }

    public boolean stringCheck(String s){
        s = s.trim();
        char[] word = s.toCharArray();
        for (char c: word){
            if (!Character.isLetter(c))
                return false;
        }

        return true;
    }

    public boolean integerCheck(){

        return true;
    }

    public String selectedSex(){
        if (rbMale.isSelected())
            return "M";
        else if (rbFemale.isSelected())
            return "F";
        else
            return "D";
    }

     public void deleteEmployee() {
         Employee employee = employeeTableView.getSelectionModel().getSelectedItem();
         String firstName = employee.firstName.getValue();
         String lastName = employee.lastName.getValue();

         if (firstName.isEmpty() || lastName.isEmpty())
             return;

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Warning");
         alert.setContentText("Do you really want to delete this Employee?");
         ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
         //ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
         ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
         alert.getButtonTypes().setAll(cancelButton, yesButton);
         alert.showAndWait().ifPresent(type -> {
             if (type == yesButton) {
                 //System.out.println("ich bin hier");
                 try {
                     employeeModel.deleteEmployee(database.getStatement(), firstName,lastName);
                     PopupWindow.display("Employee successfully deleted");
                 }
                 catch (Exception e){
                     e.printStackTrace();
                 }
           //  } else if (type == ButtonType.NO) {
             }
//             else if (type == cancelButton){
//                 System.out.println("ich bin da");
//             }
         });



     }

    public void createEmployee() {
        Integer salary=0;
        String firstName="";
        String lastName="";
        String birthday="";
        String sex;

        try {
            firstName = firstNameField.getText();
            lastName = lastNameField.getText();
            birthday = birthdayField.getValue().toString();
            String salaryText = salaryField.getText();
            sex = selectedSex();
            salary = Integer.parseInt(salaryText);
            //System.out.println(firstName + sex);
        }
        catch (Exception e){
            System.out.println("Irgendwas läuft falsch");
            //tooltip.setText("Du bist falsch");
            //firstNameField.setTooltip(tooltip);
            PopupWindow.display("Some entries are wrong");
            return;
        }

        LocalDate date = LocalDate.now();
        boolean firstNameCheck = (!firstName.isEmpty() && firstName.length()>=3 && stringCheck(firstName));
        boolean lastNameCheck = (!lastName.isEmpty() && lastName.length()>=3 && stringCheck(lastName));
        boolean birthDayCheck = date.isAfter(LocalDate.parse(birthday));
        boolean salaryCheck = (salary>=0);

        boolean entriesCheck =  firstNameCheck && lastNameCheck && birthDayCheck && salaryCheck;

        ArrayList<String> errorMessage = new ArrayList<>();
        String message = "";

        if (entriesCheck)
            try {
                boolean checkEmployee= employeeModel.checkEmployee(database.getStatement(), firstName,lastName);
                if (checkEmployee) {
                    employeeModel.createEmployee(database.getStatement(), firstName, lastName, birthday, sex, salary);
                    PopupWindow.display("Employee successfully created");
                    loadEmployee();
                    clearAll();
                }
                else {
                    PopupWindow.display("Employee exists already");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        else {
            if (!firstNameCheck)
                errorMessage.add("Firstname muss be not empty, and just contains letters \n");
            if (!lastNameCheck)
                errorMessage.add("Lastname muss be not empty, and just contains letters \n");
            if (!birthDayCheck)
                errorMessage.add("Birthday can't not be in the Future \n");
            if (!salaryCheck)
                errorMessage.add("Salary must be Integer , greater than 0 \n");

            for (String word : errorMessage){
                message +=word;
            }

            PopupWindow.display(message);
        }
//        for(int i = 0; i < errorMessage.size(); i++) {
//            message += errorMessage.get(i);
//        }

    }

    public void disableDelBtn(){
        deleteBtn.setDisable(true);
    }

    public void activateDelBtn(){
        deleteBtn.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBtn.setDisable(true);
        clearBtn.setDisable(true);
        deleteBtn.setDisable(true);

        boolean dbConnection = database.open();

        if (dbConnection) {
            dbStatusLabel.setText("OK");
            dbStatusCircle.setFill(Color.GREEN);
            loadEmployee();
        } else {
            dbStatusLabel.setText("Error");
            dbStatusCircle.setFill(Color.RED);
        }
    }
}
