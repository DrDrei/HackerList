package com.andreiusenka.hackerlist.entities;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import java.util.Map;

/**
 * Created by drei on 2017-01-28.
 */

@IgnoreExtraProperties
public class TimeSegment {
    private Map<String, String> startTime;
    private Map<String, String> endTime;

    public TimeSegment() {
        this.startTime = ServerValue.TIMESTAMP;
    }


    public Map<String, String> getStartTime() {
        return startTime;
    }

    public Map<String, String> getEndTime() {
        return endTime;
    }

    public void setStartTime(Map<String, String> startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Map<String, String> endTime) {
        this.endTime = endTime;
    }

    public void stopTime() {
        this.endTime = ServerValue.TIMESTAMP;
    }
}
