package Items;

import GameManagement.Map;

public class Usable extends Item {
    private boolean isPassive; //If usable can be used by player by clicking or not.
    private UsableType usableType;

    private int usablePower;

    public Usable(String name, int price, boolean isPassive, UsableType usableType, int usablePower, String imagePath) {
        super(name, price, imagePath);
        this.isPassive = isPassive;
        this.usableType = usableType;
        this.usablePower = usablePower;
    }

    public UsableType getUsableType(){
        return this.usableType;
    }


    public int getUsablePower(){
        return this.usablePower;
    }

    public void use(Map map){
        if (usableType == UsableType.MONEYBAG){
            map.getCharacter().addMoney(this.usablePower);
        }
    }
}
