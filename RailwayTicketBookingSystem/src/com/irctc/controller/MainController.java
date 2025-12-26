package com.irctc.controller;

import com.irctc.service.BookingService;
import java.util.Scanner;

public class MainController {

    public static void main(String[] args) {

        BookingService service = new BookingService();
        Scanner sc = new Scanner(System.in);

        System.out.println("1.Book  2.Cancel  3.Chart");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> service.book(1, 1);
            case 2 -> service.cancel(sc.nextInt());
            case 3 -> service.prepareChart(sc.nextInt());
        }
    }
}
