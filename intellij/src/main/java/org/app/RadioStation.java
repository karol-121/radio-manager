package org.app;

public class RadioStation {
    private String url, name, genre, language, bitrate;
    private Boolean favorite;

    public RadioStation(String url, String name, String genre, String language, String bitrate, Boolean favorite) {
        this.url = url;
        this.name = name;
        this.genre = genre;
        this.language = language;
        this.bitrate = bitrate;
        this.favorite = favorite;
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

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFav(Boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return this.url + " " + this.name + " " + this.genre + " " + this.language + " " + this.bitrate + " " + this.favorite;
    }
}
