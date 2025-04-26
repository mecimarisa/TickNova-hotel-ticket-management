package org.mmeci;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.mmeci.config.HibernateConfiguration;
import org.mmeci.entity.Client;
import org.mmeci.service.BookingService;
import org.mmeci.service.ClientService;
import org.mmeci.service.HotelService;
import org.mmeci.service.RoomService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = HibernateConfiguration.getSessionFactory().createEntityManager();

        BookingService bookingService = new BookingService(entityManager);
        ClientService clientService = new ClientService(entityManager);
        HotelService hotelService = new HotelService(entityManager);
       // RoomService roomService = new RoomService(entityManager);

        boolean continueLoop = true;
        while (continueLoop) {

            printFirstMenu();
            int firstMenuOption = scanner.nextInt();

            switch (firstMenuOption) {
                case 1:
                    printClientMenu();
                    int clientMenuOption = scanner.nextInt();
                    switch (clientMenuOption) {
                        case 1:
                            addClient(scanner, clientService);
                            break;

                        case 2:
                            getClientById(scanner, clientService);
                            break;

                        case 3:
                            getAllClients(clientService);
                            break;


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

    private static void printClientMenu() {
        System.out.println("Choose operation:");
        System.out.println("1.Add Client");
        System.out.println("2.Get Client by ID:");
        System.out.println("3.Get All Clients:");
        System.out.println("4.Exit");
    }

    private static void addClient(Scanner scanner, ClientService clientService) {
        System.out.println("Enter Client name: ");
        String clientName = scanner.next();

        System.out.println("Enter Client last name: ");
        String clientLastName = scanner.next();

        System.out.println("Enter Client's Email: ");
        String clientEmail = scanner.next();

        clientService.addClient(clientName, clientLastName, clientEmail);

        System.out.println("Client added successfully");
    }

    private static void getClientById(Scanner scanner, ClientService clientService) {
        System.out.println("Enter Client ID: ");
        String clientID = scanner.next();
        if (!isValidUUID(clientID)) {
            System.out.println("This isn't a valid UUID format.");
            return;
        }
        Client client = clientService.getClientById(UUID.fromString(clientID));
        System.out.println(client);
    }

    private static boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static void getAllClients(ClientService clientService){
        List<Client> clients = clientService.getAllClients();
        clients.forEach(System.out::println);
    }
}