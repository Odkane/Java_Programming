package org.gemini;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerController implements Initializable {

    @FXML
    private AnchorPane stagePane;

    @FXML
    private MediaView mediaView;

    @FXML
    private ImageView imageView;

    @FXML
    private JFXButton exitBtn;

    @FXML
    private JFXButton playlistBtn;

    @FXML
    private StackPane imgPane;

    @FXML
    private Label titelLabel;

    @FXML
    private JFXButton backwardBtn;

    @FXML
    private JFXButton playBtn;

    @FXML
    private JFXButton forwardBtn;

    @FXML
    private JFXSlider timeSlider;

    @FXML
    private JFXSlider volumeSlider;

    @FXML
    private JFXButton repeatPlaylistBtn;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private JFXButton volumeUpBtn;

    @FXML
    private Pane playlistPane;

    @FXML
    private JFXListView<String> playlistView;

    @FXML
    private JFXButton deleteSongBtn;

    @FXML
    private FontAwesomeIcon playIcon;

    @FXML
    private JFXButton addSongBtn;

    private ObservableList<Song> songs;

    private MediaPlayer mediaPlayer;
    private Media media;
    private File file;
    private String filePath;
    private String fileExtension;
    private boolean repeat;
    private boolean shuffle;
    public int currentIndex;

    @FXML
    void activateRepeat(ActionEvent event) {
        repeat =  (repeat==true) ? false: true;
    }

    public List<File> selectedFiles = Stream.of(new File[]{}).collect(Collectors.toList());
    public Iterator<File> itr;

    private String artist;
    private String title;
    private String duration;

    @FXML
    void addMusic(ActionEvent event) {
        //System.out.println("open file");
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(
               new FileChooser.ExtensionFilter("mp3", "*.mp3", "*.mp4")
        );

        file = fileChooser.showOpenDialog(null);
        if (file != null){
            filePath = file.toURI().toString();
            fileExtension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
        }
        System.out.println(filePath + " " + fileExtension);
        if (filePath != null) {
            //if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))
            if (mediaPlayer != null)
                mediaPlayer.stop();
            media = new Media(filePath);
            System.out.println(media.getDuration());
            if (fileExtension.equals("mp3")) {
                media.getMetadata().addListener(new MapChangeListener<String, Object>() {

                    @Override
                    public void onChanged(Change<? extends String, ?> change) {
                        if (change.wasAdded()) {
                            handleMetadata(change.getKey(), change.getValueAdded());
                        }
                    }
                });
                mediaView.setVisible(false);
                imageView.setVisible(true);
            }
            else {
                mediaView.setVisible(true);
                mediaView.setMediaPlayer(mediaPlayer);
                imageView.setVisible(false);
           }


            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setVolume(volumeSlider.getValue()/100);
            mediaPlayer.play();
            playIcon.setIconName("PAUSE");
            System.out.println(mediaPlayer.getMedia().getDuration().toSeconds());
            mediaPlayer.setOnReady(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Duration: "+ media.getDuration().toSeconds());

                    // display media's metadata
                    for (Map.Entry<String, Object> entry : media.getMetadata().entrySet()){
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }

                    // play if you want
                    mediaPlayer.play();
                }
            });
        }
    }

    public void addSong() {
        try {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Music"));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Music Files -- MP3", "*.mp3"));
            selectedFiles = fc.showOpenMultipleDialog(null);
            //creating meadiagroups
            playlistView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    for (int i = 0; i < selectedFiles.size(); i++) {
                        if (selectedFiles.get(i).getName().equals(newValue)) {
                            currentIndex = i;
                            loadPlayer(selectedFiles.get(i).toURI().toString());
                        }
                    }
                }
            });
            playlistView.getItems().clear();
            //checking whether list is empty or not if not then loading items in listview
            if (selectedFiles != null) {
                for (int i = 0; i < selectedFiles.size(); i++) {
                    playlistView.getItems().add(selectedFiles.get(i).getName());
                }
            }
            final Group mediagroup = new Group();
            //creating list iterator
            itr = selectedFiles.iterator();
            //check whether shffle is on/off
            //loading player with first file
            loadPlayer(selectedFiles.get(0).toURI().toString());
            //adding mediaviews in group
            mediagroup.getChildren().add(mediaView);
            String currentmedia = media.getSource().toString();
            //here happens the magic of current index
            loop1:
            for (int j = 0; j <= selectedFiles.size(); j++) {
                if (currentmedia.equals(selectedFiles.get(j).toURI().toString())) {
                    currentIndex = j;
                    break loop1;
                }
            }
        } catch (Exception e) {
        }
    }


    private void handleMetadata(String key, Object value) {

        if (key.equals("artist")) {
            titelLabel.setText(value.toString());
        } if (key.equals("title")) {
            if (!value.toString().isEmpty())
                if (titelLabel.getText().isEmpty())
                    titelLabel.setText(value.toString());
                else
                    titelLabel.setText(titelLabel.getText() + " - " + value.toString());
        }
        if (key.equals("image")) {
            imageView.setImage((Image)value);
        }
    }

    @FXML
    void backwardMedia(ActionEvent event) {

        if(shuffle==false){
            //this two things removes the current media from the player
            mediaPlayer.stop();
            mediaPlayer = null;
            //as previous requested getting the index of previous song
            int previousmedia = currentIndex - 1;
            //checking for whether this fisrst song or not if yes then restart fom firts using itreator
            if (previousmedia < 0) {
                itr = selectedFiles.iterator();
                loadPlayer(itr.next().toURI().toString());
            }
            //if this is not fist song using previous index load the previous song
            loadPlayer(selectedFiles.get((previousmedia)).toURI().toString());
            currentIndex = currentIndex - 1;
            itr.equals(selectedFiles.get(currentIndex).toURI().toString());
        }else{
            Random rand = new Random();
            mediaPlayer.stop();
            loadPlayer(selectedFiles.get(rand.nextInt(selectedFiles.size())).toURI().toString());
        }


    }

    @FXML
    void deleteSong(MouseEvent event) {

    }

    @FXML
    void exitPlayer(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void forwardMedia(ActionEvent event) {

        if (shuffle == false) {
            //this two things removes the current media from the player
            mediaPlayer.stop();
            //as requested next song this make song index to next song
            if (currentIndex == 0) {
                currentIndex = currentIndex + 1;
            } else if (currentIndex == selectedFiles.size() - 1) {
                currentIndex = 0;
            } else {
                currentIndex = currentIndex + 1;
            }
            //checking whether it is last song or not
            String currentmedia = selectedFiles.get(currentIndex).toURI().toString();
            loadPlayer(currentmedia);
            loop1:
            for (int j = 0; j <= selectedFiles.size(); j++) {
                if (currentmedia.equals(selectedFiles.get(j).toURI().toString())) {
                    currentIndex = j;
                    break loop1;
                }
            }
        } else {
            Random rand = new Random();
            mediaPlayer.stop();
            loadPlayer(selectedFiles.get(rand.nextInt(selectedFiles.size())).toURI().toString());
        }


    }

    @FXML
    void openPlaylist(ActionEvent event) {
        boolean vis = playlistPane.isVisible() ? false : true;
        playlistPane.setVisible(vis);
    }

    @FXML
    void playMedia(ActionEvent event) {
        if (mediaPlayer!=null)
            if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer.pause();
                playIcon.setIconName("PLAY");
            }else {
                mediaPlayer.play();
                playIcon.setIconName("PAUSE");
                volumeSlider.valueProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        mediaPlayer.setVolume(volumeSlider.getValue()/100);
                    }
                });

//                mediaPlayer.currentTimeProperty().addListener(new InvalidationListener()
//                {
//                    public void invalidated(Observable ov) {
//                        updateValues();
//                    }
//                });

                mediaPlayer.setOnReady(() -> {
                    startLabel.textProperty().bind(
                            Bindings.createStringBinding(() -> {
                                        Duration time = mediaPlayer.getCurrentTime();
                                        return String.format("%4d:%02d:%04.1f",
                                                (int) time.toHours(),
                                                (int) time.toMinutes() % 60,
                                                time.toSeconds() % 3600);
                                    },
                                    mediaPlayer.currentTimeProperty()));

                    timeSlider.maxProperty().bind(
                            Bindings.createDoubleBinding(
                                    () -> mediaPlayer.getTotalDuration().toSeconds(),
                                    mediaPlayer.totalDurationProperty()));

                    mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                        timeSlider.setValue(newValue.toSeconds());
                    });

                    timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                        mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                    });
                });


            }

    }

//    protected void updateValues() {
//        if (playTime != null && timeSlider != null && volumeSlider != null) {
//            Platform.runLater(new Runnable() {
//                public void run() {
//                    Duration currentTime = mediaPlayer.getCurrentTime();
//                    playTime.setText(formatTime(currentTime, duration));
//                    timeSlider.setDisable(duration.isUnknown());
//                    if (!timeSlider.isDisabled()
//                            && duration.greaterThan(Duration.ZERO)
//                            && !timeSlider.isValueChanging()) {
//                        timeSlider.setValue(currentTime.divide(duration).toMillis()
//                                * 100.0);
//                    }
//                    if (!volumeSlider.isValueChanging()) {
//                        volumeSlider.setValue((int)Math.round(mediaPlayer.getVolume()
//                                * 100));
//                    }
//                }
//            });
//        }
//    }

    public void loadSongs(){
        String text;
        ArrayList<String> list= new ArrayList<>();

        for (Song song : songs){
            if (!song.getArtist().isEmpty())
                text = song.getArtist() + " - " + song.getTitle();
            else
                text = song.getTitle();

            list.add(text);
        }

        playlistView.setItems(FXCollections.observableArrayList(list));

    }

    public void loadPlayer(String path) {
        //default player initializations
//        Image img = new Image("../../images/background.jpg");
//        //albumart.setImage(img);
//        imageView.setImage(img);
        titelLabel.setText("Unknown");
        //artistn.setText("Unknown");
        if (path == null)
            return;
        if (mediaPlayer != null) {
            //very important to remove pre-media
            mediaPlayer.stop();
            mediaPlayer = null;
            media = null;
            //-------
            timeSlider.setValue(0);
           // statevalue = 1;
            //String pic = "sample/res/pause.png";
            //playpause.setStyle("-fx-background-image: url('" + pic + "');");
            playIcon.setIconName("PLAY");
        }
        media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(new Runnable() {
            public void run() {
                startLabel.setText("0:00");
                timeSlider.setValue(0.0);
                double duration3 = mediaPlayer.getTotalDuration().toMillis();
                System.out.println(duration3);
                double time2 = media.getDuration().toSeconds();
                double lasttime = media.getDuration().toMinutes();
                String duration2 = new Double(lasttime).toString();
                String duration = new Double(time2).toString();
                //endLabel.setText(duration2.substring(0,4));
                endLabel.setText(duration2.substring(0, 4).replace('.',':'));
                timeSlider.setMax(time2);

            }
        });


        mediaPlayer.setAutoPlay(true);
        volumeSlider.setMax(100);
        playIcon.setIconName("PAUSE");

//        mediaPlayer.setVolume(volume);
//        volumeSlider.setValue(volume * 100);
//        if (volume == 1 || volume * 100 == 1) {
//            volumetxt.setText("100");
//        } else if (volume < 1) {
//            volumetxt.setText(String.valueOf(volume).substring(2, 4));
//        } else if (volume < 0.1) {
//            volumetxt.setText(String.valueOf(volume).substring(2, 3));
//        }
//        volumeSlider.valueProperty().addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable observable) {
//                double val = volumeSlider.getValue();
//                mediaPlayer.setVolume(volumeSlider.getValue()/100);
//                volume = val / 100;
//                mediaPlayer.setVolume(val / 100);
//                InputStream input = getClass().getResourceAsStream("res/nonmute.png");
//                Image image = new Image(input);
//                mute.setImage(image);
//                if (volume == 1 || volume == 1.0) {
//                    volumetxt.setText("100");
//                } else if (volume > 0.1) {
//                    volumetxt.setText(String.valueOf(volume * 100).substring(0, 2));
//                } else if (volume < 0.1) {
//                    volumetxt.setText(String.valueOf(volume * 100).substring(0, 1));
//                }
//                if (val == 0.0) {
//                    mediaPlayer.setVolume(0);
//                    InputStream inpt = getClass().getResourceAsStream("res/mute.png");
//                    Image imag = new Image(inpt);
//                    volumeSlider.setValue(0);
//                    mute.setImage(imag);
//                }
//            }
//        });

//        mediaPlayer.currentTimeProperty().addListener((observableValue, oldDuration, newDuration) -> {
//           // System.out.println("Player:" + observableValue + " | Changed from playing at: " + oldDuration + " to play at " + newDuration);
//          // String duration = new Double(time2).toString();
//            String duration = new Double(String.valueOf(newDuration.toMinutes())).toString();
//            startLabel.setText(newDuration.toString().substring(0,4).replace('.',':'));
//            try {
//                Thread.sleep(60);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        });

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<>() {

            @Override
            public void changed(ObservableValue<? extends Duration> observable,
                                Duration oldTime, Duration newTime) {

                timeSlider.setValue(newTime.toMillis() / mediaPlayer.getTotalDuration().toMillis() * 100);
                double formattedTime1 = newTime.toSeconds();
                double formattedTime = newTime.toMinutes(); // your computations
                //System.out.println(String.valueOf(formattedTime) + ":" + String.valueOf(formattedTime1));
                startLabel.setText(String.valueOf(formattedTime).substring(0,4).replace(".",":"));
            }

        });

        try {
            media.getMetadata().addListener(new MapChangeListener<String, Object>() {
                @Override
                public void onChanged(Change<? extends String, ?> ch) {
                    if (ch.wasAdded()) {
                        handleMetadata(ch.getKey(), ch.getValueAdded());
                    }
                }
            });
        } catch (RuntimeException re) {
            // Handle construction errors
            System.out.println("Caught Exception: " + re.getMessage());
        }
        //this function works when one media finished or stopped playing
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            //runnable run() method run automatically
            @Override
            public void run() {
                if (shuffle == false) {
                    if (currentIndex == 0) {
                        //Plays the subsequent files
                        currentIndex = currentIndex + 1;
                        loadPlayer(selectedFiles.get(currentIndex).toURI().toString());

                    } else if (currentIndex == selectedFiles.size() - 1) { //this elseif checks whether the file is last or not if disturbed
                        loadPlayer(selectedFiles.get(0).toURI().toString());
                        //updating the value
                        currentIndex = 0;
                    } else if (selectedFiles.size() == 2 & currentIndex == 1) { // this else block move to next media from list if it is disturbed by user and not last file
                        loadPlayer(selectedFiles.get(0).toURI().toString());
                        currentIndex = 0;
                    } else {
                        String currentMedia = selectedFiles.get(currentIndex + 1).toURI().toString();
                        loadPlayer(currentMedia);
                        loop1:
                        for (int j = 0; j <= selectedFiles.size(); j++) {
                            if (currentMedia.equals(selectedFiles.get(j).toURI().toString())) {
                                currentIndex = j;
                                break loop1;
                            }
                        }
                    }
                } else {
                    Random rand = new Random();
                    loadPlayer(selectedFiles.get(rand.nextInt(selectedFiles.size())).toURI().toString());
                }

            }
        });
        //String currentTime = "";
        while(mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            mediaPlayer.setOnPlaying(new Runnable() {
                @Override
                public void run() {
                    System.out.println(mediaPlayer.getCurrentTime().toMinutes());
                }
            });
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void listenTime(MouseEvent event){
        if (mediaPlayer != null)
 //           timeSlider.valueProperty().addListener(new InvalidationListener() {
   //             @Override
  //              public void invalidated(Observable observable) {
                    try {
                        mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
                        double time1 = mediaPlayer.getCurrentTime().toMinutes();
                        String duration = new Double(time1).toString();
                        startLabel.setText(duration.substring(0, 4).replace('.',':'));
                    } catch (Exception e) {
                    }

 //               }
 //           });
    }

    @FXML
    public void listenVolume(MouseEvent event){
        if (mediaPlayer != null)
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume((volumeSlider.getValue()/100.0));
                }
            });
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playlistPane.setVisible(false);
    //    mediaView.setVisible(false);
        imageView.setVisible(false);

//        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                if (mediaPlayer !=null) {
//                    mediaPlayer.setVolume((int) newValue);
//                }
//            }
//        });
//        if (media !=null)


    }
}
