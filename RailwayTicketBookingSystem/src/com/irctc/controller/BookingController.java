package com.irctc.controller;

import com.irctc.model.Train;
import com.irctc.service.BookingService;
import com.irctc.view.ConsoleView;

import java.util.List;

public class BookingController {

    private BookingService service = new BookingService();

    public void start() {

        String source = ConsoleView.input("From: ");
        String destination = ConsoleView.input("To: ");

        List<Train> trains = service.searchTrains(source, destination);

        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }

        ConsoleView.showTrains(trains);

        int trainId = ConsoleView.inputInt("Enter Train ID to book: ");

        for (Train t : trains) {
            if (t.getId() == trainId) {
                service.bookTicket(trainId, t.getAvailableSeats());
                return;
            }
        }

        System.out.println("Invalid Train ID");
    }
}
