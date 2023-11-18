public class Vehicle extends Content {
    private double engineCapacity;
    private String engineType;

    public Vehicle(String name,
                   double volume,
                   double engineCapacity,
                   String engineType) {
        super(name, volume);
        this.engineCapacity = engineCapacity;
        this.engineType = engineType;
        Content.setVehicles(this);
    }

    public Vehicle(String name,
                   double length,
                   double width,
                   double height,
                   double engineCapacity,
                   String engineType) {
        super(name, length, width, height);
        this.engineCapacity = engineCapacity;
        this.engineType = engineType;
        Content.setVehicles(this);
    }

    public double getEngineCapacity() { return engineCapacity; }

    public String getEngineType() { return engineType; }

    @Override
    public String toString() {
        return "Vehicle: "
                + getName()
                + ", Volume: "
                + getVolume()
                + ", Engine Capacity: "
                + engineCapacity
                + ", Engine Type: "
                + engineType;
    }
}
