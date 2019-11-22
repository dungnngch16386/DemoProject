package com.example.demoproject.ui.notifications;

import java.util.Date;

public class Notification {
    String notification;
    String imageNoti;
    Date dateNoti;

    public Notification(String notification, String imageNoti) {
        this.notification = notification;
        this.imageNoti = imageNoti;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getImageNoti() {
        return imageNoti;
    }

    public void setImageNoti(String imageNoti) {
        this.imageNoti = imageNoti;
    }

    public Date getDateNoti() {
        return dateNoti;
    }

    public void setDateNoti(Date dateNoti) {
        this.dateNoti = dateNoti;
    }
}
