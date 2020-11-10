module MusicPlayer.main {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;
    requires com.jfoenix;
    requires fontawesomefx;

    opens org.gemini to javafx.fxml;
    exports org.gemini;

}