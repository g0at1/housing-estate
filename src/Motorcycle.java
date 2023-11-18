public class Motorcycle extends Vehicle {
    private boolean isThreeWheeled;

    public Motorcycle(String name,
                      double volume,
                      double engineCapacity,
                      String engineType,
                      boolean isThreeWheeled) {
        super(name, volume, engineCapacity, engineType);
        this.isThreeWheeled = isThreeWheeled;
    }

    public Motorcycle(String name,
                      double length,
                      double width,
                      double height,
                      double engineCapacity,
                      String engineType,
                      boolean isThreeWheeled) {
        super(name, length, width, height, engineCapacity, engineType);
        this.isThreeWheeled = isThreeWheeled;
    }

    public boolean isThreeWheeled() {
        return isThreeWheeled;
    }

    @Override
    public String toString() {
        return "Motorcycle: Name ="
                + getName()
                + ", Volume: "
                + getVolume()
                + ", Engine Capacity: "
                + getEngineCapacity()
                + ", Engine Type: "
                + getEngineType()
                + ", Is three wheeled: "
                + isThreeWheeled();
    }
}
