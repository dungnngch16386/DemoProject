package com.example.demoproject.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoproject.R;

import java.util.List;

public class PaperAdapter extends BaseAdapter {
    List<Paper> paperList;

    public PaperAdapter(List<Paper> paperList) {
        this.paperList = paperList;
    }

    @Override
    public int getCount() {
        return paperList.size();
    }

    @Override
    public Object getItem(int i) {
        return paperList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.item_paper, viewGroup, false);

        ImageView ivPaper = view.findViewById(R.id.ivPaper);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvShortParagraph = view.findViewById(R.id.tvShortParagraph);
        TextView tvDate = view.findViewById((R.id.tvDate));
        TextView tvCategory = view.findViewById((R.id.tvCategory));

        Paper paper = paperList.get(i);

        tvTitle.setText(paper.getTitle());
        tvShortParagraph.setText(paper.getShortParagraph());

        return view;
    }
}
