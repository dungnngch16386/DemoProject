package com.example.demoproject.ui.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.demoproject.R;
import com.example.demoproject.SQLHelper;
import com.example.demoproject.ui.bookmarks.Demo3Activity;
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

public class HomeFragment extends Fragment {

    ListView lvHome;
    ImageButton ibBookmark;
    SQLHelper sqlHelper;

    //    Demo RSS
    PaperAdapterDemo paperAdapterDemo;
    ArrayList<Paper> paperArrayList;
    ArrayList<String> hinhanhArray;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sqlHelper = new SQLHelper(getContext());

        ibBookmark = view.findViewById(R.id.ibBookmark);
        ibBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), Demo3Activity.class);
                startActivity(intent1);
            }
        });

        // Tuong tac voi RSS
        lvHome = view.findViewById(R.id.lvHome);
        paperArrayList = new ArrayList<Paper>();
        hinhanhArray = new ArrayList<>();

        new ReadRSS().execute("https://vnexpress.net/rss/the-gioi.rss");

        lvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sqlHelper.insertHistory(paperArrayList.get(i).title, paperArrayList.get(i).date, paperArrayList.get(i).link);
                paperAdapterDemo.notifyDataSetChanged();

                String linkDemo = paperArrayList.get(i).link;
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("link", linkDemo);
                startActivity(intent);
            }
        });

        lvHome.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                sqlHelper.insertProduct(paperArrayList.get(i).title, paperArrayList.get(i).date, paperArrayList.get(i).link);
                Toast.makeText(getContext(), "The news was added into the bookmark list", Toast.LENGTH_LONG).show();
                paperAdapterDemo.notifyDataSetChanged();
                return true;
            }
        });

        return view;
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
                paperArrayList.add(new Paper(tieuDe, link, hinhAnh, doanVanNgan, binhLuan, bocTachThoiGian));
            }
            paperAdapterDemo = new PaperAdapterDemo(getContext(), R.layout.item_paper, paperArrayList);
            lvHome.setAdapter(paperAdapterDemo);
            paperAdapterDemo.notifyDataSetChanged();
            super.onPostExecute(s);
        }
    }
}