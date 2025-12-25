package com.irctc.model;

public class Ticket {
    private int trainId;
    private String status;

    public Ticket(int trainId, String status) {
        this.trainId = trainId;
        this.status = status;
    }

    public int getTrainId() { return trainId; }
    public String getStatus() { return status; }
}
