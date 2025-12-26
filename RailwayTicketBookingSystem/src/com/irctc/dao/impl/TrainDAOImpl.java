package com.irctc.dao.impl;


import com.irctc.dao.TrainDAO;
import com.irctc.model.Train;
import com.irctc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainDAOImpl implements TrainDAO {

    @Override
    public List<Train> searchTrains(String source, String destination) {

        List<Train> trains = new ArrayList<>();

        String query = """
            SELECT * FROM trains
            WHERE source = ?
              AND destination = ?
              AND chart_prepared = false
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
                trains.add(new Train(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getDate("travel_date"),
                        rs.getTime("departure_time"),
                        rs.getInt("berth"),
                        rs.getInt("rac"),
                        rs.getInt("wl")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return trains;
    }

    @Override
    public Train getTrainById(int trainId) {

        String query = "SELECT * FROM trains WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, trainId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Train(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getDate("travel_date"),
                        rs.getTime("departure_time"),
                        rs.getInt("berth"),
                        rs.getInt("rac"),
                        rs.getInt("wl")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
