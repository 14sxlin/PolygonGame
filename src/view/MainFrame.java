package view;

import controller.Game;
import controller.PolygonManager;
import controller.StateBackup;



public class MainFrame {
    public static void main(String[] args) {
        PolygonManager polygonManager =
                new PolygonManager(new PolygonCanvas());
        StateBackup stateBackup = new StateBackup();
        Game game = new Game(polygonManager,stateBackup);
        new GameFrame(game);
    }
}
