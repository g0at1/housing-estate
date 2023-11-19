import java.util.*;

public class HousingEstate {
    private String name;
    private ArrayList<Apartment> apartments;
    private ArrayList<ParkingSpace> parkingSpaces;

    public HousingEstate(String name) {
        this.name = name;
        this.apartments = Space.getAllApartments();
        this.parkingSpaces = Space.getAllParkings();
    }

    public String getName() {
        return name;
    }

    public void setRooms(Space space) {
        if (space instanceof Apartment) {
            this.apartments.add((Apartment) space);
        }
        else {
            this.parkingSpaces.add((ParkingSpace) space);
        }
    }

    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

    public Apartment getApartmentById(int number) {
        for (Apartment apartment : this.getApartments()) {
            if (apartment.getIdNum() == number) {
                return apartment;
            }
        }
        return null;
    }

    public ParkingSpace getParkingById(int number) {
        for (ParkingSpace parkingSpace : this.getParkingSpaces()) {
            if (parkingSpace.getIdNum() == number) {
                return parkingSpace;
            }
        }
        return null;
    }

    public ArrayList<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public ArrayList<Apartment> getRentedApartments() {
        ArrayList<Apartment> rented = new ArrayList<>();
        for (Apartment a : apartments) {
            if (a.getTenant() != null) {
                rented.add(a);
            }
        }
        return rented;
    }

    public ArrayList<ParkingSpace> getRentedParkingSpaces() {
        ArrayList<ParkingSpace> rented = new ArrayList<>();
        for (ParkingSpace p : parkingSpaces) {
            if (p.getTenant() != null) {
                rented.add(p);
            }
        }
        return rented;
    }

    public ArrayList<Space> getRentedRooms() {
        ArrayList<Space> rentedSpaces = new ArrayList<>();
        rentedSpaces.addAll(getRentedApartments());
        rentedSpaces.addAll(getRentedParkingSpaces());
        return rentedSpaces;
    }

    public ArrayList<Apartment> getFreeApartments() {
        ArrayList<Apartment> list = new ArrayList<>();
        for (Apartment a : apartments) {
            if (a.getTenant() == null) {
                list.add(a);
            }
        }
        return list;
    }

    public ArrayList<ParkingSpace> getFreeParkingSpaces() {
        ArrayList<ParkingSpace> list = new ArrayList<>();
        for (ParkingSpace p : parkingSpaces) {
            if (p.getTenant() == null) {
                list.add(p);
            }
        }
        return list;
    }

    public Apartment getFreeApartmentById(int number) {
        for (Apartment apartment : this.getFreeApartments()) {
            if (apartment.getIdNum() == number) {
                return apartment;
            }
        }
        return null;
    }

    public ParkingSpace getFreeParkingById(int number) {
        for (ParkingSpace parking : this.getFreeParkingSpaces()) {
            if (parking.getIdNum() == number) {
                return parking;
            }
        }
        return null;
    }

    public Set<Person> getTenants() {
        Set<Person> tenants = new HashSet<>();
        for (Apartment apartment : this.getApartments()) {
            if (apartment.getTenant() != null) {
                tenants.add(apartment.getTenant());
            }
        }
        return tenants;
    }


    @Override
    public String toString() {
        return "HousingEstate{" + name + '}';
    }
}
