import java.time.LocalDate;
import java.util.*;


public class Main {

    public static LocalDate currentDate = LocalDate.now();

    public static void main(String[] args) throws TooManyThingsException {
        Scanner scanner = new Scanner(System.in);
        int command;

        Person p1 = new Person("Marianna", "Adamska", "08081354478", "Warsaw");
        Person p2 = new Person("Jan", "Kowalski", "502123456", "Kraków");
        Person p3 = new Person("Anna", "Nowak", "503234567", "Gdańsk");
        Person p4 = new Person("Piotr", "Wiśniewski", "504345678", "Wrocław");
        Person p5 = new Person("Ewa", "Dąbrowska", "505456789", "Poznań");
        Person p6 = new Person("Ewa", "Kotowska", "505456788", "Poznań");

        OffRoadCar orc = new OffRoadCar("Toyota Land Cruiser", 10, 2.0, "Diesel", false);
        CityCar cc = new CityCar("Mercedes-Benz C-Class", 8, 2.0, "Diesel", "Sedan");
        Boat b = new Boat("MasterCraft", 13, 8, "Petrol", 4);
        Motorcycle moto = new Motorcycle("Harley Davidson", 6, 3.0, "Petrol", false);
        Amphibious amp = new Amphibious("Argo Frontier", 20, 2.0, "Diesel", 4);

        ParkingSpace ps1 = new ParkingSpace(50, p1, LocalDate.parse("2023-11-11"), LocalDate.parse("2024-11-11"));
        ParkingSpace ps2 = new ParkingSpace(60, p2, LocalDate.parse("2023-11-13"), LocalDate.parse("2024-11-11"));
        ParkingSpace ps3 = new ParkingSpace(10, 7, 3);
        ParkingSpace ps4 = new ParkingSpace(10, 10, 3);
        ParkingSpace ps5 = new ParkingSpace(30);

        Apartment ap1 = new Apartment(50, 500, p1, LocalDate.parse("2023-11-11"), LocalDate.parse("2024-11-11"), ps1);
        Apartment ap2 = new Apartment(100, 1000, p2, LocalDate.parse("2023-11-11"), LocalDate.parse("2024-11-11"), ps2);
        Apartment ap3 = new Apartment(75, 800);
        Apartment ap4 = new Apartment(200, 2500);
        Apartment ap5 = new Apartment(60, 550);
        Apartment ap6 = new Apartment(68, 650);
        Apartment ap7 = new Apartment(57, 750);
        Apartment ap8 = new Apartment(45, 850);
        Apartment ap9 = new Apartment(40, 950);
        Apartment ap10 = new Apartment(30, 250);

        p1.setRentedSpaces(ap1);
        p2.setRentedSpaces(ap2);
        p1.setRentedSpaces(ps1);
        p2.setRentedSpaces(ps2);

        ps1.insertContent(p1, orc);
        ps1.insertContent(p1, cc);
        ps2.insertContent(p2, b);
        ps2.insertContent(p2, moto);
        ps2.insertContent(p2, amp);

        ap3.startRent(p3, LocalDate.parse("2023-11-11"), LocalDate.parse("2024-11-11"));
        ap4.startRent(p4, LocalDate.parse("2023-11-11"), LocalDate.parse("2024-11-11"));

        ap1.checkIn(p1, Arrays.asList(p1));
        ap2.checkIn(p2, Arrays.asList(p2));
        ap3.checkIn(p3, Arrays.asList(p3));
        ap4.checkIn(p4, Arrays.asList(p4));

        do {
            Menu.printMenuOptions();
            command = scanner.nextInt();

            switch (command) {
                case 1 -> Menu.printAllSpaces();
                case 2 -> Menu.printRentedRooms();
                case 3 -> Menu.printFreeRooms();
                case 4 -> Menu.printAllPeople();
                case 5 -> Menu.rentSpace();
                case 6 -> Menu.cancelRoomRent();
                case 7 -> Menu.renewRoomRent();
                case 8 -> Menu.printResidents();
                case 9 -> Menu.printContent();
                case 10 -> Menu.checkIn();
                case 11 -> Menu.checkOut();
                case 12 -> Menu.evictPeople();
                case 13 -> Menu.takeOutItem();
                case 14 -> Menu.utilize();
                case 15 -> Menu.createObject();
                case 16 -> Menu.insertItem();
                default -> System.out.println("Invalid command.");
            }
        } while (command != 17);
    }
}
