package com.irctc.model;

import java.sql.Date;
import java.sql.Time;

public class Train {
    private int id;
    private String name;
    private String source;
    private String destination;
    private Date travelDate;
    private Time departureTime;
    private int berth;
    private int rac;
    private int wl;

    public Train(int id, String name, String source, String destination,
                 Date travelDate, Time departureTime,
                 int berth, int rac, int wl) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.travelDate = travelDate;
        this.departureTime = departureTime;
        this.berth = berth;
        this.rac = rac;
        this.wl = wl;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Date getTravelDate() { return travelDate; }
    public Time getDepartureTime() { return departureTime; }

    public int getBerth() { return berth; }
    public int getRac() { return rac; }
    public int getWl() { return wl; }
}
