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

        OffRoadCar orc = new OffRoadCar("Land Rover Defender", 10, 2.0, "Diesel", false);
        CityCar cc = new CityCar("Audi A6", 8, 2.0, "Diesel", "Combi");
        Boat b = new Boat("Caparral", 13, 8, "Petrol", 4);
        Motorcycle moto = new Motorcycle("Kawasaki", 6, 3.0, "Petrol", false);
        Amphibious amp = new Amphibious("Sherp N", 20, 2.0, "Diesel", 4);

        ParkingSpace ps1 = new ParkingSpace(50, p1, LocalDate.parse("2023-11-11"), LocalDate.parse("2021-06-05"));
        p1.setRentedRooms(ps1);
        ParkingSpace ps2 = new ParkingSpace(60, p2, LocalDate.parse("2021-01-03"), LocalDate.parse("2021-03-04"));
        p2.setRentedRooms(ps2);
        ParkingSpace ps3 = new ParkingSpace(10, 7, 3);
        ParkingSpace ps4 = new ParkingSpace(10, 10, 3);
        ParkingSpace ps5 = new ParkingSpace(30);

        ps1.insertContent(p1, orc);
        ps1.insertContent(p1, cc);
        ps2.insertContent(p2, b);
        ps2.insertContent(p2, moto);
        ps2.insertContent(p2, amp);

        Apartment ap1 = new Apartment(50, 500, p1, LocalDate.parse("2021-01-10"), LocalDate.parse("2021-06-05"), ps1);
        p1.setRentedRooms(ap1);
        Apartment ap2 = new Apartment(100, 1000, p2, LocalDate.parse("2021-01-03"), LocalDate.parse("2021-03-04"), ps2);
        p2.setRentedRooms(ap2);
        Apartment ap3 = new Apartment(75, 800);
        ap3.startRent(p3, LocalDate.parse("2021-01-02"), LocalDate.parse("2021-03-03"));
        Apartment ap4 = new Apartment(200, 2500);
        ap4.startRent(p3, LocalDate.parse("2021-02-15"), LocalDate.parse("2021-04-15"));
        Apartment ap5 = new Apartment(60, 750);
        Apartment ap6 = new Apartment(60, 750);

        ap1.checkIn(p1, Arrays.asList(p1, p2, p3));

        StringBuilder str = new StringBuilder("Apartment");

        TenantLetter l1 = new TenantLetter(ap1, str);
        TenantLetter l2 = new TenantLetter(ap1, str);
        TenantLetter l3 = new TenantLetter(ap1, str);

        p1.setLetters(l1);
        p1.setLetters(l2);
        p1.setLetters(l3);

        do {
            Menu.printMenuOptions();
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    Menu.printAllRooms();
                    break;
                case 2:
                    Menu.printRentedRooms();
                    break;
                case 3:
                    Menu.printFreeRooms();
                    break;
                case 4:
                    Menu.printAllPeople();
                    break;
                case 5:
                    Menu.rentRoom();
                    break;
                case 6:
                    Menu.cancelRoomRent();
                    break;
                case 7:
                    Menu.renewRoomRent();
                    break;
                case 8:
                    Menu.printResidents();
                    break;
                case 9:
                    Menu.printContent();
                    break;
                case 10:
                    Menu.checkIn();
                    break;
                case 11:
                    Menu.checkOut();
                    break;
                case 12:
                    Menu.evictPeople();
                    break;
                case 13:
                    Menu.takeOutItem();
                    break;
                case 14:
                    Menu.utilize();
                    break;
                case 15:
                    Menu.printTenantLetters();
                    break;
                case 16:
                    Menu.createObject();
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 17);
    }
}
