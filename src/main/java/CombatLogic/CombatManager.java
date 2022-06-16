package CombatLogic;

import Entities.Monster;
import GameManagement.Animation;
import GameManagement.Character;
import GameManagement.Map;
import Items.Item;
import SceneManagers.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class CombatManager {
    private Character playerCharacter;
    private Monster monsterEntity;
    private Canvas combatScreen;
    private Group displayGroup;
    private Group entitiesDisplayed;
    private Group root;
    private Map map;
    private SceneManager sceneManager;


    public CombatManager(Character playerCharacter, Monster monsterEntity, Group root, Map map, SceneManager sceneManager){
        this. playerCharacter = playerCharacter;
        this.monsterEntity = monsterEntity;
        this.root = root;
        this.map = map;
        this.sceneManager = sceneManager;


    }

    public void createDisplay(Group root){

        //Group creation:
        displayGroup = new Group();
        displayGroup.setViewOrder(-1.0);
        //Create the display (Black background)
        combatScreen = new Canvas(830, 500);
        combatScreen.setLayoutX(185);
        combatScreen.setLayoutY(110);

        GraphicsContext gc = combatScreen.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 830, 500);
        displayGroup.getChildren().add(combatScreen);

        //Adds player and monster + current health of both
        loadEntities(displayGroup);

        //Finally, adds all groups to the root group for general display
        root.getChildren().add(displayGroup);
    }

    

    public void enterCombatLoop(){
        createDisplay(root);
        loadPlayerActions();
    }

    public void loadEntities(Group group){
        //Loads images
        Image heartImage = new Image("file:resources/graphics/interface/heart.png", 50, 50, true, false);

        entitiesDisplayed = new Group();

        //Displays the player:
        Canvas playerCanvas = new Canvas(100,100);
        playerCanvas.setLayoutX(750);
        playerCanvas.setLayoutY(400);
        GraphicsContext playerGContext = playerCanvas.getGraphicsContext2D();
        playerGContext.setFill(new ImagePattern(new Image(playerCharacter.getImgPath(),100,100, true,false)));
        playerGContext.fillRect(0, 0, 100, 100);
        entitiesDisplayed.getChildren().add(playerCanvas);

        //Displays the player health
        Canvas playerHealthCanvas = new Canvas(200,100);
        playerHealthCanvas.setLayoutX(600);
        playerHealthCanvas.setLayoutY(450);
        GraphicsContext playerHealthGContext = playerHealthCanvas.getGraphicsContext2D();
        playerHealthGContext.setFont(new Font("Arial",35));
        //adds hearth icon
        playerHealthGContext.setFill(new ImagePattern(heartImage));
        playerHealthGContext.fillRect(0, 0, 35, 35);
        //adds health text
        playerHealthGContext.setFill(Color.WHITE);
        playerHealthGContext.fillText(playerCharacter.getCaracteristics().getCurrentHP()+"/"+playerCharacter.getCaracteristics().getMaxHp(),35,35);
        entitiesDisplayed.getChildren().add(playerHealthCanvas);

        //Displays the monster:
        var rectangle = new Rectangle(320,200,100,100);
        Image img = new Image(monsterEntity.getImgPath(),100, 100, true, false);
        rectangle.setFill(new ImagePattern(img));

        entitiesDisplayed.getChildren().add(rectangle);

        //Displays the monster health
        Canvas monsterHealthCanvas = new Canvas(200,100);
        monsterHealthCanvas.setLayoutX(450);
        monsterHealthCanvas.setLayoutY(250);
        GraphicsContext monsterHealthGContext = monsterHealthCanvas.getGraphicsContext2D();
        monsterHealthGContext.setFont(new Font("Arial",35));
        //adds hearth icon
        monsterHealthGContext.setFill(new ImagePattern(heartImage));
        monsterHealthGContext.fillRect(0, 0, 35, 35);
        //adds health text
        monsterHealthGContext.setFill(Color.WHITE);
        monsterHealthGContext.fillText(monsterEntity.getCaracteristics().getCurrentHP()+"/"+monsterEntity.getCaracteristics().getMaxHp(),35,35);
        entitiesDisplayed.getChildren().add(monsterHealthCanvas);





        //add to parent group
        group.getChildren().add(entitiesDisplayed);


    }

    public void unloadEntities(Group group){
        group.getChildren().remove(entitiesDisplayed);
        //System.out.println("Unloaded combat entities");
    }

    public void reloadEntities(Group group){
        //Reload combat display ONLY !!
        unloadEntities(group);
        loadEntities(group);
    }

    public void loadPlayerActions(){

        Button basicAttack = new Button();
        basicAttack.setLayoutX(200);
        basicAttack.setLayoutY(530);
        basicAttack.setPrefSize(140, 50);
        basicAttack.setText("Basic Attack");
        basicAttack.setOnAction(event ->{
            //Perform attack on monster
            System.out.println("Player attack = "+playerCharacter.getCaracteristics().getAttack());
            monsterEntity.takeDamages(playerCharacter.getCaracteristics().getAttack());
            System.out.println("new monster HP = "+monsterEntity.getCaracteristics().getCurrentHP());
            reloadEntities(displayGroup); //Reloads combat display


            if(monsterEntity.getCaracteristics().getCurrentHP() <=0){
                //Adds animation
                Animation basicAttackAnim = new Animation(1,"file:resources/graphics/sprite/hit_animation.gif",displayGroup,250,250,270,130);
                basicAttackAnim.playAnimation();
                //Timer for readability and animation
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(basicAttackAnim.getAnimationLength()),ev ->{
                    basicAttackAnim.removeAnimation();
                    reloadEntities(displayGroup);
                    //Ends player's turn and begins monster's turn
                    winCombat();
                }));
                timeline.play();

            }else{
                //Adds animation
                Animation basicAttackAnim = new Animation(1,"file:resources/graphics/sprite/hit_animation.gif",displayGroup,250,250,270,130);
                basicAttackAnim.playAnimation();
                //Timer for readability and animation
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(basicAttackAnim.getAnimationLength()),ev ->{
                    basicAttackAnim.removeAnimation();
                    reloadEntities(displayGroup);
                    //Ends player's turn and begins monster's turn
                    playMonsterTurn();
                }));
                timeline.play();


            }
        });

        entitiesDisplayed.getChildren().add(basicAttack);

    }

    private void playPlayerTurn(){
        loadPlayerActions();
    }

    private void playMonsterTurn(){
        //Attacks player with a basic attack
        playerCharacter.takeDamages(monsterEntity.getCaracteristics().getAttack());
        //Reloads combat display

        if(playerCharacter.getCaracteristics().getCurrentHP() <=0){
            //Adds animation
            Animation basicAttackAnim = new Animation(1,"file:resources/graphics/sprite/hit_animation.gif",displayGroup,250,250,650,300);
            basicAttackAnim.playAnimation();
            //Timer for readability and animation
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(basicAttackAnim.getAnimationLength()),ev ->{
                basicAttackAnim.removeAnimation();
                reloadEntities(displayGroup);
                //Ends monster's turn and begins player's turn
                looseCombat();

            }));
            timeline.play();


        }else{
            //Adds animation
            Animation basicAttackAnim = new Animation(1,"file:resources/graphics/sprite/hit_animation.gif",root,250,250,650,300);
            basicAttackAnim.playAnimation();
            //Timer for readability and animation
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(basicAttackAnim.getAnimationLength()),ev ->{
                basicAttackAnim.removeAnimation();
                reloadEntities(displayGroup);
                //Ends monster's turn and begins player's turn
                playPlayerTurn();
            }));
            timeline.play();

        }
    }

    public void winCombat(){
        System.out.println("You WON against an enemy");
        //Removes combat screen
        root.getChildren().remove(displayGroup);
        //Asks the monster to give it's items to the player
        if(monsterEntity.getDropList() != null){ //Verifies the droplist isn't null
            for(Item item: monsterEntity.getDropList()){
                playerCharacter.addItem(item);
            }
        }

        //Removes the monster entity and reloads the map DISPLAY
        map.getEntities().remove(this.monsterEntity);
        root.getChildren().clear();
        sceneManager.addAll();
        //Removes current combat manager

        //Gives move key controls back to the player
        playerCharacter.setIsInteracting(false);
    }

    public void looseCombat(){
        System.out.println("You LOST against an enemy");
        //Displays death screen for 2 second:
        Animation deathScreen = new Animation(2,"file:resources/graphics/interface/you_died_screen.png",displayGroup,800,400,200,170);
        deathScreen.playAnimation();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(deathScreen.getAnimationLength()),ev ->{
            //Then plays death and respawn logic
            root.getChildren().remove(displayGroup);
            //Reloads the MAP and player position (Respawn)
            sceneManager.resetAndRespawn(root,map);
            //Gives move key controls back to the player
            playerCharacter.setIsInteracting(false);
        }));
        timeline.play();

    }


}
