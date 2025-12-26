package com.irctc.service;

import com.irctc.dao.TicketDAO;
import com.irctc.dao.impl.TicketDAOImpl;

public class CancellationService {

    private TicketDAO ticketDAO = new TicketDAOImpl();

    public void cancelTicket(int pnr) {

        String cancelledStatus = ticketDAO.getTicketStatus(pnr);
        int trainId = ticketDAO.getTrainIdByPNR(pnr);

        if (cancelledStatus == null) {
            System.out.println("Invalid PNR");
            return;
        }

        ticketDAO.deleteTicket(pnr);

        if (cancelledStatus.equals("CONFIRMED")) {

            ticketDAO.incrementSeat(trainId, "berth");

            Integer racPNR = ticketDAO.getNextTicket(trainId, "RAC");
            if (racPNR != null) {
                ticketDAO.updateTicketStatus(racPNR, "CONFIRMED");

                Integer wlPNR = ticketDAO.getNextTicket(trainId, "WAITING");
                if (wlPNR != null) {
                    ticketDAO.updateTicketStatus(wlPNR, "RAC");
                }
            }

        } else if (cancelledStatus.equals("RAC")) {

            ticketDAO.incrementSeat(trainId, "rac");

            Integer wlPNR = ticketDAO.getNextTicket(trainId, "WAITING");
            if (wlPNR != null) {
                ticketDAO.updateTicketStatus(wlPNR, "RAC");
            }

        } else {
            ticketDAO.incrementSeat(trainId, "wl");
        }

        System.out.println("Ticket cancelled successfully");
    }
}
