package model;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 
 */
public class Polygon {

    private ArrayList<Edge> edges;
    private ArrayList<Vertex> vertices;


    public Polygon(char[] edges,long[] vertices) {
        this.edges = new ArrayList<>();
        this.vertices = new ArrayList<>();
        int len = edges.length;
        for(int i=0;i<len;i++)
            this.edges.add(new Edge(edges[(i+1)%len]));//为了显示的一致性
        for(int i=0;i<len;i++)
            this.vertices.add(new Vertex(vertices[i]));
    }

    public Polygon(Edge[] edges,Vertex[] vertices) {
        this.edges = new ArrayList<>();
        this.vertices = new ArrayList<>();
        Collections.addAll(this.edges, edges);
        Collections.addAll(this.vertices, vertices);
    }

    public Polygon(ArrayList<Edge> edges, ArrayList<Vertex> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }

    /**
     * @param edge 被选中的边
     */
    public void doOperation(Edge edge) {
        if(vertices.size()<=1)
            return;
        int index = edges.indexOf(edge);
        assert index>=0 && index<= vertices.size()-1;
        long num1 = vertices.get(index).getNum();
        int nextIndex = (index+1)%vertices.size();
        long num2 = vertices.get(nextIndex).getNum();

//        System.out.println("------------------------");
//        System.out.println("index1 = "+index);
//        System.out.println("index2 = "+(index+1)%vertices.size());
//        System.out.println("num1 = "+num1+"  num2 = "+num2);
        switch(edge.getOp()){
            case '+':
                vertices.get(nextIndex).setNum(num1+num2);
                vertices.get(nextIndex).setNew(true);
//                System.out.println("set "+(nextIndex)+" to +  "+vertices.get(nextIndex).getNum());
                break;
            case '*':
                vertices.get(nextIndex).setNum(num1*num2);
                vertices.get(nextIndex).setNew(true);
//                System.out.println("set "+(nextIndex)+" to *  "+vertices.get(nextIndex).getNum());
                break;
        }

//        System.out.println("remove "+vertices.get(index).getNum());
//        System.out.println("remove "+edges.get(index).getOp());
        vertices.remove(index);
        edges.remove(index);
//        System.out.print("rest op = ");
//        for(Edge e:edges){
//            System.out.print(e.getOp()+"\t");
//        }
//        System.out.println();
//        System.out.println("------------------------");


    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }


    public Polygon copy(){
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Vertex> vertices = new ArrayList<>();
        for(Edge e:this.edges){
            edges.add(e.copy());
        }
        for(Vertex v:this.vertices){
            vertices.add(v.copy());
        }
        return new Polygon(edges,vertices);
    }
}