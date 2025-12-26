package com.irctc.model;

import java.sql.Timestamp;

public class Ticket {

    private int pnr;
    private int userId;
    private int trainId;
    private String status;
    private Timestamp bookedAt;

    public Ticket(int userId, int trainId, String status) {
        this.userId = userId;
        this.trainId = trainId;
        this.status = status;
    }

    public String getStatus() { return status; }
}
