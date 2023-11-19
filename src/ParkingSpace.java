import java.time.LocalDate;
import java.util.ArrayList;

public class ParkingSpace extends Room {

    private int idNum;
    private ArrayList<Vehicle> content;
    private double freeVolume;
    private static int counter = 1;

    public ParkingSpace(double volume,
                        Person tenant,
                        LocalDate startDate,
                        LocalDate endDate) {
        super(volume, tenant, startDate, endDate);
        content = new ArrayList<>();
        this.freeVolume = volume;
        this.idNum = counter++;
    }

    public ParkingSpace(double length,
                        double width,
                        double height,
                        Person tenant,
                        LocalDate startDate,
                        LocalDate endDate) {
        super(length, width, height, tenant, startDate, endDate);
        content = new ArrayList<>();
        this.freeVolume = getVolume();
        this.idNum = counter++;
    }

    public ParkingSpace(double volume) {
        super(volume);
        content = new ArrayList<>();
        this.freeVolume = volume;
        this.idNum = counter++;
    }

    public ParkingSpace(double length,
                        double width,
                        double height) {
        super(length, width, height);
        content = new ArrayList<>();
        this.freeVolume = getVolume();
        this.idNum = counter++;
    }

    public int getIdNum() {
        return idNum;
    }

    public ArrayList<Vehicle> getContent() {
        return content;
    }

    public void insertContent(Person tenant, Vehicle vehicle) throws TooManyThingsException {
        if (this.getTenant().equals(tenant)) {
            if (this.freeVolume < vehicle.getVolume()) {
                throw new TooManyThingsException();
            } else {
                this.freeVolume -= vehicle.getVolume();
                this.content.add(vehicle);
                System.out.println(vehicle + " was added to " + this);
            }
        }
    }

    public void takeOutContent(Person tenant, Content content) {
        if (this.getTenant().equals(tenant)) {
            this.freeVolume += content.getVolume();
            this.content.remove(content);
            System.out.println(content + " was taken out from " + this);
        }
    }

    public void utilizeAll() {
        this.content.clear();
    }

    @Override
    public String toString() {
        return "\nParking Space ID: "
                + idNum
                + ", Volume:"
                + getVolume()
                + "\n";
    }
}
