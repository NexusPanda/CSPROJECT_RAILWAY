package com.irctc.dao;

public interface TicketDAO {
    void bookTicket(int userId, int trainId, String status);
    int cancelTicket(int pnr);
    int getNextRAC(int trainId);
    int getNextWL(int trainId);
    void updateStatus(int pnr, String status);
}
