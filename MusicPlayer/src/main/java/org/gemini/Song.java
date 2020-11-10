package org.gemini;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Song {
    private String title;
    private String artist;
    private String duration;
    private String path;
    private Image cover;

    public Song(String title, String artist, String duration, String path) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.path = path;
    }

    public Song(String title, String artist, String duration, String path,  Image cover) {
        this(title, artist, duration, path);
        this.cover = cover;
    }

    public String getPath() {
        return path;
    }

    public Image getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }
}
