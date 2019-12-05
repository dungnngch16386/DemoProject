package com.example.demoproject.ui.history;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demoproject.MainActivity;
import com.example.demoproject.R;
import com.example.demoproject.SQLHelper;
import com.example.demoproject.ui.detail.DetailActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ListView lvHistory;
    ImageButton ibHistory, ibBackHistory;
    SQLHelper sqlHelper;

    HistoryAdapter historyAdapter;
    ArrayList<History> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        sqlHelper = new SQLHelper(getBaseContext());

        lvHistory = findViewById(R.id.lvHistory);
        ibHistory = findViewById(R.id.ibHistory);
        ibBackHistory = findViewById(R.id.ibBackHistory);
        histories = new ArrayList<>();

        getData();

        final HistoryAdapter historyAdapter = new HistoryAdapter(getBaseContext(), R.layout.item_history, histories);
        lvHistory.setAdapter(historyAdapter);

        ibHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlHelper.deleteAllHistory();
                Toast.makeText(getBaseContext(), "Removing all histories successfully!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getBaseContext(), HistoryActivity.class);
                startActivity(intent);

            }
        });

        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String linkDemo = histories.get(i).link;
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                intent.putExtra("link", linkDemo);
                startActivity(intent);
            }
        });

        ibBackHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getData() {
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = sqlHelper.getHistory(db);
        String title, time, link;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            title = cursor.getString(cursor.getColumnIndex("title"));
            time = cursor.getString(cursor.getColumnIndex("time"));
            link = cursor.getString(cursor.getColumnIndex("link"));
            histories.add(new History(title, time, link, id));
        }
    }
}
