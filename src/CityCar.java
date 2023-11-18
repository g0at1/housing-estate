public class CityCar extends Vehicle {

    private String bodyType;

    public CityCar(String name,
                   double volume,
                   double engineCapacity,
                   String engineType,
                   String bodyType) {
        super(name, volume, engineCapacity, engineType);
        this.bodyType = bodyType;
    }

    public CityCar(String name,
                   double length,
                   double width,
                   double height,
                   double engineCapacity,
                   String engineType,
                   String bodyType) {
        super(name, length, width, height, engineCapacity, engineType);
        this.bodyType = bodyType;
    }

    public String getBodyType() { return bodyType; }

    @Override
    public String toString() {
        return "City car: Name ="
                + getName()
                + ", Volume: "
                + getVolume()
                + ", Engine Capacity: "
                + getEngineCapacity()
                + ", Engine Type: "
                + getEngineType()
                + ", Body Type: "
                + getBodyType();
    }
}
