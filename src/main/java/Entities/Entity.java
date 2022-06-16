package Entities;


import GameManagement.Cell;
import GameManagement.Map;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Entity {
    private int x;
    private int y;

    private EntityStatus status;
    private EntityType type;

    private String imgPath;


    public Entity(int x, int y, EntityStatus status,EntityType type, String imgPath) {
        this.x = x;
        this.y = y;
        this.status = status;
        this.type = type;

        this.imgPath = imgPath;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //function that set x
    public void setX(int x) {
        this.x = x;
    }
    //function that set y
    public void setY(int y) {
        this.y = y;
    }

    public EntityType getType() {
        return type;
    }

    public Rectangle getEntityRectangle(){
        var rectangle = new Rectangle(x* Cell.Width,y*Cell.Height,Cell.Width,Cell.Height);
        Image img = new Image(imgPath, Cell.Width, Cell.Height, true, false);
        rectangle.setFill(new ImagePattern(img));
        return rectangle;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    //For a door, allow to passe only if the door is open or if the player has the key
    public void interact(Map map, Entity entity) {
        if (type == EntityType.DOOR ) {
            var caracter = map.getCharacter();
            if (status == EntityStatus.CLOSE) {
                var keys = map.getCharacter().getItems().stream().filter(item -> item.getName() == "key").findFirst();
                if (keys.isPresent()) {
                    caracter.getItems().remove(keys.get());
                    status = EntityStatus.OPEN;
                    caracter.setX(caracter.getX() + (x - caracter.getX())*2);
                    caracter.setY(caracter.getY() + (y - caracter.getY())*2);
                }
            }
            else {
                caracter.setX(caracter.getX() + (x - caracter.getX())*2);
                caracter.setY(caracter.getY() + (y - caracter.getY())*2);
            }
        }
    }

}
