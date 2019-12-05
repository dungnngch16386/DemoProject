package com.example.demoproject.ui.history;

public class History {
    int id;
    String title;
    String time;
    String link;

    public History(String title, String time, String link, int id) {
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
