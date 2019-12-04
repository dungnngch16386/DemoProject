package com.example.demoproject.ui.detailcategory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.MainActivity;
import com.example.demoproject.R;
import com.example.demoproject.SQLHelper;
import com.example.demoproject.ui.detail.DetailActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailCategoryActivity extends AppCompatActivity {

    ListView lvCategory;
    TextView tvTitleOfCategory;
    ImageButton ibBookmark, ibBackCategory;
    SQLHelper sqlHelper;
    String rss;

    //    Demo RSS
    PaperCateAdapter paperCateAdapter;
    ArrayList<PaperCate> paperCates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        sqlHelper = new SQLHelper(getBaseContext());

        Intent intent = getIntent();
        rss = intent.getStringExtra("rss");

        // Tuong tac voi RSS
        lvCategory = findViewById(R.id.lvCategory);
        tvTitleOfCategory = findViewById(R.id.tvTitleOfCategory);
        ibBackCategory = findViewById(R.id.ibBackCategory);
        paperCates = new ArrayList<>();
        new ReadRSS().execute(rss);

        if (rss.equals("https://vnexpress.net/rss/kinh-doanh.rss")) {
            tvTitleOfCategory.setText("Bussiness");
        } else if (rss.equals("https://vnexpress.net/rss/the-thao.rss")) {
            tvTitleOfCategory.setText("Sport");
        } else if (rss.equals("https://vnexpress.net/rss/the-gioi.rss")) {
            tvTitleOfCategory.setText("World");
        } else if (rss.equals("https://vnexpress.net/rss/oto-xe-may.rss")) {
            tvTitleOfCategory.setText("Vehicle");
        }

        ibBackCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String linkDemo = paperCates.get(i).link;
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                intent.putExtra("link", linkDemo);
                startActivity(intent);
            }
        });
    }

    class ReadRSS extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }

                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();

            Document document = parser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDescription = document.getElementsByTagName("description");

            String tieuDe = "";
            String doanVanNgan = "";
            String hinhAnh = "";
            String urlImg = "";
            String link = "";
            String binhLuan = "";
            String thoiGian = "";
            String bocTachThoiGian = "";

            for (int i = 0; i < nodeList.getLength(); i++) {
//                    Lay hình anh
                String cdata = nodeListDescription.item(i + 1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);

                int startDescription2 = cdata.indexOf("data-original=");
                int endDescription2 = cdata.indexOf(".jpg");

                if (startDescription2 != -1 && endDescription2 != -1) {
                    hinhAnh = cdata.substring(startDescription2 + 15, endDescription2 + 4); //+15 +4
                    Log.d("urlImg", urlImg + "......" + i);
                } else if (matcher.find()) {
                    hinhAnh = matcher.group(1);
                    Log.d("hinhanh", hinhAnh + "......" + i);
                }

//                Lay doan van ngan
                int startDescription = cdata.indexOf("</br>");
                doanVanNgan = cdata.substring(startDescription + 5);

                Element element = (Element) nodeList.item(i);

                tieuDe = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                binhLuan = parser.getValue(element, "slash:comments");
                thoiGian = parser.getValue(element, "pubDate");

//                Boc tach thoi gian
                int endDescription3 = thoiGian.indexOf("+0700");
                if (endDescription3 != -1) {
                    bocTachThoiGian = thoiGian.substring(0, endDescription3 - 4);
                }

//                Truyen thong tin de hien thi
                paperCates.add(new PaperCate(tieuDe, link, hinhAnh, doanVanNgan, binhLuan, bocTachThoiGian));
            }
            paperCateAdapter = new PaperCateAdapter(getBaseContext(), R.layout.item_papercate, paperCates);
            lvCategory.setAdapter(paperCateAdapter);
            paperCateAdapter.notifyDataSetChanged();
            super.onPostExecute(s);
        }
    }
}
