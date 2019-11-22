package com.example.demoproject.ui.home;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoproject.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PaperAdapterDemo extends ArrayAdapter<Paper> {
    Context context;

    public PaperAdapterDemo(Context context, int resource, List<Paper> items) {
        super(context, resource, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_paper, null);
        }
        Paper p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            ImageView ivPaper = view.findViewById(R.id.ivPaper);
            TextView tvTitle = view.findViewById(R.id.tvTitle);
            TextView tvShortParagraph = view.findViewById(R.id.tvShortParagraph);
            TextView tvComment = view.findViewById(R.id.tvComment);
            TextView tvDate = view.findViewById(R.id.tvDate);

            tvTitle.setText(p.title);
            tvShortParagraph.setText(p.shortParagraph);
            tvComment.setText(p.numOfCmt);
            tvDate.setText(p.date);

            // Set hinh anh
            Picasso.with(context)
                    .load(p.img)
                    .into(ivPaper);


        }
        return view;
    }

}
