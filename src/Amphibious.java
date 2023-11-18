public class Amphibious extends Vehicle {

    private int numOfAxles;

    public Amphibious(String name,
                      double volume,
                      double engineCapacity,
                      String engineType,
                      int numOfAxles) {
        super(name, volume, engineCapacity, engineType);
        this.numOfAxles = numOfAxles;
    }

    public Amphibious(String name,
                      double length,
                      double width,
                      double height,
                      double engineCapacity,
                      String engineType,
                      int numOfAxles) {
        super(name, length, width, height, engineCapacity, engineType);
        this.numOfAxles = numOfAxles;
    }

    public int getNumOfAxles() { return numOfAxles; }

    @Override
    public String toString() {
        return "Amphibious: Name="
                + getName()
                + ", Volume="
                + getVolume()
                + ", Engine Capacity="
                + getEngineCapacity()
                + ", Engine Type="
                + getEngineType()
                + ", Number of Axles="
                + getNumOfAxles();
    }
}
