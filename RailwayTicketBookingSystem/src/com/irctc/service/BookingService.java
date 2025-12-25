package com.irctc.service;

import com.irctc.dao.TrainDAO;
import com.irctc.dao.TicketDAO;
import com.irctc.dao.impl.TrainDAOImpl;
import com.irctc.dao.impl.TicketDAOImpl;
import com.irctc.model.Ticket;
import com.irctc.model.Train;

import java.util.List;

public class BookingService {

    private TrainDAO trainDAO = new TrainDAOImpl();
    private TicketDAO ticketDAO = new TicketDAOImpl();

    public List<Train> searchTrains(String source, String destination) {
        return trainDAO.searchTrains(source, destination);
    }

    public void bookTicket(int trainId, int availableSeats) {

        String status = availableSeats > 0 ? "CONFIRMED" : "WAITING";

        if (availableSeats > 0) {
            trainDAO.reduceSeat(trainId);
        }

        ticketDAO.bookTicket(new Ticket(trainId, status));

        System.out.println("Ticket Booked: " + status);
    }
}
