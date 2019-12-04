package com.example.demoproject.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.demoproject.R;
import com.example.demoproject.ui.bookmarks.Demo3Activity;

public class SettingFragment extends Fragment {

    TextView tvBookmark;

    private SettingViewModel settingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                ViewModelProviders.of(this).get(SettingViewModel.class);
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        tvBookmark = view.findViewById(R.id.tvBookmark);

        tvBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Demo3Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}