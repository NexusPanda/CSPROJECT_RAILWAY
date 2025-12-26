package com.irctc.service;

import com.irctc.dao.TicketDAO;
import com.irctc.dao.TrainDAO;
import com.irctc.dao.impl.TicketDAOImpl;
import com.irctc.dao.impl.TrainDAOImpl;
import com.irctc.model.Ticket;
import com.irctc.model.Train;

public class BookingService {

    private TicketDAO ticketDAO = new TicketDAOImpl();
    private TrainDAO trainDAO = new TrainDAOImpl();

    public void bookTicket(int userId, int trainId) {

        // Check if chart is prepared
        if (ticketDAO.isChartPrepared(trainId)) {
            System.out.println("Chart already prepared. Booking closed.");
            return;
        }

        Train train = trainDAO.getTrainById(trainId);

        if (train == null) {
            System.out.println("Invalid Train ID");
            return;
        }

        if (train.getBerth() > 0) {
            ticketDAO.updateSeatCount(trainId, "berth");
            ticketDAO.bookTicket(new Ticket(userId, trainId, "CONFIRMED"));
            System.out.println("✅ Ticket CONFIRMED");

        } else if (train.getRac() > 0) {
            ticketDAO.updateSeatCount(trainId, "rac");
            ticketDAO.bookTicket(new Ticket(userId, trainId, "RAC"));
            System.out.println("🟡 Ticket in RAC");

        } else if (train.getWl() > 0) {
            ticketDAO.updateSeatCount(trainId, "wl");
            ticketDAO.bookTicket(new Ticket(userId, trainId, "WAITING"));
            System.out.println("🔴 Ticket in WAITING LIST");

        } else {
            System.out.println("❌ No seats available");
        }
    }
}
