package Items;

public class Item {
    private String Name;
    private int Price;
    private String imagePath;

    public Item(String name, int price, String imagePath) {
        Name = name;
        Price = price;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return Name;
    }

    public int getPrice() { return Price;}

}
