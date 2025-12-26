package com.irctc.dao.impl;


import com.irctc.dao.TicketDAO;
import com.irctc.model.Ticket;
import com.irctc.util.DBConnection;

import java.sql.*;

public class TicketDAOImpl implements TicketDAO {

    @Override
    public int getTrainIdByPNR(int pnr) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT train_id FROM tickets WHERE pnr = ?");
            ps.setInt(1, pnr);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {}
        return -1;
    }

    @Override
    public String getTicketStatus(int pnr) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT status FROM tickets WHERE pnr = ?");
            ps.setInt(1, pnr);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString(1);
        } catch (Exception e) {}
        return null;
    }

    @Override
    public boolean deleteTicket(int pnr) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM tickets WHERE pnr = ?");
            ps.setInt(1, pnr);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {}
        return false;
    }

    @Override
    public Integer getNextTicket(int trainId, String status) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT pnr FROM tickets WHERE train_id=? AND status=? ORDER BY booked_at LIMIT 1");
            ps.setInt(1, trainId);
            ps.setString(2, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {}
        return null;
    }

    @Override
    public void updateTicketStatus(int pnr, String newStatus) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE tickets SET status=? WHERE pnr=?");
            ps.setString(1, newStatus);
            ps.setInt(2, pnr);
            ps.executeUpdate();
        } catch (Exception e) {}
    }

    @Override
    public void incrementSeat(int trainId, String column) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE trains SET " + column + " = " + column + " + 1 WHERE id=?");
            ps.setInt(1, trainId);
            ps.executeUpdate();
        } catch (Exception e) {}
    }

    @Override
    public boolean isChartPrepared(int trainId) {
        return false;
    }

    @Override
    public void updateSeatCount(int trainId, String berth) {

    }

    @Override
    public void bookTicket(Ticket confirmed) {

    }
}
