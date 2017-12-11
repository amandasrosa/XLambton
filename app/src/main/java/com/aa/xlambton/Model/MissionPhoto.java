package com.aa.xlambton.Model;

/**
 * Created by Amanda on 11/12/2017.
 */

public class MissionPhoto {
    private int id;
    private String photoPath;
    private Mission mission;
    private boolean selected = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public boolean selected(){
        return selected;
    }
    public void turnSelected(){
        selected = !selected;
    }

}
