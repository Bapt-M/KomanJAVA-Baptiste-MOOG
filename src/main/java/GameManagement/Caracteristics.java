package GameManagement;

public class Caracteristics {


    private int attack;
    private int armor;
    private int maxHp;
    private int currentHP;

    public Caracteristics(int attack, int armor, int maxhp, int currentHP) {
        this.attack = attack;
        this.armor = armor;
        this.maxHp = maxhp;
        this.currentHP = currentHP;
    }

    public void addCaracteristics(Caracteristics carac) {
        this.attack += carac.getAttack();
        this.armor += carac.getArmor();
        this.maxHp += carac.getMaxHp();
    }

    public void substractCaracteristics(Caracteristics carac) {
        this.attack -= carac.getAttack();
        this.armor -= carac.getArmor();
        this.maxHp -= carac.getMaxHp();
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public int getMaxHp() {
        return maxHp;
    }

    //Current HP setters and getters
    public void setHp(int hp) {
        this.currentHP = hp;
    }

    public int getCurrentHP() {
        return this.currentHP;
    }
}
