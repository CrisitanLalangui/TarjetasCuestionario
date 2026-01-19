package com.example.aplicacindesarrollo2;

import androidx.annotation.NonNull;

public class EventModel {

    public String eventName;

    public String eventDate;

    public String eventLocation;


    public EventModel(String eventName, String eventDate, String eventLocation) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    @NonNull
    @Override
    public String toString() {
        return eventName + " " + eventDate + " " + eventLocation;
    }
}
