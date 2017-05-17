package controller;


import java.util.Stack;

public class StateBackup {

    Stack<PolygonManager> stack;

    public PolygonManager init;

    public StateBackup(){
        stack = new Stack<>();
    }

    public void storeInit(PolygonManager init) throws CloneNotSupportedException {
        this.init = init.copy();
    }

    public void store(PolygonManager polygonManager) throws CloneNotSupportedException {
        stack.push(polygonManager.copy());
    }

    public PolygonManager recover(){
        if(stack.isEmpty())
            return null;
        return stack.pop();
    }

}
