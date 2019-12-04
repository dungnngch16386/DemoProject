package com.example.demoproject.ui.bookmarks;

public interface IonClickBookmark {

    void onClickTitle(String link, int position);

    void onClickImg(String link, int position);

    void onClickRemove(int position);
}
