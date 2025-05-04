package controller;

import model.DatabaseModel;
import model.TicketModel;
import model.UserModel;
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
                    String adminEmail = MainView.getUserInput("Enter the Email: ");
                    String adminPassword = MainView.getUserInput("Enter the Password: ");
                    if(DatabaseModel.adminUser(adminEmail, adminPassword)) {
                        adminMenu();
                    }
                    else{
                        MainView.showMessage("Invalid Admin credentials!");
                    }
                    break;

                case 4:
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

    private static void adminMenu() {
        while (true) {
            int choice = MainView.getIntInput(
                    "\n===== Admin Panel =====\n"
                            + "1. Add New Train\n"
                            + "2. View All Trains\n"
                            + "3. Update Train Details\n"
                            + "4. Delete Train\n"
                            + "5. View All Bookings\n"
                            + "6. Search Booking by PNR\n"
                            + "7. Cancel a Booking\n"
                            + "8. View All Users\n"
                            + "9. View User's Bookings\n"
                            + "10. Delete User\n"
                            + "11. Logout\n"
                            + "Enter your choice: "
            );

            switch (choice) {
                case 1:
                    // Call model method to add a train
                    break;

                case 2:
                    // Call method to view all trains
                    break;

                case 3:
                    // Call method to update train details
                    break;

                case 4:
                    // Call method to delete a train
                    break;

                case 5:
                    // Call method to view all bookings
                    break;

                case 6:
                    // Call method to search booking by PNR
                    break;

                case 7:
                    // Cancel a booking
                    break;

                case 8:
                    // View all users
                    break;

                case 9:
                    // View bookings of a specific user
                    break;

                case 10:
                    // Delete a user
                    break;

                case 11:
                    MainView.showMessage("Admin Logged Out!");
                    return;

                default:
                    MainView.showMessage("Invalid choice.");
            }
        }
    }

}
