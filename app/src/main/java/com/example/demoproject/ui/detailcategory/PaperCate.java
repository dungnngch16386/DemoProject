package com.example.demoproject.ui.detailcategory;

public class PaperCate {
    String title;
    String shortParagraph;
    String img;
    String date;
    String category;
    String numOfCmt;
    String link;

    public PaperCate(String title, String link, String img, String shortParagraph, String numOfCmt, String date) {
        this.title = title;
        this.link = link;
        this.img = img;
        this.shortParagraph = shortParagraph;
        this.numOfCmt = numOfCmt;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortParagraph() {
        return shortParagraph;
    }

    public void setShortParagraph(String shortParagraph) {
        this.shortParagraph = shortParagraph;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNumOfCmt() {
        return numOfCmt;
    }

    public void setNumOfCmt(String numOfCmt) {
        this.numOfCmt = numOfCmt;
    }
}
