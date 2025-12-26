package com.irctc.service;


import com.irctc.dao.TrainDAO;
import com.irctc.dao.impl.TrainDAOImpl;
import com.irctc.model.Train;

import java.util.List;

public class TrainService {

    private TrainDAO trainDAO = new TrainDAOImpl();

    public List<Train> search(String source, String destination) {
        return trainDAO.searchTrains(source, destination);
    }
}
