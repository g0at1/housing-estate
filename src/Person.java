import java.util.*;

public class Person {

    private String firstName;
    private String lastName;
    private String pesel;
    private String address;
    private ArrayList<Room> rentedRooms;
    private static ArrayList<Person> persons = new ArrayList<>();

    public Person(String firstName,
                  String lastName,
                  String pesel,
                  String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.address = address;
        rentedRooms = new ArrayList<>();
        persons.add(this);
    }

    public String getPesel() {
        return pesel;
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

    public static ArrayList<Person> getPersons() {
        return persons;
    }

    public void setRentedRooms(Room rentedRoom) {
        this.rentedRooms.add(rentedRoom);
    }

    public static Person getByPesel(String pesel) {
        for (Person person : persons) {
            if (person.getPesel().equals(pesel)) {
                return person;
            }
        }
        System.out.println("Person with pesel " + pesel + " not found.");
        return null;
    }

    public void removeRoom(Room room) {
        rentedRooms.remove(room);
    }

    @Override
    public String toString() {
        return "\n" + firstName + " " + lastName + " (" + pesel + ")";
    }


}
