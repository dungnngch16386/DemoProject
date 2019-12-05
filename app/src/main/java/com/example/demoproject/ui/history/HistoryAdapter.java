package com.example.demoproject.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoproject.R;
import com.example.demoproject.ui.home.Paper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<History> {
    Context context;

    public HistoryAdapter(Context context, int resource, List<History> items) {
        super(context, resource, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_history, null);
        }
        History p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView tvTitleHistory = view.findViewById(R.id.tvTitleHistory);
            TextView tvDateHistory = view.findViewById(R.id.tvDateHistory);

            tvTitleHistory.setText(p.title);
            tvDateHistory.setText(p.time);
        }
        return view;
    }

}
