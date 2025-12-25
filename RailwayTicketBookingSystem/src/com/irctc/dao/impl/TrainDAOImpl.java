package com.irctc.dao.impl;

import com.irctc.dao.TrainDAO;
import com.irctc.model.Train;
import com.irctc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainDAOImpl implements TrainDAO {

    public List<Train> searchTrains(String source, String destination) {

        List<Train> trains = new ArrayList<>();

        String query = """
            SELECT * FROM trains
            WHERE source=? AND destination=?
            AND (
                travel_date > CURRENT_DATE
                OR (travel_date = CURRENT_DATE AND departure_time > CURRENT_TIME)
            )
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, source);
            ps.setString(2, destination);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Train t = new Train();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setAvailableSeats(rs.getInt("available_seats"));
                trains.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return trains;
    }

    public void reduceSeat(int trainId) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE trains SET available_seats = available_seats - 1 WHERE id=?"
            );
            ps.setInt(1, trainId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
