package com.example.class_model;

public class ListMenuGetterSetter{
    String title;
    String subtitles;
    int image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ListMenuGetterSetter(String title, String subtitles, int image) {
        this.title = title;
        this.subtitles = subtitles;
        this.image = image;
    }


}
