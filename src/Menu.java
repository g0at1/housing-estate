import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private final static Scanner scanner = new Scanner(System.in);
    private final static HousingEstate housingEstate = new HousingEstate("Atrium");

    public static void printMenuOptions() {
        System.out.println("Choose option:");
        System.out.println("1: Print all rooms of housing estate");
        System.out.println("2: Print rented rooms");
        System.out.println("3: Print available rooms");
        System.out.println("4: Print all people");
        System.out.println("5: Rent room");
        System.out.println("6: Cancel room rent");
        System.out.println("7: Renew room rent");
        System.out.println("8: Print residents of apartments");
        System.out.println("9: Print content of parking spaces");
        System.out.println("10: Check in person to apartment");
        System.out.println("11: Check out person from apartment");
        System.out.println("12: Evict people from apartment");
        System.out.println("13: Take out content from parking space");
        System.out.println("14: Utilize all from parking space");
        System.out.println("15: Print tenant letters of all people");
        System.out.println("16: Add new object");
        System.out.println("17: Exit");
    }

    public static void printAllRooms() {
        System.out.print("Apartments:");
        printList(housingEstate.getApartments());
        System.out.print("Parking spaces:");
        printList(housingEstate.getParkingSpaces());
    }

    public static void printRentedRooms() {
        System.out.print("Rented Apartments:");
        printList(housingEstate.getRentedApartments());
        System.out.print("Rented Parking spaces:");
        printList(housingEstate.getRentedParkings());
    }

    public static void printFreeRooms() {
        System.out.print("Available Apartments:");
        printList(housingEstate.getFreeApartments());
        System.out.print("Available Parking spaces:");
        printList(housingEstate.getFreeParkings());
    }

    public static void printAllPeople() {
        System.out.print("People:");
        for (Person people : Person.getAllPeople()) {
            System.out.println(people);
        }
        System.out.println();
    }

    public static void rentRoom() {
        System.out.println("Choose room type to rent:");
        System.out.println("1: Apartment");
        System.out.println("2: Parking space");
        int command, id;
        ArrayList<LocalDate> dates;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.print("Choose apartment to rent");
                    printList(housingEstate.getFreeApartments());
                    System.out.println("Enter Apartment ID");
                    id = scanner.nextInt();
                    dates = inputRentDates();
                    printAllPeople();
                    System.out.println("Choose tenant");
                    housingEstate.getFreeApartmentById(id).startRent(inputPerson(), dates.get(0), dates.get(1));
                    break;
                case 2:
                    printAllPeople();
                    System.out.println("Choose tenant");
                    Person p = inputPerson();
                    if (!p.getRentedApartments().isEmpty()) {
                        System.out.println("Choose parking space to rent");
                        printList(housingEstate.getFreeParkings());
                        System.out.println("Enter Parking space ID");
                        id = scanner.nextInt();
                        //dates = inputRentDates();
                        housingEstate.getFreeParkingById(id).startRent(p, LocalDate.parse("2021-04-18"), LocalDate.parse("2021-04-19"));/*dates.get(0), dates.get(1));*/
                    } else {
                        System.out.println("Person has not rented rooms");
                    }
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void cancelRoomRent() {
        System.out.print("Rented rooms:");
        printList(housingEstate.getRentedRooms());
        System.out.println("Choose room type to cancel rent:");
        System.out.println("1: Apartment\n2: Parking space");
        int command, id;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter Apartment ID");
                    id = scanner.nextInt();
                    housingEstate.getApartmentById(id).cancelRent();
                    break;
                case 2:
                    System.out.println("Enter Parking space ID");
                    id = scanner.nextInt();
                    housingEstate.getParkingById(id).cancelRent();
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void renewRoomRent() {
        System.out.print("Rented rooms:");
        printList(housingEstate.getRentedRooms());
        System.out.println("Choose room type to renew rent:");
        System.out.println("1: Apartment\n2: Parking space");
        int command, id;
        String date;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter Apartment ID");
                    id = scanner.nextInt();
                    System.out.println("Enter end date in format yyyy-mm-dd:");
                    date = scanner.next();
                    housingEstate.getApartmentById(id).renewRent(LocalDate.parse(date));
                    break;
                case 2:
                    System.out.println("Enter Parking space ID");
                    id = scanner.nextInt();
                    System.out.println("Enter end date in format yyyy-mm-dd:");
                    date = scanner.next();
                    housingEstate.getParkingById(id).renewRent(LocalDate.parse(date));
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void printResidents() {
        housingEstate.getRentedApartments().forEach((r) -> {
            System.out.print(r);
            printList(r.getPeople());
        });
    }

    public static void printContent() {
        housingEstate.getRentedParkings().forEach((p) -> {
            System.out.print(p + ":");
            printList(p.getContent());
        });
    }

    public static void checkIn() {
        printTenants();
        Person p = inputPerson();
        printList(p.getRentedApartments());
        System.out.println("Enter apartment ID");
        int id = scanner.nextInt();
        printAllPeople();
        p.getRentedApartmentById(id).checkIn(p, inputPerson());
    }

    public static void checkOut() {
        printTenants();
        Person p = inputPerson();
        printList(p.getRentedApartments());
        System.out.println("Enter apartment ID");
        int id = scanner.nextInt();
        System.out.print("Residents:");
        printList(p.getRentedApartmentById(id).getPeople());
        p.getRentedApartmentById(id).checkOut(p, inputPerson());
    }

    public static void evictPeople() {
        printTenants();
        Person p = inputPerson();
        printList(p.getRentedApartments());
        System.out.println("Enter apartment ID");
        int id = scanner.nextInt();
        p.getRentedApartmentById(id).evictPeople();
        System.out.println("All people from" + p.getRentedApartmentById(id) + " were evicted");
    }

    public static void takeOutItem() {
        printTenants();
        Person p = inputPerson();
        System.out.print("Rented parking:");
        p.getRentedApartments().forEach((a) -> System.out.println(a.getParkingSpace().toString()));
        String content;
        int command, id;
        System.out.println("Enter Parking space ID");
        id = scanner.nextInt();
        System.out.print("Parking content:");
        printList(housingEstate.getParkingById(id).getContent());
        System.out.println("What you want to take out?");
        System.out.println("1: Vehicle\n2: Item");
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter vehicle name:");
                    scanner.nextLine();
                    content = scanner.nextLine();
                    Content.getVehicleByName(content);
                    housingEstate.getParkingById(id).takeOutContent(p, Content.getVehicleByName(content));
                    break;
                case 2:
                    System.out.println("Enter item's name:");
                    scanner.nextLine();
                    content = scanner.nextLine();
                    housingEstate.getParkingById(id).takeOutContent(p, Content.getItemByName(content));
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void utilize() {
        printTenants();
        Person p = inputPerson();
        System.out.print("Rented parking:");
        p.getRentedApartments().forEach((a) -> System.out.println(a.getParkingSpace().toString()));
        System.out.println("Enter parking ID");
        int id = scanner.nextInt();
        housingEstate.getParkingById(id).utilizeAll();
        System.out.println("All items from" + housingEstate.getParkingById(id) + " were utilized");
    }

    public static void printTenantLetters() {
        for (Person p : Person.getAllPeople()) {
            System.out.println(p);
            printList(p.getLetters());
        }
    }

    public static void createObject() {
        System.out.println("Which object you want to create?");
        System.out.println("1: Person\n2: Apartment\n3: Parking space\n4: Vehicle\n5: Item");
        int command;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    createPerson();
                    break;
                case 2:
                    createRoom(true);
                    break;
                case 3:
                    createRoom(false);
                    break;
                case 4:
                    createContent(true);
                    break;
                case 5:
                    createContent(false);
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2 && command != 3 && command != 4 && command != 5);
    }

    public static void createPerson() {
        String name, surname, pesel, address;
        scanner.nextLine();
        System.out.println("Enter name:");
        name = scanner.nextLine();

        System.out.println("Enter surname:");
        surname = scanner.nextLine();

        System.out.println("Enter pesel number:");
        pesel = scanner.next();

        scanner.nextLine();

        System.out.println("Enter address:");
        address = scanner.nextLine();

        new Person(name, surname, pesel, address);
    }

    public static void createRoom(boolean f) {
        double volume, rentaFee, length, width, height;
        scanner.nextLine();
        System.out.println("Choose volume input type:");
        System.out.println("1: Cubic meters\n2: Length, width, height");
        int command;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter volume:");
                    volume = scanner.nextDouble();
                    if (f) {
                        System.out.println("Enter rental fee:");
                        rentaFee = scanner.nextDouble();
                        housingEstate.setRooms(new Apartment(volume, rentaFee));
                    } else {
                        housingEstate.setRooms(new ParkingSpace(volume));
                    }
                    break;
                case 2:
                    System.out.println("Enter length:");
                    length = scanner.nextDouble();
                    System.out.println("Enter width:");
                    width = scanner.nextDouble();
                    System.out.println("Enter height:");
                    height = scanner.nextDouble();
                    if (f) {
                        System.out.println("Enter rental fee:");
                        rentaFee = scanner.nextDouble();
                        housingEstate.setRooms(new Apartment(length, width, height, rentaFee));
                    } else {
                        housingEstate.setRooms(new ParkingSpace(length, width, height));
                    }
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void createContent(boolean f) {
        double volume, length, width, height, engineCapacity;
        String name, engineType;
        scanner.nextLine();
        System.out.println("Enter name:");
        name = scanner.nextLine();
        System.out.println("Choose volume input type:");
        System.out.println("1: Cubic meters\n2: Length, width, height");
        int command;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter volume:");
                    volume = scanner.nextDouble();
                    System.out.println("Enter engine capacity:");
                    engineCapacity = scanner.nextDouble();
                    System.out.println("Enter engine type:");
                    engineType = scanner.next();
                    if (f) {
                        createVehicle(name, volume, engineCapacity, engineType);
                    } else {
                        Item i = new Item(name, volume);
                    }
                    break;
                case 2:
                    System.out.println("Enter length:");
                    length = scanner.nextDouble();
                    System.out.println("Enter width:");
                    width = scanner.nextDouble();
                    System.out.println("Enter height:");
                    height = scanner.nextDouble();
                    System.out.println("Enter engine capacity:");
                    engineCapacity = scanner.nextDouble();
                    System.out.println("Enter engine type:");
                    engineType = scanner.next();
                    if (f) {
                        createVehicle(name, length, width, height, engineCapacity, engineType);
                    } else {
                        Item i = new Item(name, length, width, height);
                    }
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void createVehicle(String name, double volume, double engineCapacity, String engineType) {
        System.out.println("Choose vehicle type to create: ");
        System.out.println("1: Off-road car\n2: City car\n3: Boat\n4: Motorcycle\n5: Amphibious");
        String bodyType;
        int command, peopleCapacity, numOfAxles;
        boolean isPickUp, isThreeWheeled;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter engine type:");
                    scanner.nextLine();
                    engineType = scanner.nextLine();
                    System.out.println("Is pick-up? (true/false)");
                    isPickUp = scanner.nextBoolean();
                    OffRoadCar orc = new OffRoadCar(name, volume, engineCapacity, engineType, isPickUp);
                    break;
                case 2:
                    System.out.println("Enter body type:");
                    bodyType = scanner.next();
                    CityCar cc = new CityCar(name, volume, engineCapacity, engineType, bodyType);
                    break;
                case 3:
                    System.out.println("Enter people capacity of boat:");
                    peopleCapacity = scanner.nextInt();
                    Boat b = new Boat(name, volume, engineCapacity, engineType, peopleCapacity);
                    break;
                case 4:
                    System.out.println("Is three wheeled? (true/false):");
                    isThreeWheeled = scanner.nextBoolean();
                    Motorcycle m = new Motorcycle(name, volume, engineCapacity, engineType, isThreeWheeled);
                    break;
                case 5:
                    System.out.println("Enter number of axles:");
                    numOfAxles = scanner.nextInt();
                    Amphibious a = new Amphibious(name, volume, engineCapacity, engineType, numOfAxles);
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2 && command != 3 && command != 4 && command != 5);
    }

    public static void createVehicle(String name, double length, double width, double height, double engineCapacity, String engineType) {
        System.out.println("Choose vehicle type to create: ");
        System.out.println("1: Off-road car\n2: City car\n3: Boat\n4: Motorcycle\n5: Amphibious");
        String bodyType;
        int command, peopleCapacity, numOfAxles;
        boolean isPickUp, isThreeWheeled;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter engine type:");
                    scanner.nextLine();
                    isPickUp = scanner.nextBoolean();
                    OffRoadCar frc = new OffRoadCar(name, length, width, height, engineCapacity, engineType, isPickUp);
                    break;
                case 2:
                    System.out.println("Enter body type:");
                    bodyType = scanner.next();
                    CityCar cc = new CityCar(name, length, width, height, engineCapacity, engineType, bodyType);
                    break;
                case 3:
                    System.out.println("Enter people capacity of boat:");
                    peopleCapacity = scanner.nextInt();
                    Boat b = new Boat(name, length, width, height, engineCapacity, engineType, peopleCapacity);
                    break;
                case 4:
                    System.out.println("Enter engine volume:");
                    isThreeWheeled = scanner.nextBoolean();
                    Motorcycle m = new Motorcycle(name, length, width, height, engineCapacity, engineType, isThreeWheeled);
                    break;
                case 5:
                    System.out.println("Enter number of axles:");
                    numOfAxles = scanner.nextInt();
                    Amphibious a = new Amphibious(name, length, width, height, engineCapacity, engineType, numOfAxles);
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2 && command != 3 && command != 4 && command != 5);
    }

    public static ArrayList<LocalDate> inputRentDates() {
        ArrayList<LocalDate> dates = new ArrayList<>();
        System.out.println("Enter start date in format yyyy-mm-dd");
        dates.add(LocalDate.parse(scanner.next()));
        System.out.println("Enter end date in format yyyy-mm-dd:");
        dates.add(LocalDate.parse(scanner.next()));
        return dates;
    }

    public static Person inputPerson() {
        System.out.println("Enter person's PESEL number");
        String pesel = scanner.next();
        return Person.getByPesel(pesel);
    }

    public static void printTenants() {
        System.out.print("Tenants:");
        for (Person tenant : housingEstate.getTenants()) {
            System.out.print(tenant);
        }
        System.out.println();
    }

    public static void printList(ArrayList<?> arrayList) {
        String result = arrayList.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));
        System.out.println(result);
    }

}
