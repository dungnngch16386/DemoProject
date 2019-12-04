package com.example.demoproject.ui.bookmarks;

public class Bookmark {
    int id;
    String title;
    String time;
    String link;

    public Bookmark(String title, String time, String link, int id) {
        this.title = title;
        this.time = time;
        this.link = link;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
