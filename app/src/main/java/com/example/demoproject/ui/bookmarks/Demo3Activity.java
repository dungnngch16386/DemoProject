package com.example.demoproject.ui.bookmarks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.demoproject.MainActivity;
import com.example.demoproject.R;
import com.example.demoproject.SQLHelper;
import com.example.demoproject.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class Demo3Activity extends AppCompatActivity {

    List<Bookmark> bookmarks;
    ImageButton ibBackBookmark, ibBookmark;
    EditText inputSearch;
    RecyclerView recyclerView;
    BookmarkAdapter bookmarkAdapter;
    SQLHelper sqlHelper;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);
        sqlHelper = new SQLHelper(getBaseContext());
        ibBackBookmark = findViewById(R.id.ibBackBookmark);
        inputSearch = findViewById(R.id.inputSearch);
        ibBookmark = findViewById(R.id.ibBookmark);

        ibBackBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ibBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlHelper.deleteNoteAll();
                Toast.makeText(getBaseContext(), "Removing all bookmarks successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), Demo3Activity.class);
                startActivity(intent);
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        //Khai báo SharedPreferences bộ nhớ tạm
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = preferences.edit();

        recyclerView = findViewById(R.id.rvList2);

        bookmarks = new ArrayList<>();

        //Lay du lieu tu database va them vao list
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = sqlHelper.getAll(db);
        String title, time, link;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            title = cursor.getString(cursor.getColumnIndex("title"));
            time = cursor.getString(cursor.getColumnIndex("time"));
            link = cursor.getString(cursor.getColumnIndex("link"));
            bookmarks.add(new Bookmark(title, time, link, id));
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getBaseContext(), 1, RecyclerView.VERTICAL, false);

        bookmarkAdapter = new BookmarkAdapter(bookmarks, getBaseContext());

        recyclerView.setAdapter(bookmarkAdapter);
        recyclerView.setLayoutManager(layoutManager);

        bookmarkAdapter.setIonClickBookmark(new IonClickBookmark() {

            @Override
            public void onClickTitle(String link, int position) {
                //Toast.makeText(getBaseContext(), link, Toast.LENGTH_SHORT).show();
                //String linkDemo = bookmarks.get(position).link;
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }

            @Override
            public void onClickImg(String link, int position) {
                //String linkDemo = bookmarks.get(position).link;
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }

            @Override
            public void onClickRemove(int position) {
                int idDemo = bookmarks.get(position).id;
                sqlHelper.deleteNote(idDemo);
                Toast.makeText(getBaseContext(), "Removing sucessfully!", Toast.LENGTH_SHORT).show();
                bookmarks.remove(position);
                bookmarkAdapter.notifyItemRemoved(position);
            }
        });
    }

    public void filter(String text) {
        ArrayList<Bookmark> filteredList = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(bookmark);
            }
        }

        bookmarkAdapter.filterList(filteredList);
    }
}
