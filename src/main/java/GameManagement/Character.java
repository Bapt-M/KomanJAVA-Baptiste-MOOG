package GameManagement;


import com.google.gson.annotations.Expose;
import Items.*;
import java.util.ArrayList;
import java.util.List;

public class Character {
    @Expose
    private int x;

    @Expose
    private int y;

    @Expose
    private String ImgPath = "file:resources/graphics/sprite/character.png";

    @Expose
    private Caracteristics caracteristics;

    @Expose
    private int money = 0;

    private List<Item> items = new ArrayList<Item>();

    //Gives the program the information if the player is in an interaction and move keys needs to be frozen.
    private boolean isInteracting = false;

    public List<Item> getItems() {
        return items;
    }

    public boolean hasItem(Item item){
        return items.contains(item);
    }


    public Character(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }


    public Caracteristics getCaracteristics() {
        return caracteristics;
    }

    public void setCaracteristics(Caracteristics caracteristics) {
        this.caracteristics = caracteristics;
    }

    public Boolean equipItem(Equipment item){
        if (items.stream().anyMatch(i -> i instanceof Equipment && ((Equipment)i).getEquipmentType() == item.getEquipmentType() && ((Equipment)i).isEquiped())) { //Si on a déjà un item de ce type équipé, on interdit d'équiper l'item
            return false;
        }
        else { //Autrement, on équipe l'item
            caracteristics.addCaracteristics(item.getCaracteristics());
            item.setEquiped(true);
            return true;
        }
    }

    public void unequipItem(Equipment item){
        if (item.isEquiped()) {
            caracteristics.substractCaracteristics(item.getCaracteristics());
            verifiesCurrentHP();
            item.setEquiped(false);
        }
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int qty) {
        this.money += qty ;
    }

    public void takeMoney(int qty) {
        this.money -= qty ;
    }

    public void sellItem(Item item) {
        if (item instanceof Equipment) {
            this.unequipItem((Equipment) item);
            addMoney(item.getPrice());
            removeItem(item);
        }
        //if the item is named heartKey, add attack to the player
        if(item.getName().equals("heartKey")){
            this.caracteristics.addCaracteristics(new Caracteristics(15,0,0,0));
            removeItem(item);
        };
    }

    public void takeDamages(int damages){
        double calculated =1+ damages/(1 + 0.4*this.caracteristics.getArmor());
        System.out.println("Damage calculated = "+calculated);
        this.caracteristics.setHp(this.getCaracteristics().getCurrentHP() - (int) calculated);
    }

    //heals player for a certain amount. Called by a healing item in combat or inventory.
    public void heal(int amount){
        int currentHP = this.getCaracteristics().getCurrentHP();
        if(currentHP + amount <= this.getCaracteristics().getMaxHp()){
            //Gives full heal amount provided by the item
            this.getCaracteristics().setHp(currentHP+amount);
        }else{
            if(currentHP <= this.getCaracteristics().getMaxHp()){
                int calculated = this.getCaracteristics().getMaxHp() - currentHP;
                this.getCaracteristics().setHp(currentHP + calculated);
                //heals the player the amount needed to be full health
            }else{
                this.getCaracteristics().setHp(currentHP); //heals nothing because player is already full health
            }
        }
    }


    //Setters and getters for isInteracting.
    public boolean getIsInteracting() {
        return isInteracting;
    }

    public void setIsInteracting(boolean interacting) {
        isInteracting = interacting;
    }

    //Verifies if current HP is not higher than max HP
    public void verifiesCurrentHP(){
        if(this.getCaracteristics().getCurrentHP() > this.getCaracteristics().getMaxHp()){
            //sets currentHp = maxHP
            this.getCaracteristics().setHp(this.getCaracteristics().getMaxHp());
        }
    }

}
