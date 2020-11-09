package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML private TextField newTask;
    @FXML private ListView<String> listView;
    @FXML private Button exitBtn;

    private String dataPath = "data.txt";
    private File fp = new File(dataPath);

    public void exit() throws IOException{

        List<String> currentTasks = listView.getItems();
        FileWriter writer = new FileWriter(dataPath);

        for (String text: currentTasks){
            text += "\n";
            writer.write(text);
        }
        writer.close();

    }

    public void exitProgramm(ActionEvent e) throws IOException {
        exit();
        System.exit(0);
    }

    public void addNewTask(ActionEvent e){
        String text = newTask.getText();
        //listView.getItems().add(text);

        if (!text.isEmpty()) {
            if (!listView.getItems().contains(text)) {
                listView.getItems().add(text);
                newTask.clear();
            } else {
                listView.getSelectionModel().select(text);
            }
        }

    }

    public void deleteTAsk(ActionEvent e){
        String selected = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(selected);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Scanner myReader = new Scanner(fp);
            // List<String> currentTasks = new ArrayList<>();
            String text = "";
            while (myReader.hasNextLine()) {
                text = myReader.nextLine();
                listView.getItems().add(text);
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
