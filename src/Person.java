import java.util.*;

public class Person {

    private String firstName;
    private String lastName;
    private String pesel;
    private String address;
    private ArrayList<Space> rentedSpaces;
    private static ArrayList<Person> persons = new ArrayList<>();

    public Person(String firstName,
                  String lastName,
                  String pesel,
                  String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.address = address;
        rentedSpaces = new ArrayList<>();
        persons.add(this);
    }

    public String getPesel() {
        return pesel;
    }

    public ArrayList<Space> getRentedRooms() {
        return rentedSpaces;
    }

    public ArrayList<Apartment> getRentedApartments() {
        ArrayList<Apartment> apartments = new ArrayList<>();
        for (Space space : this.getRentedRooms()) {
            if (space instanceof Apartment) {
                apartments.add((Apartment) space);
            }
        }
        return apartments;
    }

    public Apartment getRentedApartmentById(int number) {
        for (Space space : this.getRentedRooms()) {
            if (space instanceof Apartment) {
                if (((Apartment) space).getIdNum() == number) {
                    return (Apartment) space;
                }
            }
        }
        return null;
    }

    public static ArrayList<Person> getPersons() {
        return persons;
    }

    public void setRentedSpaces(Space rentedSpace) {
        this.rentedSpaces.add(rentedSpace);
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

    public void removeRoom(Space space) {
        rentedSpaces.remove(space);
    }

    @Override
    public String toString() {
        return "\n" + firstName + " " + lastName + " (" + pesel + ")";
    }


}
