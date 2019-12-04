package com.example.demoproject.ui.detailcategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoproject.R;
import com.example.demoproject.ui.home.Paper;
import com.squareup.picasso.Picasso;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

public class PaperCateAdapter extends ArrayAdapter<PaperCate> {

    Context context;

    public PaperCateAdapter(Context context, int resource, List<PaperCate> items) {
        super(context, resource, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_papercate, null);
        }
        PaperCate p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            ImageView ivPaper = view.findViewById(R.id.ivPaper2);
            TextView tvTitle = view.findViewById(R.id.tvTitle2);
            TextView tvShortParagraph = view.findViewById(R.id.tvShortParagraph2);
            TextView tvComment = view.findViewById(R.id.tvComment2);
            TextView tvDate = view.findViewById(R.id.tvDate2);

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
