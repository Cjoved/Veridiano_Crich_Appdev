package com.example.afinal.ui.module5;

public class recyleview {

    public String mission;
    public String title;

    public recyleview(String title,String mission ) {
        this.mission = mission;
        this.title = title;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
