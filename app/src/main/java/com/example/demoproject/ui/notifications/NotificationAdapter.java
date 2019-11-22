package com.example.demoproject.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoproject.R;

import java.util.List;

public class NotificationAdapter extends BaseAdapter {
    List<Notification> notificationList;

    public NotificationAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int i) {
        return notificationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.item_noti, viewGroup, false);

        ImageView ivNoti = view.findViewById(R.id.ivNoti);
        TextView tvShortNoti = view.findViewById(R.id.tvShortNoti);
        TextView tvDateNoti = view.findViewById((R.id.tvDateNoti));

        Notification notification = notificationList.get(i);

        tvShortNoti.setText(notification.getNotification());

        if (notification.getImageNoti() == "a"){
            ivNoti.setImageResource(R.drawable.img_paper);
        } else if (notification.getImageNoti() == "b"){
            ivNoti.setImageResource(R.drawable.anhmot);
        }

        return view;
    }
}
