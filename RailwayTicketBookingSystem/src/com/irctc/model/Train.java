package com.irctc.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Train {
    private int id;
    private String name;
    private String source;
    private String destination;
    private LocalDate travelDate;
    private LocalTime departureTime;
    private int availableSeats;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
