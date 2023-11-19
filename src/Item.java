public class Item extends Content {

    public Item(String name,
                double volume) {
        super(name, volume);
        Content.setItems(this);
    }

    public Item(String name,
                double length,
                double width,
                double height) {
        super(name, length, width, height);
        Content.setItems(this);
    }


    @Override
    public String toString() {
        return "Item: "
                + this.getName()
                + ", Volume: "
                + this.getVolume();
    }
}
