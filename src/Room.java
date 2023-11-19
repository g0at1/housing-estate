import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class Room implements Comparable<Room> {

    private double volume;
    private Person tenant;
    private LocalDate startDate;
    private LocalDate endDate;
    private static ArrayList<Room> rooms = new ArrayList<>();


    public Room(double volume,
                Person tenant,
                LocalDate startDate,
                LocalDate endDate) {
        this.volume = volume;
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        rooms.add(this);
    }

    public Room(double length,
                double width,
                double height,
                Person tenant,
                LocalDate startDate,
                LocalDate endDate) {
        this.volume = Volume.calculateVolume(length, width, height);
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        rooms.add(this);
    }

    public Room(double volume) {
        this.volume = volume;
        rooms.add(this);
    }

    public Room(double length,
                double width,
                double height) {
        this.volume = Volume.calculateVolume(length, width, height);
        rooms.add(this);
    }

    public double getVolume() {
        return volume;
    }

    public Person getTenant() {
        return tenant;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static ArrayList<Apartment> getAllApartments() {
        ArrayList<Apartment> ap = new ArrayList<>();
        for (Room r : rooms) {
            if (r instanceof Apartment) {
                ap.add((Apartment) r);
            }
        }
        return ap;
    }

    public static ArrayList<ParkingSpace> getAllParkings() {
        ArrayList<ParkingSpace> ps = new ArrayList<>();
        for (Room r : rooms) {
            if (r instanceof ParkingSpace) {
                ps.add((ParkingSpace) r);
            }
        }
        return ps;
    }

    public void deleteTenant() {
        this.tenant = null;
        this.startDate = null;
        this.endDate = null;
    }

    public boolean checkIfAvailable() { return this.tenant == null; }

    public void startRent(Person person,
                          LocalDate start,
                          LocalDate end) {
        if (!checkIfAvailable()) {
            System.out.println("Room is being rented right now");
            return;
        }

        if (person.getRentedRooms().size() >= 5) {
            System.out.println("Person already has " + person.getRentedRooms().size() + " rooms rented");
            return;
        }

        if (this instanceof Apartment) {
            startRentingApartment(person, start, end);
        } else {
            startRentingParkingSpace(person, start, end);
        }
    }

    private void startRentingApartment(Person person,
                                       LocalDate start,
                                       LocalDate end) {
        this.tenant = person;
        this.startDate = start;
        this.endDate = end;
        person.setRentedRooms(this);
        System.out.println("Rent started from " + start + " to " + end +  " by " + this);
    }

    private void startRentingParkingSpace(Person person,
                                          LocalDate start,
                                          LocalDate end) {
        if (person.getRentedRooms().isEmpty()) {
            System.out.println(person + " has no rented apartments");
            return;
        }

        System.out.print("Rented apartments:");
        for (Apartment apartment : person.getRentedApartments()) {
            System.out.print(apartment);
        }

        System.out.println();
        System.out.println("Enter apartment ID to add parking space:");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        person.getRentedApartmentById(id).addParkingSpace((ParkingSpace) this, start, end);
        this.tenant = person;
        this.startDate = start;
        this.endDate = end;
        person.setRentedRooms(this);
        System.out.println("Rent has successfully started " + this + " by " + person + " from: " + start + " to: " + end);
    }

    public void renewRent(LocalDate endDate) {
        Period period = Period.between(this.getEndDate(), Main.currentDate);
        if (period.getDays() <= 30 || period.getMonths() <= 1) {
            System.out.println("Rent was renewed, last end:" + this.endDate + ", current end:" + endDate);
            this.endDate = endDate;
        } else {
            System.out.println("Rent can't be renewed, it's been " + period.getDays() + " days after expiring");
        }
    }

    public void cancelRent() {
        Period period = Period.between(this.getEndDate(), Main.currentDate);
        if (period.getDays() <= 30 && period.getMonths() < 1) {
            this.tenant.removeRoom(this);
            deleteTenant();
            System.out.println("Rent rent is canceled");
        } else {
            this.tenant.removeRoom(this);
            deleteTenant();
            System.out.println("Rent rent is canceled, but tenant letter remains");
        }
    }

    @Override
    public int compareTo(Room room) {
        return (int) (getVolume() - room.getVolume());
    }
}
