package com.example.demoproject.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.R;

public class DetailActivity extends AppCompatActivity {

    WebView wvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
//        Toast.makeText(getBaseContext(), link, Toast.LENGTH_LONG).show();

        wvDetail = findViewById(R.id.wvDetail);
        wvDetail.loadUrl(link);
        wvDetail.setWebViewClient(new WebViewClient());
    }
}
