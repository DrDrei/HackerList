package com.andreiusenka.hackerlist.entities;

import com.andreiusenka.hackerlist.util.FirebaseInterface;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;
import java.util.Map;


@IgnoreExtraProperties
public class Task {
    private String title;
    private String taskID;
    private List<TimeSegment> timeSegList;
    private Boolean isCompleted;
    private Boolean isActive;
    private Map<String, String> scheduledDate;
    private String userID;

    public Task(String title) {
        this.title = title;
        this.taskID = FirebaseInterface.registerTaskId();
        this.isCompleted = false;
        this.isActive = false;
        this.userID = FirebaseInterface.getUserUid();
        updateTask();
    }

    public void updateTask() {
        FirebaseInterface.registerTask(this);
    }

    public String getTaskID() {
        return taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TimeSegment> getTimeSegList() {
        return timeSegList;
    }

    public void setTimeSegList(List<TimeSegment> timeSegList) {
        this.timeSegList = timeSegList;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Map<String, String> getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Map<String, String> scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isComplete() {
        return isCompleted;
    }

    public void setComplete() {
        isCompleted = true;
    }

    public void setIncomplete() {
        isCompleted = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive() {
        isActive = true;
    }

    public void setInactive() {
        isActive = false;
    }

    public String getTimeForListView() {
        return "00:00:00";
    }

    // TODO: 2017-01-28 method for computing the duration from all the timesegments

}
