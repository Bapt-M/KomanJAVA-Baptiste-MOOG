package SceneManagers;

import GameManagement.*;
import GameManagement.Character;
import Items.Equipment;
import Items.EquipmentType;
import Items.Usable;
import Items.UsableType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SceneManager {

    private Scene Scene;
    private Group Root;
    private Map Map;

    private SceneButtons sceneButtons;
    private SceneInterface sceneInterface;
    private SceneEntities sceneEntities;

    public SceneManager(Group root, int width, int height, Color color) {
        Scene = new Scene(root, width, height, color);
        Root = root;

        sceneButtons = new SceneButtons();
        sceneInterface = new SceneInterface();
        sceneEntities = new SceneEntities();
    }

    public Scene getScene() {
        return Scene;
    }

    public void addAll(){
        fillSceneWithMap(Root, Map);
        addButtons();
        addEntities();
        addInterface();
    }

    public void initialize(Map map) {
        Map = map;
        addAll();

        Scene.addEventFilter(KeyEvent.KEY_PRESSED, e->{

            if (sceneButtons.isInventoryOpen() || map.getCharacter().getIsInteracting()) { return; }
            Root.getChildren().clear();
            try {

                switch (e.getCode()) {
                    case LEFT:
                        Map.moveCaracterLeft();
                        System.out.println("Go left");
                        break;
                    case RIGHT:
                        Map.moveCaracterRight();
                        System.out.println("Go right");
                        break;
                    case UP:
                        Map.moveCaracterUp();
                        System.out.println("Go up");
                        break;
                    case DOWN:
                        Map.moveCaracterDown();
                        System.out.println("Go down");
                        break;
                    default:
                        System.out.println("Other click detected");
                        break;
                }


            }
            finally {
                if(map.getCharacter().getX()==24 && map.getCharacter().getY()==7){
                    map.getCharacter().setIsInteracting(true);
                    sceneInterface.displayEndScreen(Root,map,this);
                }
                addAll();
            }

        });
    }

    public void addEntities()
    {
        Root.getChildren().addAll(sceneEntities.getEntities(Root, Map));
    }

    private void addButtons(){
        Root.getChildren().addAll(sceneButtons.getButtons(Root, Map, this));
    }

    public void addInterface(){
        Root.getChildren().addAll(sceneInterface.getInterface(Root, Map));
    }

    private void fillSceneWithMap(Group root, Map randomMap) { //Display the map
        for (int i = 0; i < randomMap.getTableWidth(); i++) {
            for (int j = 0; j < randomMap.getTableHeight(); j++) {
                Cell cell = randomMap.getCellFromCoordinate(i, j);
                var rectangle = cell.getRectangle();
                root.getChildren().add(rectangle);
            }
        }
        Cell cell = randomMap.getCharacterCell();
        var rectangle = cell.getRectangle();
        root.getChildren().add(rectangle);

        for (Rectangle currentRectangle: randomMap.getItemRectangle()) {
            root.getChildren().add(currentRectangle);
        }
    }

    public void resetAndRespawn(Group root, Map map){ //Reset the map and respawn the character
        root.getChildren().clear();

        var resetCaracter = new Character(0, 7);
        resetCaracter.addMoney(50);
        resetCaracter.setCaracteristics(new Caracteristics(5,5,20,20));
        var shield = new Equipment("Shield", 8, EquipmentType.SHIELD, new Caracteristics(0, 20, 0,0), "file:resources/graphics/sprite/equipements/shield1.png");
        resetCaracter.addItem(shield);
        var potion = new Usable("HealPotion", 25, false, UsableType.POTION,  15, "file:resources/graphics/sprite/healPotion.png");
        resetCaracter.addItem(potion);

        map.setCaracter(resetCaracter);
        map.initializeEntities(map);
        addAll();
    }
}
