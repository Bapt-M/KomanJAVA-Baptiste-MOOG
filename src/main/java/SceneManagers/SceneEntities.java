package SceneManagers;

import GameManagement.Map;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class SceneEntities {

    public List<Node> getEntities(Group root, Map map) {
        var nodes = new ArrayList<Node>();
        var entities = map.getEntities();
        for(var entity : entities)
        {
            root.getChildren().add(entity.getEntityRectangle());
        }

        return nodes;
    }

}
