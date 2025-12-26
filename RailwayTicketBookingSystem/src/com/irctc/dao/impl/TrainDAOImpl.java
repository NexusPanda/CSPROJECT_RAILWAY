package com.irctc.dao.impl;

import com.irctc.dao.TrainDAO;
import com.irctc.util.DBConnection;
import java.sql.*;

public class TrainDAOImpl implements TrainDAO {

    public ResultSet searchTrain(String s, String d) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("""
                SELECT * FROM trains
                WHERE source=? AND destination=?
                AND chart_prepared=false
                AND (travel_date > CURRENT_DATE OR
                (travel_date=CURRENT_DATE AND departure_time>CURRENT_TIME))
            """);
            ps.setString(1, s);
            ps.setString(2, d);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }

    public int[] getSeats(int id) {
        int[] seats = new int[3];
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement()
                    .executeQuery("SELECT berth,rac,wl FROM trains WHERE id=" + id);
            if (rs.next()) {
                seats[0] = rs.getInt(1);
                seats[1] = rs.getInt(2);
                seats[2] = rs.getInt(3);
            }
        } catch (Exception e) {}
        return seats;
    }

    public void reduceSeat(int id, String type) {
        try (Connection con = DBConnection.getConnection()) {
            con.createStatement()
                    .executeUpdate("UPDATE trains SET " + type + "=" + type + "-1 WHERE id=" + id);
        } catch (Exception e) {}
    }

    public boolean isChartPrepared(int id) {
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement()
                    .executeQuery("SELECT chart_prepared FROM trains WHERE id=" + id);
            rs.next();
            return rs.getBoolean(1);
        } catch (Exception e) {
            return true;
        }
    }

    public void prepareChart(int id) {
        try (Connection con = DBConnection.getConnection()) {
            con.createStatement()
                    .executeUpdate("UPDATE trains SET chart_prepared=true WHERE id=" + id);
        } catch (Exception e) {}
    }
}
