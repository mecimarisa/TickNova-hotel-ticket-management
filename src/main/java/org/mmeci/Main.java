package org.mmeci;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.mmeci.config.HibernateConfiguration;
import org.mmeci.service.BookingService;
import org.mmeci.service.ClientService;
import org.mmeci.service.HotelService;
import org.mmeci.service.RoomService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = HibernateConfiguration.getSessionFactory().createEntityManager();

        BookingService bookingService = new BookingService(entityManager);
        ClientService clientService= new ClientService(entityManager);
        HotelService hotelService = new HotelService(entityManager);
       // RoomService roomService = new RoomService(entityManager);

        boolean continueLoop = true;
        while(continueLoop){

            printFirstMenu();
            int firstMenuOption = scanner.nextInt();

            switch (firstMenuOption){
                case 1:
                    printClientMenu();
                    int clientMenuOption = scanner.nextInt();
                    switch (clientMenuOption){

                    }
            }
        }

    }

    private static void printFirstMenu() {
        System.out.println("WELCOME TO TICKNOVA");
        System.out.println("Please choose your options:");
        System.out.println("1.Client OPERATIONS");
        System.out.println("2.Hotel OPERATIONS");
        System.out.println("3.Room OPERATIONS");
        System.out.println("4.Booking OPERATIONS");
        System.out.println("5.Exit");

    }

    private static void printClientMenu(){
        System.out.println("Choose operation:");
        System.out.println("1.Add Client");
        System.out.println("2.Get Client by ID:");
        System.out.println("3.Get All Clients:");
    }
}