package com.example.demoproject.ui.notifications;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.demoproject.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    ListView lvNotification;
    List<Notification> notifications;
    NotificationAdapter notificationAdapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Notification notification1, notification2, notification3, notification4, notification5, notification6;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        //Khai báo SharedPreferences bộ nhớ tạm
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();

        lvNotification = view.findViewById(R.id.lvNotification);
        notifications = new ArrayList<>();

        notification1 = new Notification("Quốc vương Thái Lan Rama X được nhận xét là một người kín tiếng nhưng lại có đời sống hôn nhân gây chú ý với 5 người vợ.","a");
        notification2 = new Notification("Tài xế Vũ Thị Hồng Thái (47 tuổi) lái ôtô Mercedes tông ba xe máy đã bị Công an quận Cầu Giấy tạm giữ hình sự","b");

        notifications.add(notification1);
        notifications.add(notification2);

        NotificationAdapter notificationAdapter = new NotificationAdapter(notifications);
        lvNotification.setAdapter(notificationAdapter);

        lvNotification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"This is a notification ", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}