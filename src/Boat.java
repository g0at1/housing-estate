public class Boat extends Vehicle {

    private int peopleCapacity;

    public Boat(String name,
                double volume,
                double engineCapacity,
                String engineType,
                int peopleCapacity) {
        super(name, volume, engineCapacity, engineType);
        this.peopleCapacity = peopleCapacity;
    }

    public Boat(String name,
                double length,
                double width,
                double height,
                double engineCapacity,
                String engineType,
                int peopleCapacity) {
        super(name, length, width, height, engineCapacity, engineType);
        this.peopleCapacity = peopleCapacity;
    }

    public int getPeopleCapacity() { return peopleCapacity; }

    @Override
    public String toString() {
        return "Boat: Name = "
                + getName()
                + ", Volume = "
                + getVolume()
                + ", Engine Capacity = "
                + getEngineCapacity()
                + ", Engine Type = "
                + getEngineType()
                + ", People Capacity = "
                + getPeopleCapacity()
                + "\n";
    }
}
