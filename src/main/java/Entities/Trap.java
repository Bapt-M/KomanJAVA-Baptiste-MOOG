package Entities;
import GameManagement.Character;

public class Trap extends Entity{

    public TrapType type;

    public Trap(int x, int y, EntityStatus status, EntityType type, String imgPath, TrapType trap) {
        super(x, y, status, type, imgPath);
        this.type = trap;
    }

    //Apply trap effect to the character
    public void triggerTrap(Character character){
        if (type == TrapType.BLACKHOLE) {
            character.takeDamages(1000);
        }
        else if (type == TrapType.DART) {
            character.takeDamages(10);
        }
    }

}
