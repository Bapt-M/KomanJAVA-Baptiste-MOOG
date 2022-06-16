package Entities;

import GameManagement.Character;
import Items.Item;

import java.util.ArrayList;
import java.util.List;

public class Merchant extends Entity {
    private List<Item> items=new ArrayList<Item>();
    private String dialog;

    //add dialog to merchant caracteristics
    public Merchant(int x, int y, List<Item> items, EntityStatus status, EntityType type, String imgPath, String dialog) {
        super(x, y, status, type, imgPath);
        this.items=items;
        this.dialog=dialog;
    }

    public List<Item> getItems() {
        return items;
    }
    public void removeItem(Item item){
        items.remove(item);
    }
    public void addItem(Item item){
        items.add(item);
    }
    public String getDialog() {
        return dialog;
    }



    public void buyItem(Item item, Character character) {
        if (character.getMoney()>=item.getPrice()){
            character.takeMoney(item.getPrice());
            character.addItem(item);
            removeItem(item);
        }


    }
}
