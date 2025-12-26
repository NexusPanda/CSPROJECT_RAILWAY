package com.irctc.dao;

import com.irctc.model.Ticket;

public interface TicketDAO {

    int getTrainIdByPNR(int pnr);

    String getTicketStatus(int pnr);

    boolean deleteTicket(int pnr);

    Integer getNextTicket(int trainId, String status);

    void updateTicketStatus(int pnr, String newStatus);

    void incrementSeat(int trainId, String column);

    boolean isChartPrepared(int trainId);

    void updateSeatCount(int trainId, String berth);

    void bookTicket(Ticket confirmed);
}
