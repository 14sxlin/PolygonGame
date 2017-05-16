package model;

/**
 * 
 */
public class DrawEdge {

    private DrawVertex v1;
    private DrawVertex v2;
    private Edge edge;

    /**
     * Default constructor
     */
    public DrawEdge(DrawVertex v1, DrawVertex v2, Edge edge) {
        setVertex(v1,v2);
        setEdge(edge);
    }




    /**
     * @param v1 
     * @param v2
     */
    public void setVertex(DrawVertex v1, DrawVertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public DrawVertex getV1() {
        return v1;
    }

    public void setV1(DrawVertex v1) {
        this.v1 = v1;
    }

    public DrawVertex getV2() {
        return v2;
    }

    public void setV2(DrawVertex v2) {
        this.v2 = v2;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }
}