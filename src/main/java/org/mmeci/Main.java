package org.mmeci;

import jakarta.persistence.EntityManager;
import org.mmeci.config.HibernateConfiguration;
import org.mmeci.entity.*;
import org.mmeci.service.BookingService;
import org.mmeci.service.ClientService;
import org.mmeci.service.HotelService;
import org.mmeci.service.RoomService;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = HibernateConfiguration.getSessionFactory().createEntityManager();

        BookingService bookingService = new BookingService(entityManager);
        ClientService clientService = new ClientService(entityManager);
        HotelService hotelService = new HotelService(entityManager);
        RoomService roomService = new RoomService(entityManager);

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

                        case 4:
                            deleteClientById(scanner, clientService);
                            break;

                    }
                    break;

                case 2:
                    printHotelMenu();
                    int hotelChoice = scanner.nextInt();
                    switch (hotelChoice) {
                        case 1:
                            addHotel(scanner, hotelService);
                            break;
                        case 2:
                            getHotelByName(scanner, hotelService);
                            break;
                        case 3:
                            getHotelByLocation(scanner, hotelService);
                            break;
                        case 4:
                            getAllHotels(hotelService);
                            break;
                        case 5:
                            break;
                    }
                    break;

                case 3:
                    printRoomMenu();
                    int roomChoice = scanner.nextInt();
                    switch (roomChoice) {
                        case 1:
                            addRoom(scanner, hotelService, roomService);
                            break;
                        case 2:
                            getAvailableRoom(roomService);
                            break;
                        case 3:
                            getTypeOfRoom(scanner, roomService);
                            break;
                    }
                    break;
                case 4:
                    printBookingMenu();
                    int bookingMenuOption = scanner.nextInt();
                    switch (bookingMenuOption) {
                        case 1:
                            addBooking(scanner,bookingService);
                            break;
                        case 2:
                            findBookingById(scanner,bookingService);
                            break;
                        case 3:
                            getAllBookings(scanner,bookingService);
                            break;
                        case 4:
                            deleteBookingById(scanner,bookingService);
                            break;

                    }

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
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
        System.out.println("4.Delete Client by ID:");
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
        if (!isValidLong(clientID)) {
            System.out.println("This isn't a valid UUID format.");
            return;
        }
        Client client = clientService.getClientById(Long.valueOf(clientID));
        System.out.println(client);
    }

    private static void deleteClientById(Scanner scanner, ClientService clientService) {
        System.out.println("Please Enter Client id: ");
        String clientID = scanner.next();

        if (!isValidLong(clientID)) {
            System.out.println("This isn't a valid UUID format.");
            return;
        }

        clientService.deleteClientById(Long.valueOf(clientID));
        System.out.println("Client deleted");
    }

    private static boolean isValidLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void getAllClients(ClientService clientService){
        List<Client> clients = clientService.getAllClients();
        clients.forEach(System.out::println);
    }

    private static void printHotelMenu() {
        System.out.println("Choose operation:");
        System.out.println("1.Add Hotel");
        System.out.println("2.Find Hotel by Name");
        System.out.println("3.Find Hotels by Location");
        System.out.println("4.Get All Hotels");
        System.out.println("5.Exit");
    }

    private static void addHotel(Scanner scanner, HotelService hotelService) {
        System.out.println("Enter Hotel name:");
        scanner.nextLine();
        String hotelName = scanner.nextLine();

        System.out.println("Enter Hotel location:");
        String hotelLocation = scanner.nextLine();

        Hotel hotel = new Hotel(hotelName, hotelLocation);
        hotelService.addHotel(hotel);

        System.out.println("Hotel added successfully!");
    }

    private static void getHotelByName(Scanner scanner, HotelService hotelService) {
        System.out.println("Enter Hotel name: ");
        scanner.nextLine();
        String hotelName = scanner.nextLine();

        List<Hotel> hotels = hotelService.getHotelByName(hotelName);
        if (hotels.isEmpty()) {
            System.out.println("I couldn't find any hotels with the name: " + hotelName);
        } else {
            System.out.println("Found the following hotels with the name '" + hotelName + "':");
            hotels.forEach(System.out::println);
        }
    }

    private static void getHotelByLocation(Scanner scanner, HotelService hotelService) {
        System.out.println("Enter Hotel location: ");
        String hotelLocation = scanner.nextLine();

        List<Hotel> hotels = hotelService.getHotelByLocation(hotelLocation);
        if (hotels.isEmpty()) {
            System.out.println("I couldn't find any hotels in the location: " + hotelLocation);
        } else {
            System.out.println("Found the following hotels in '" + hotelLocation + "':");
            hotels.forEach(System.out::println);
        }
    }

    private static void getAllHotels(HotelService hotelService) {
        List<Hotel> hotels = hotelService.getAllHotels();
        if (hotels.isEmpty()) {
            System.out.println("No hotels available.");
        } else {
            hotels.forEach(System.out::println);
        }
    }

    private static void printRoomMenu() {
        System.out.println("Choose operation:");
        System.out.println("1.Add Room");
        System.out.println("2.Get Available Room");
        System.out.println("3.Get type of Room");
        System.out.println("4.Exit");
    }
    private static void addRoom(Scanner scanner, HotelService hotelService, RoomService roomService) {
        System.out.println("Enter Room type (e.g., SINGLE, DOUBLE, SUITE):");
        String type = scanner.next();


        TypeOfRoom roomType = TypeOfRoom.valueOf(type.toUpperCase());

        System.out.println("Enter Room price:");
        double price = scanner.nextDouble();

        System.out.println("Is the Room available? (true/false):");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        System.out.println("Enter Hotel ID for this room:");
        int hotelId = scanner.nextInt();
        scanner.nextLine();

    }


    private static void getAvailableRoom(RoomService roomService) {
        System.out.println("Available Rooms:");
        List<Room> availableRooms = roomService.getAvailableRooms();
        for (Room room : availableRooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
        System.out.println();
    }

    private static void getTypeOfRoom(Scanner scanner, RoomService roomService) {
        System.out.println("Enter Room type:");
        String type = scanner.nextLine();

        System.out.println("Rooms of type " + type + ":");
        List<Room> roomsByType = roomService.getRoomsByType(type);
        for (Room room : roomsByType) {
            System.out.println(room);
        }
        System.out.println();
    }



    private static void printBookingMenu() {
        System.out.println("Choose operation:");
        System.out.println("1.Save Booking");
        System.out.println("2.Find Booking by ID");
        System.out.println("3.Get All Bookings");
        System.out.println("4.Delete Booking by ID");
        System.out.println("5.Exit");
    }
    private static void deleteBookingById(Scanner scanner, BookingService bookingService) {
        System.out.println("Enter Booking ID for deletion");
        int bookingId = scanner.nextInt();
        bookingService.deleteBooking(bookingId);
        System.out.println("Booking deleted successfully");


    }

    private static void getAllBookings(Scanner scanner, BookingService bookingService) {
        List<Booking> bookings = bookingService.getAllBookings();
        bookings.forEach(System.out::println);
    }

    private static void findBookingById(Scanner scanner, BookingService bookingService) {
        System.out.println("Enter Booking ID");
        Long bookingId = scanner.nextLong();
        Booking booking = bookingService.findBookingById(bookingId);
        System.out.println(booking);


    }

    private static void addBooking(Scanner scanner, BookingService bookingService) {
        System.out.println("Enter Client ID");
        Long clientId = scanner.nextLong();

        System.out.println("Enter Hotel ID");
        Long hotelId = scanner.nextLong();

        System.out.println("Enter Room ID");
        Long roomId = scanner.nextLong();

        System.out.println("Enter Check-in date");
        int checkIn = scanner.nextInt();

        System.out.println("Enter Check-out date");
        int checkOut = scanner.nextInt();

        System.out.println("Enter number of persons");
        int numberOfPersons = scanner.nextInt();

        System.out.println("Enter type of rooms");
        String roomType = scanner.next();

        System.out.println("Enter number of rooms");
        int numberOfRooms = scanner.nextInt();

        System.out.println("Choose Payment method");
        String paymentMethod = scanner.next();


        bookingService.saveBooking(clientId,roomId,roomType,hotelId,numberOfPersons,numberOfRooms,checkIn
                ,checkOut,paymentMethod);

        System.out.println("Booking successful");


    }
}