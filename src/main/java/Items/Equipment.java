package Items;

import GameManagement.Caracteristics;

public class Equipment extends Item{
    private Caracteristics caracteristics;
    private EquipmentType equipmentType;
    private Boolean IsEquiped = false;

    public Equipment(String name, int price, EquipmentType type, Caracteristics caracteristics, String imgPath){
        super(name, price, imgPath);
        this.caracteristics = caracteristics;
        this.equipmentType = type;
    }



    public Caracteristics getCaracteristics() {
        return caracteristics;
    }

    public Boolean isEquiped() {
        return IsEquiped;
    }

    public void setEquiped(Boolean equiped) {
        IsEquiped = equiped;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }
}
