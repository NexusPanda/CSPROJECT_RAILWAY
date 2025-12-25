package com.irctc.dao;

import com.irctc.model.Train;
import java.util.List;

public interface TrainDAO {
    List<Train> searchTrains(String source, String destination);
    void reduceSeat(int trainId);
}
