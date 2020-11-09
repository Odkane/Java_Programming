package sample;

//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
//import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label dbStatusLabel;

    @FXML
    private Circle dbStatusCircle;

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField pwdField;

    @FXML
    private Label loginLabel;

    //Datenbank Objekt
    Database database = new Database();

    @FXML
    void keyReleaseProperty(KeyEvent event) {
        String uStr = usernameField.getText();
        String pStr = pwdField.getText();
        boolean isDisabled = (uStr.isEmpty() || uStr.trim().isEmpty() ||uStr.length()<4 || pStr.isEmpty() || pStr.trim().isEmpty() || pStr.length()< 4 );
        loginBtn.setDisable(isDisabled);
        if (!isDisabled && event.getCode().getName().equals("Enter")) {
            //ActionEvent e = new ActionEvent();
            loginBtnAction();
        }

//        if (uStr.length()> 5 || pStr.length()>5 )
//            loginBtn.setDisable(false);
//        else
//            loginBtn.setDisable(true);
    }

    //Login Model
    LoginModel loginModel = new LoginModel();

    @FXML
    void loginBtnAction() {
        String uStr = usernameField.getText();
        String pStr = pwdField.getText();
        Connection connection = database.getConnection();
        //System.out.println(uStr + " " + pStr);


        try{
            if (loginModel.isLogin(uStr, pStr, connection)){
                loginLabel.setText("Login successfull");

                // close login window
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.close();

                //öffne Dashboard
                loginSuccessfull();
                connection.close();
            } else loginLabel.setText("Username or Password is false");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void loginSuccessfull(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();

            Pane root = (Pane) fxmlLoader.load(getClass().getResource("Employee.fxml").openStream());

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.setResizable(false);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginBtn.setDisable(true);

        boolean dbConnection = database.open();
        System.out.println(dbConnection);

        if (dbConnection){
            dbStatusCircle.setFill(Color.GREEN);
            dbStatusLabel.setText("OK");
        }
        else {
            dbStatusCircle.setFill(Color.RED);
            dbStatusLabel.setText("Error");
        }

    }
}
