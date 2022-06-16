package com.example.komanjava;
import GameManagement.*;
import SceneManagers.SceneManager;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        //Create main display Group
        Group root = new Group();
        stage.setTitle("Koman Java");

        //Creates a new scene for runtime
        var sceneManager = new SceneManager(root, 1450, 850, Color.WHITE);

        //Creates the map
        Map map = Map.createFirstLevel();
        sceneManager.initialize(map);

        stage.setScene(sceneManager.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}