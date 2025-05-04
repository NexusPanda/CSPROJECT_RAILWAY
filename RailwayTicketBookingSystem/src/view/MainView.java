package view;

import java.util.Scanner;

public class MainView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int showMainMenu() {
        System.out.println("\nWelcome to Railway Ticket Booking System!");
        System.out.println("1. Create User");
        System.out.println("2. Login User");
        System.out.println("3. Admin Login");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public static String getUserInput(String message) {
        System.out.print(message);
        return scanner.next();
    }

    public static int getIntInput(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }
}
