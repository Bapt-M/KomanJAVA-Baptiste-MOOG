package Entities;

import Items.Item;

import java.util.ArrayList;
import java.util.List;

public class Chest extends Entity{


    public List<Item> items = new ArrayList<Item>();

    public Chest(int x, int y, EntityStatus status, EntityType type, List<Item> items,String imgPath) {
        super(x, y, status, type, imgPath);
        this.items = items;
    }


    public List<Item> getItems() {
        return items;
    }

    public void addItemInChest(Item item){
        items.add(item);
    }


}
