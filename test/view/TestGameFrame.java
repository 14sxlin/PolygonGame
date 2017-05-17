package view;

import controller.Game;
import controller.PolygonManager;
import controller.StateBackup;
import junit.framework.TestCase;

public class TestGameFrame{

    public static void main(String args[]){
        PolygonManager polygonManager =
                new PolygonManager(new PolygonCanvas());
        StateBackup stateBackup = new StateBackup();
        Game game = new Game(polygonManager,stateBackup);
        new GameFrame(game);
    }
}
