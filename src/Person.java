import java.util.*;

public class Person {

    private String name;
    private String surname;
    private String pesel;
    private String address;
    private ArrayList<TenantLetter> letters;
    private ArrayList<Room> rentedRooms;
    private static ArrayList<Person> allPeople = new ArrayList<>();

    public Person(String name, String surname, String pesel, String address) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.address = address;
        letters = new ArrayList<>();
        rentedRooms = new ArrayList<>();
        allPeople.add(this);
    }

//    public Person(String name, String surname, String address) {
//        this.name = name;
//        this.surname = surname;
//        Random r = new Random();
//        this.pesel = 10000 + r.nextInt(90000);
//        this.address = address;
//        letters = new ArrayList<>();
//        rentedRooms = new ArrayList<>();
//        allPeople.add(this);
//    }

    public String getName() {
        return name + " " + surname;
    }

    public String getPesel() {
        return pesel;
    }

    public ArrayList<TenantLetter> getLetters() {
        return letters;
    }

    public ArrayList<Room> getRentedRooms() {
        return rentedRooms;
    }

    public ArrayList<Apartment> getRentedApartments() {
        ArrayList<Apartment> apartments = new ArrayList<>();
        for (Room room : this.getRentedRooms()) {
            if (room instanceof Apartment) {
                apartments.add((Apartment) room);
            }
        }
        return apartments;
    }

    public Apartment getRentedApartmentById(int number) {
        for (Room room : this.getRentedRooms()) {
            if (room instanceof Apartment) {
                if (((Apartment) room).getIdNum() == number) {
                    return (Apartment) room;
                }
            }
        }
        return null;
    }

    public static ArrayList<Person> getAllPeople() {
        return allPeople;
    }

    public void setRentedRooms(Room rentedRoom) {
        this.rentedRooms.add(rentedRoom);
    }

    public void setLetters(TenantLetter letter) {
        this.letters.add(letter);
    }

    public static Person getByPesel(String pesel) {
        for (Person person : allPeople) {
            if (person.getPesel().equals(pesel)) {
                return person;
            }
        }
        System.out.println("There are no person with pesel " + pesel);
        return null;
    }

    public void addLetter(Room room, StringBuilder str) {
        this.letters.add(new TenantLetter(room, str));
    }

    public void removeLetter(Room room) {
        for (TenantLetter l : letters) {
            if (l.getRoom().equals(room)) {
                this.letters.remove(l);
                break;
            }
        }
    }

    public void removeRoom(Room room) {
        rentedRooms.remove(room);
    }

    @Override
    public String toString() {
        return "\n" + name + " " + surname + " (" + pesel + ")";
    }


}
