package com.andreiusenka.hackerlist.entities;

import com.google.firebase.database.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;


@IgnoreExtraProperties
public class TimeSegment {
    private Long startTime;
    private Long endTime;

    public TimeSegment() {
        this.startTime = System.currentTimeMillis();
        this.endTime = System.currentTimeMillis();
    }

    public Long getStartTime() {
        return startTime;
    }

    public String getStartTimeForListItem() {
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        return format.format(new Date(getStartTime()));
    }

    public Long getEndTime() {
        return endTime;
    }

    public String getEndTimeForListItem() {
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        return format.format(new Date(getEndTime()));
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public void stopTime() {
        this.endTime = System.currentTimeMillis();
    }

    public Long getTimeDifference() {
        return endTime - startTime;
    }
}
