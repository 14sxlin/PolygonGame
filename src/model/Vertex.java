package model;

public class Vertex {

    private long num;

    private boolean isNew = false;
    /**
     * Default constructor
     */
    public Vertex(long num) {
        setNum(num);
    }



    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Vertex copy(){
        return new Vertex(num);
    }
}