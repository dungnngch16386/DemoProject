package com.example.demoproject.ui.communication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.demoproject.ui.detailcategory.DetailCategoryActivity;
import com.example.demoproject.R;

public class CommunicationFragment extends Fragment {

    TextView tvSport, tvBussiness, tvWorld, tvCar;
    String rssSport, rssBussiness, rssWorld, rssCar;

    private CommunicationViewModel communicationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        communicationViewModel = ViewModelProviders.of(this).get(CommunicationViewModel.class);
        View view = inflater.inflate(R.layout.fragment_communication, container, false);

        rssSport = "https://vnexpress.net/rss/the-thao.rss";
        rssBussiness = "https://vnexpress.net/rss/kinh-doanh.rss";
        rssWorld = "https://vnexpress.net/rss/the-gioi.rss";
        rssCar = "https://vnexpress.net/rss/oto-xe-may.rss";

        tvSport = view.findViewById(R.id.tvSport);
        tvBussiness = view.findViewById(R.id.tvBussiness);
        tvWorld = view.findViewById(R.id.tvWorld);
        tvCar = view.findViewById(R.id.tvCar);

        tvSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailCategoryActivity.class);
                intent.putExtra("rss", rssSport);
                startActivity(intent);
            }
        });

        tvBussiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailCategoryActivity.class);
                intent.putExtra("rss", rssBussiness);
                startActivity(intent);
            }
        });

        tvWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailCategoryActivity.class);
                intent.putExtra("rss", rssWorld);
                startActivity(intent);
            }
        });

        tvCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailCategoryActivity.class);
                intent.putExtra("rss", rssCar);
                startActivity(intent);
            }
        });

        return view;
    }
}