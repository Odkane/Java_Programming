package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopupWindow {

    public static void display(String message){
        Stage stage =  new Stage();
        stage.setTitle("Info!");
        Label label = new Label(message);
        Button button = new Button("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(label, button);
        vbox.setSpacing(20.0);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 400,150);

        stage.setScene(scene);
        stage.showAndWait();

    }
}
