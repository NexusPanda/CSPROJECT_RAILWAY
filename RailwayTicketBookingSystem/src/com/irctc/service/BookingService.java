package com.irctc.service;

import com.irctc.dao.*;
import com.irctc.dao.impl.*;

public class BookingService {

    TrainDAO trainDAO = new TrainDAOImpl();
    TicketDAO ticketDAO = new TicketDAOImpl();

    public void book(int userId, int trainId) {

        if (trainDAO.isChartPrepared(trainId)) {
            System.out.println("Chart prepared. Booking closed.");
            return;
        }

        int[] seats = trainDAO.getSeats(trainId);

        if (seats[0] > 0) {
            trainDAO.reduceSeat(trainId, "berth");
            ticketDAO.bookTicket(userId, trainId, "CONFIRMED");
            System.out.println("Ticket CONFIRMED");
        }
        else if (seats[1] > 0) {
            trainDAO.reduceSeat(trainId, "rac");
            ticketDAO.bookTicket(userId, trainId, "RAC");
            System.out.println("Ticket RAC");
        }
        else if (seats[2] > 0) {
            trainDAO.reduceSeat(trainId, "wl");
            ticketDAO.bookTicket(userId, trainId, "WAITING");
            System.out.println("Ticket WAITING");
        }
        else {
            System.out.println("No seats available");
        }
    }

    public void cancel(int pnr) {
        int trainId = ticketDAO.cancelTicket(pnr);

        int rac = ticketDAO.getNextRAC(trainId);
        if (rac != -1) {
            ticketDAO.updateStatus(rac, "CONFIRMED");

            int wl = ticketDAO.getNextWL(trainId);
            if (wl != -1)
                ticketDAO.updateStatus(wl, "RAC");
        }

        System.out.println("Ticket Cancelled & Auto-Promoted");
    }

    public void prepareChart(int trainId) {
        trainDAO.prepareChart(trainId);
        System.out.println("Chart Prepared");
    }
}
