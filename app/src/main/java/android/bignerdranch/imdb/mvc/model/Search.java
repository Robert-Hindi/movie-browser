package android.bignerdranch.imdb.mvc.model;

import java.io.Serializable;

public class Search implements Serializable {
    private String base;
    private String title;
    private String year;
    private String type;
    private String imdbID;
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String para = "&s=";
        title.replace(" ", "%20");
        title = para + title;
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        String para = "&y=";
        year.replace(" ", "");
        year = para + year;
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        String para = "&type=";
        type = para + type;
        this.type = type;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        String para = "&i=";
        imdbID = para + imdbID;
        this.imdbID = imdbID;
    }
}
