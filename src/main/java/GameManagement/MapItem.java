package GameManagement;

import Items.Item;

public class MapItem {

    private Item item;
    private int x;
    private int y;

    public MapItem(Item item, int x, int y) {
        this.item = item;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Item getItem() {
        return item;
    }
}
