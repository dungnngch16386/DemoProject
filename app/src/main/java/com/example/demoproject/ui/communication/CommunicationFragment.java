package com.example.demoproject.ui.communication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.demoproject.R;

public class CommunicationFragment extends Fragment {

    private CommunicationViewModel communicationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        communicationViewModel =
                ViewModelProviders.of(this).get(CommunicationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_communication, container, false);
//        final TextView textView = root.findViewById(R.id.text_communication);
//        communicationViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}