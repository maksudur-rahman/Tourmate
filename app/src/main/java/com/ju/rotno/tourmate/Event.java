package com.ju.rotno.tourmate;

/**
 * Created by Mac on 23-10-17.
 */
public class Event {
    private String desName,fromaDate,toDate;
    private int eventID,budget;
    public Event(int eventID, String desName, String fromaDate, String toDate, int budget) {
        this.desName = desName;
        this.fromaDate = fromaDate;
        this.toDate = toDate;
        this.eventID = eventID;
        this.budget = budget;
    }
    public Event(String desName, String fromaDate, String toDate,int budget) {
        this.desName = desName;
        this.fromaDate = fromaDate;
        this.toDate = toDate;
        this.eventID = eventID;
        this.budget = budget;
    }

    public Event(){

    }
    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getDesName() {
        return desName;
    }

    public void setDesName(String desName) {
        this.desName = desName;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getFromaDate() {
        return fromaDate;
    }

    public void setFromaDate(String fromaDate) {
        this.fromaDate = fromaDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }


}
