package com.example.demoproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLHelper";
    static final String DB_NAME = "NewsPaper.db";
    static final String DB_NAME_TABLE = "LinkTable";
    static final int DB_VERSION = 1;

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;


    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreaTable = "CREATE TABLE LinkTable ( " +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "title Text," +
                "time Text," +
                " link Text )";


        //Chạy câu lệnh tạo bảng
        db.execSQL(queryCreaTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists " + DB_NAME_TABLE);
            onCreate(db);
        }
    }

    public void insertProduct(String title, String time, String link) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        //cách 1
//        contentValues.put("name", "Coca");
//        contentValues.put("quantity", "15");

        //cách 2
        contentValues.put("title", title);
        contentValues.put("time", time);
        contentValues.put("link", link);

        sqLiteDatabase.insert(DB_NAME_TABLE, null, contentValues);
        closeDB();
    }

    public int deleteNote(int id) {
        sqLiteDatabase = getWritableDatabase();
        return Long.valueOf(sqLiteDatabase.delete(DB_NAME_TABLE, " id=?",
                new String[]{String.valueOf(id)})).intValue();
    }

    public boolean deleteNoteAll() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_NAME_TABLE, null, null);
        closeDB();
        return true;
    }

    public void updateProduct(String id, String title, String time, String link) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("time", time);
        contentValues.put("link", link);

        sqLiteDatabase.update(DB_NAME_TABLE, contentValues, "id=?", new String[]{String.valueOf(id)});
        closeDB();
    }

    public void getAllProduct() {
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false, DB_NAME_TABLE, null, null, null
                , null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String link = cursor.getString(cursor.getColumnIndex("link"));
            Log.d(TAG, "getAllProduct: " + "id - " + id + " - title - " + title + " - time - " + time + " - link - " + link);
        }
        closeDB();
    }

    public Cursor getAll(SQLiteDatabase db){
        Cursor cursor = db.query(false, DB_NAME_TABLE, null, null, null
                , null, null, null, null);
        return cursor;
    }

    private void closeDB() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
    }
}
