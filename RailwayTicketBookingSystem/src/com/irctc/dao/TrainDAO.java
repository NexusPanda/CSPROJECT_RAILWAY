package com.irctc.dao;

import java.sql.ResultSet;

public interface TrainDAO {
    ResultSet searchTrain(String source, String destination);
    int[] getSeats(int trainId);
    void reduceSeat(int trainId, String type);
    boolean isChartPrepared(int trainId);
    void prepareChart(int trainId);
}
