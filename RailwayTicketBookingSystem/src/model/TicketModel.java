package model;

import java.sql.*;

public class TicketModel {
    static final String URL = "jdbc:postgresql://localhost:5434/postgres";
    static final String USER = "postgres";
    static final String PASS = "JAVA";

    public static boolean bookTicket(String name, int age, char preference) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String fetchQuery = "SELECT * FROM SeatAvailability WHERE id = 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(fetchQuery);

            if (rs.next()) {
                int berth = rs.getInt("berth_available");
                int rac = rs.getInt("rac_available");
                int waiting = rs.getInt("waiting_list_available");

                String status = null;
                String updateQuery = null;

                if (berth > 0) {
                    status = "Confirmed";
                    updateQuery = "UPDATE SeatAvailability SET berth_available = berth_available - 1 WHERE id = 1";
                } else if (rac > 0) {
                    status = "RAC";
                    updateQuery = "UPDATE SeatAvailability SET rac_available = rac_available - 1 WHERE id = 1";
                } else if (waiting > 0) {
                    status = "Waiting";
                    updateQuery = "UPDATE SeatAvailability SET waiting_list_available = waiting_list_available - 1 WHERE id = 1";
                } else {
                    System.out.println("No Tickets Available for " + name);
                    return false;
                }

                stmt.executeUpdate(updateQuery);

                String insertQuery = "INSERT INTO Tickets (name, age, preference, status) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.setString(3, String.valueOf(preference));
                pstmt.setString(4, status);

                pstmt.executeUpdate();

                System.out.println("Ticket " + status + " for " + name);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void resetSeats() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String update = "UPDATE SeatAvailability SET berth_available = 3, rac_available = 6, waiting_list_available = 10 WHERE id = 1";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(update);

            String deleteTickets = "DELETE FROM Tickets";
            stmt.executeUpdate(deleteTickets);

            System.out.println("Seat counts reset and all tickets cleared!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAllBookedTickets() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String query = "SELECT * FROM Tickets";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n--- All Booked Tickets ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Preference: " + rs.getString("preference") +
                        ", Status: " + rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAvailableTickets() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String query = "SELECT * FROM SeatAvailability WHERE id = 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Available Berth: " + rs.getInt("berth_available"));
                System.out.println("Available RAC: " + rs.getInt("rac_available"));
                System.out.println("Available Waiting List: " + rs.getInt("waiting_list_available"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
