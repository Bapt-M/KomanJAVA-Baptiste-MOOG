package SceneManagers;

import GameManagement.Map;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class SceneInterface {

    public List<Node> getInterface(Group root, Map map) {
        var nodes = new ArrayList<Node>();

        Image heartImage = new Image("file:resources/graphics/interface/heart.png", 50, 50, true, false);
        Image attackImage = new Image("file:resources/graphics/interface/attack.png", 50, 50, true, false);
        Image armorImage = new Image("file:resources/graphics/interface/armor.png", 50, 50, true, false);
        Image moneyImage = new Image("file:resources/graphics/interface/money.png", 50, 50, true, false);

        Canvas caracteristicsCanvas = new Canvas(700, 70);
        caracteristicsCanvas.setLayoutX(50);
        caracteristicsCanvas.setLayoutY(750);

        GraphicsContext gc = caracteristicsCanvas.getGraphicsContext2D();
        gc.setFont(new Font("Arial", 50));

        gc.setFill(new ImagePattern(heartImage));
        gc.fillRect(0, 0, 50, 50);

        gc.setFill(Color.BLACK);
        gc.fillText(map.getCharacter().getCaracteristics().getCurrentHP()+"/" + map.getCharacter().getCaracteristics().getMaxHp(), 55, 40);

        gc.setFill(new ImagePattern(attackImage));
        gc.fillRect(200, 0, 50, 50);

        gc.setFill(Color.BLACK);
        gc.fillText("" + map.getCharacter().getCaracteristics().getAttack(), 245, 40);

        gc.setFill(new ImagePattern(armorImage));
        gc.fillRect(340, 0, 50, 50);

        gc.setFill(Color.BLACK);
        gc.fillText("" + map.getCharacter().getCaracteristics().getArmor(), 395, 40);

        gc.setFill( new ImagePattern(moneyImage));
        gc.fillRect(490, 0,50,50);

        gc.setFill(Color.BLACK);
        gc.fillText("" + map.getCharacter().getMoney(), 545,40);

        nodes.add(caracteristicsCanvas);
        return nodes;

    }

    public void displayEndScreen(Group root,Map map,SceneManager sceneManager){
        //Takes move controls off from player
        map.getCharacter().setIsInteracting(true);
        //Creates a black screen covering the map
        Group endDisplay = new Group();
        endDisplay.setViewOrder(-3.0);
        Canvas endScreen = new Canvas(1200,720);
        endScreen.setLayoutX(0);
        endScreen.setLayoutY(0);
        GraphicsContext gc = endScreen.getGraphicsContext2D();

        //Displays endgame text
        gc.setFont(new Font("Arial",60));
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1200, 720);
        gc.setFill(Color.WHITE);
        gc.fillText("VOUS ÊTES VAINQUEUR !",200,200);
        gc.setFont(new Font("Arial",30));
        gc.fillText("Réessayer ?",490,300);

        //Adds a retry/new game button
        Button retryButton = new Button();
        retryButton.setViewOrder(-4.0);//Ensures retry button is on top of everything
        retryButton.setLayoutX(470);
        retryButton.setLayoutY(350);
        retryButton.setPrefSize(190, 50);
        retryButton.setText("Nouvelle partie");
        retryButton.setOnAction(event ->{
            sceneManager.resetAndRespawn(root,map);
                });
        endDisplay.getChildren().add(retryButton);
        endDisplay.getChildren().add(endScreen);
        root.getChildren().add(endDisplay);
    }
}
