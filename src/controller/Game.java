package controller;

import model.Edge;
import view.PolygonCanvas;

import java.io.File;

/**
 * 
 */
public class Game {


    public PolygonManager polygonManager;
    public StateBackup backup;

    /**
     * Default constructor
     */
    public Game(PolygonManager polygonManager,StateBackup s) {
        this.polygonManager = polygonManager;
        backup = s;
    }


    public void beginGame(int edgeNum,int from,int to) {
        polygonManager.randomGen(edgeNum,from,to);
    }

    /**
     * @param file 表示图形的文件
     */
    public void beginGame(File file) {
        polygonManager.loadPolygon(file);
    }

    /**
     * @param edge
     */
    public void chooseOneOp(Edge edge) {
        polygonManager.choose(edge);
    }

}