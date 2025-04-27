package model;

import java.util.*;

public class TicketModel {
    private static final int MainBerth = 3;
    private static final int MainRac = 6;
    private static final int MainWaitingList = 10;

    private static int availableBerth = MainBerth;
    private static int availableRac = MainRac;
    private static int availableWaitingList = MainWaitingList;

    private static List<TicketModel> berth = new ArrayList<>();
    private static Queue<TicketModel> rac = new LinkedList<>();
    private static Queue<TicketModel> waitingList = new LinkedList<>();

    private String name;
    private int age;
    private char preference;

    public TicketModel(String name, int age, char preference) {
        this.name = name;
        this.age = age;
        this.preference = preference;
    }

    public static boolean bookTicket(String name, int age, char preference) {
        TicketModel user = new TicketModel(name, age, preference);
        
        if (availableBerth > 0) {
            berth.add(user);
            availableBerth--;
            System.out.println("Ticket Confirmed for " + name);
            return true;
        } else if (availableRac > 0) {
            rac.add(user);
            availableRac--;
            System.out.println("Ticket added to RAC for " + name);
            return true;
        } else if (availableWaitingList > 0) {
            waitingList.add(user);
            availableWaitingList--;
            System.out.println("Ticket added to Waiting List for " + name);
            return true;
        } else {
            System.out.println("No Tickets Available for " + name);
            return false;
        }
    }

    public static void showAvailableTickets() {
        System.out.println("Available Berth: " + availableBerth);
        System.out.println("Available RAC: " + availableRac);
        System.out.println("Available Waiting List: " + availableWaitingList);
    }
}
