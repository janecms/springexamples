package com.hellojd.springexample.bean;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/7.
 */
public class Event {
    private int eventId;
    private String title;
    private Date eventDate;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
