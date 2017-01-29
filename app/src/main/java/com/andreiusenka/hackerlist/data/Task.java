package com.andreiusenka.hackerlist.data;

/**
 * Created by satyen on 28/01/17.
 */

public class Task {

    private boolean active;

    public Task() {
        active = false;
    }

    public String getID() {
        return "my_id";
    }

    public String getTitleForListView() {
        return "Title!";
    }

    public boolean isActive() {
        return active;
    }

    public void setActive() {
        active = true;
    }

    public void setInactive() {
        active = false;
    }

    public String getTimeForListView() {
        return "00:00:00";
    }

}
