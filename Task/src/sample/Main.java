package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("My Tasks");
        primaryStage.setScene(new Scene(root, 400, 375));
        primaryStage.show();

//        primaryStage.setOnCloseRequest(e ->{
//            System.out.println("Stage is closing");
//            Platform.exit();
//        });


//        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            public void handle(WindowEvent we) {
//                System.out.println("Stage is closing");
//            }
//        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
