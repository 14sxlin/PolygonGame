package model;

import java.util.*;

/**
 * 
 */
public class Edge {

    private char op;


    /**
     * Default constructor
     */
    public Edge(char op) {
        setOp(op);
    }


    public char getOp() {
        return op;
    }

    public void setOp(char op) {
        this.op = op;
    }

    public Edge copy(){
        return new Edge(op);
    }

}