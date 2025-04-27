package controller;

import model.*;
import java.util.*;
import view.MainView;

public class MainController {
    public static void start() {
        while (true) {
            int choice = MainView.showMainMenu();

            switch (choice) {
                case 1:
                    String name = MainView.getUserInput("Enter Name: ");
                    String email = MainView.getUserInput("Enter Email: ");
                    String password = MainView.getUserInput("Enter Password: ");
                    
                    UserModel newUser = new UserModel(name, email, password);
                    if (DatabaseModel.createUser(newUser)) {
                        MainView.showMessage("User Registered Successfully!");
                    } else {
                        MainView.showMessage("User Registration Failed!");
                    }
                    break;

                case 2:
                    email = MainView.getUserInput("Enter Email: ");
                    password = MainView.getUserInput("Enter Password: ");
                    
                    if (DatabaseModel.loginUser(email, password)) {
                        ticketBookingMenu();
                    }
                    break;

                case 3:
                    MainView.showMessage("Exiting... Thank you!");
                    return;

                default:
                    MainView.showMessage("Invalid choice. Please try again.");
            }
        }
    }

    private static void ticketBookingMenu() {
        while (true) {
            int choice = MainView.getIntInput("\n1. Book Ticket\n2. View Available Tickets\n3. Logout\nEnter choice: ");

            switch (choice) {
                case 1:
                    String name = MainView.getUserInput("Enter Name: ");
                    int age = MainView.getIntInput("Enter Age: ");
                    char preference = MainView.getUserInput("Enter Preference (L/M/U): ").charAt(0);
                    
                    TicketModel.bookTicket(name, age, preference);
                    break;

                case 2:
                    TicketModel.showAvailableTickets();
                    break;

                case 3:
                    MainView.showMessage("Logged out!");
                    return;

                default:
                    MainView.showMessage("Invalid choice.");
            }
        }
    }
}
