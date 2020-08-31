package org.app;

public class RadioStation {
    private String url, name, genre, language, bitrate;
    private Boolean favorite;

    public RadioStation(String url, String name, String genre, String language, String bitrate, Boolean favorite) {
        this.url = url.strip();
        this.name = name.strip();
        this.genre = genre.strip();
        this.language = language.strip();
        this.bitrate = bitrate.strip();
        this.favorite = favorite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url.strip();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.strip();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre.strip();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language.strip();
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate.strip();
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFav(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getAttributesStream() {
        return this.name+this.genre+this.language+this;
    }

    @Override
    public String toString() {
        return this.url + " " + this.name + " " + this.genre + " " + this.language + " " + this.bitrate + " " + this.favorite;
    }
}
