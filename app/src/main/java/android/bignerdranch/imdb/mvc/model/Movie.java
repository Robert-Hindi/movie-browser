package android.bignerdranch.imdb.mvc.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.net.URL;

public class Movie implements Serializable {

    private String title;
    private String year;
    private String poster;
    private String url;
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getUrl() { return url; }

    public void setUrl(String imdbID) {
        String url;
        url = "https://www.imdb.com/title/" + imdbID + "/";
        this.url = url; }

}
