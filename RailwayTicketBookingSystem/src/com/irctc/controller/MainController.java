package com.irctc.controller;

import com.irctc.model.Train;
import com.irctc.service.AuthService;
import com.irctc.service.CancellationService;
import com.irctc.service.TrainService;

import java.util.List;
import java.util.Scanner;

public class MainController {

    private static Scanner sc = new Scanner(System.in);
    private static TrainService trainService = new TrainService();
    private static CancellationService cancelService = new CancellationService();

    public static void start() {
        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();

        System.out.println("1 Register\n2 Login");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Password: ");
            String pass = sc.next();
            auth.register("User", email, pass);
            System.out.println("Registered!");
        }

        if (choice == 2) {
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Password: ");
            String pass = sc.next();

            int userId = auth.login(email, pass);
            if (userId > 0)
                System.out.println("Login success");
            else
                System.out.println("Invalid login");
        }
    }


    public static void trainSearch() {

        System.out.print("Enter Source: ");
        String source = sc.next();

        System.out.print("Enter Destination: ");
        String destination = sc.next();

        List<Train> trains = trainService.search(source, destination);

        if (trains.isEmpty()) {
            System.out.println("No trains available");
            return;
        }

        System.out.println("\nAvailable Trains:");
        for (Train t : trains) {
            System.out.println(
                    "Train ID: " + t.getId() +
                            " | Name: " + t.getName() +
                            " | Date: " + t.getTravelDate() +
                            " | Time: " + t.getDepartureTime()
            );
        }
    }

    public static void cancelTicket() {
        System.out.print("Enter PNR to cancel: ");
        int pnr = sc.nextInt();
        cancelService.cancelTicket(pnr);
    }
}
