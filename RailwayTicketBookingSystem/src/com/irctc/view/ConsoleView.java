package com.irctc.view;

import com.irctc.model.Train;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private static Scanner sc = new Scanner(System.in);

    public static String input(String msg) {
        System.out.print(msg);
        return sc.next();
    }

    public static int inputInt(String msg) {
        System.out.print(msg);
        return sc.nextInt();
    }

    public static void showTrains(List<Train> trains) {
        System.out.println("\nAvailable Trains:");
        for (Train t : trains) {
            System.out.println(
                "Train ID: " + t.getId() +
                ", Name: " + t.getName() +
                ", Seats: " + t.getAvailableSeats()
            );
        }
    }
}
