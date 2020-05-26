package org.app;

public class RadioStation {
    private String url, name, genre, language, bitrate, fav;

    public RadioStation(String url, String name, String genre, String language, String bitrate, String fav) {
        this.url = url;
        this.name = name;
        this.genre = genre;
        this.language = language;
        this.bitrate = bitrate;
        this.fav = fav;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    @Override
    public String toString() {
        return this.url + " " + this.name + " " + this.genre + " " + this.language + " " + this.bitrate + " " + this.fav;
    }
}
