public class OffRoadCar extends Vehicle {

    private boolean isPickUp;

    public OffRoadCar(String name,
                      double volume,
                      double engineCapacity,
                      String engineType,
                      boolean isPickUp) {
        super(name, volume, engineCapacity, engineType);
        this.isPickUp = isPickUp;
    }

    public OffRoadCar(String name,
                      double length,
                      double width,
                      double height,
                      double engineCapacity,
                      String engineType,
                      boolean isPickUp) {
        super(name, length, width, height, engineCapacity, engineType);
        this.isPickUp = isPickUp;
    }

    public boolean isPickUp() {
        return isPickUp;
    }

    @Override
    public String toString() {
        return "Off Road car: Name ="
                + getName()
                + ", Volume: "
                + getVolume()
                + ", Engine Capacity: "
                + getEngineCapacity()
                + ", Engine Type: "
                + getEngineType()
                + ", Is pick up: "
                + isPickUp();
    }
}
