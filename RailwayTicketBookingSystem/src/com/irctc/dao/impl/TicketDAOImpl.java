package com.irctc.dao.impl;

import com.irctc.dao.TicketDAO;
import com.irctc.model.Ticket;
import com.irctc.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TicketDAOImpl implements TicketDAO {

    public void bookTicket(Ticket ticket) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO tickets(train_id, status) VALUES(?, ?)"
            );
            ps.setInt(1, ticket.getTrainId());
            ps.setString(2, ticket.getStatus());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
