package model;


public class DrawVertex {

    private int x;
    private int y;
    private Vertex vertex;

    /**
     * Default constructor
     */
    public DrawVertex(int x,int y,Vertex vertex) {
        this.x = x;
        this.y = y;
        this.vertex = vertex;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }
}