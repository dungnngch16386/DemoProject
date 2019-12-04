package com.example.demoproject.ui.bookmarks;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {

    List<Bookmark> bookmarks;
    IonClickBookmark ionClickBookmark;
    SharedPreferences sharedPreferences;
    Context context;

    public void setIonClickBookmark(IonClickBookmark ionClickBookmark) {
        this.ionClickBookmark = ionClickBookmark;
    }

    public BookmarkAdapter(List<Bookmark> bookmarks, Context context) {
        this.bookmarks = bookmarks;
        this.context = context;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_bookmark, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Bookmark bookmark = bookmarks.get(position);

        holder.tvTitle.setText(bookmark.getTitle());
        holder.tvDate.setText(bookmark.getTime());
        //holder.ivBookmark.setImageResource();

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickBookmark.onClickTitle(bookmark.getLink(), position);
            }
        });

        holder.ivBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickBookmark.onClickImg(bookmark.getLink(), position);
            }
        });

        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickBookmark.onClickRemove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookmarks.size();
    }

    public void filterList(ArrayList<Bookmark> filteredList) {
        bookmarks=filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate;
        ImageView ivBookmark, ivRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivBookmark = itemView.findViewById(R.id.ivBookmark);
            ivRemove = itemView.findViewById(R.id.ivRemove);
        }
    }
}
