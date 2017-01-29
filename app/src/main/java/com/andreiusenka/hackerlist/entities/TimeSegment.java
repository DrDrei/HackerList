package com.andreiusenka.hackerlist.entities;

import com.google.firebase.database.IgnoreExtraProperties;


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

    public Long getEndTime() {
        return endTime;
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
