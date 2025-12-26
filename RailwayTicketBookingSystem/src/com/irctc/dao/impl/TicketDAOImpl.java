package com.irctc.dao.impl;

import com.irctc.dao.TicketDAO;
import com.irctc.util.DBConnection;
import java.sql.*;

public class TicketDAOImpl implements TicketDAO {

    public void bookTicket(int uid, int tid, String status) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO tickets(user_id,train_id,status) VALUES(?,?,?)"
            );
            ps.setInt(1, uid);
            ps.setInt(2, tid);
            ps.setString(3, status);
            ps.executeUpdate();
        } catch (Exception e) {}
    }

    public int cancelTicket(int pnr) {
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement()
                    .executeQuery("SELECT train_id FROM tickets WHERE pnr=" + pnr);
            rs.next();
            con.createStatement().executeUpdate(
                    "DELETE FROM tickets WHERE pnr=" + pnr
            );
            return rs.getInt(1);
        } catch (Exception e) {
            return -1;
        }
    }

    public int getNextRAC(int tid) {
        return getNext("RAC", tid);
    }

    public int getNextWL(int tid) {
        return getNext("WAITING", tid);
    }

    private int getNext(String status, int tid) {
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT pnr FROM tickets WHERE train_id=" + tid +
                            " AND status='" + status + "' ORDER BY pnr LIMIT 1"
            );
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {}
        return -1;
    }

    public void updateStatus(int pnr, String status) {
        try (Connection con = DBConnection.getConnection()) {
            con.createStatement().executeUpdate(
                    "UPDATE tickets SET status='" + status + "' WHERE pnr=" + pnr
            );
        } catch (Exception e) {}
    }
}
