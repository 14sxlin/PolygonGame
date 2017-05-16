package controller;

import model.Edge;
import model.Polygon;
import model.Vertex;
import view.PolygonCanvas;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class PolygonManager implements Cloneable{

    public Polygon polygon;

    public PolygonCanvas canvas;

    private long v[];
    private char e[];
    public long maxScore;
    public int[] bestSeq;

    Queue<Edge> edgeQueue;

    public PolygonManager(PolygonCanvas canvas) {
        this.canvas = canvas;
    }


    /**
     * 
     */
    public void randomGen(int edgeNum,int from,int to) {
        Edge edges[] = new Edge[edgeNum];
        Vertex vs[] = new Vertex[edgeNum];
        v = new long[edgeNum];
        e = new char[edgeNum];
        int index = 0;
        for(int i=0;i<edges.length;i++){
            double p = Math.random();
            Edge e ;
            if(p>0.5)
            {
                e = new Edge('+');
                this.e[(index+1)%edgeNum] = '+';
            }
            else {
                e = new Edge('*');
                this.e[(index+1)%edgeNum] = '*';
            }
            edges[i] = e;
            int num = (int)(Math.random()*(to-from))+from;
            vs[i] = new Vertex(num);
            this.v[index] = num;
            index++;
        }
        polygon = new Polygon(edges,vs);
        canvas.display(polygon);
    }


    /**
     * @param edge 被选中的边
     */
    public void choose(Edge edge) {
        canvas.markOperation(polygon.getEdges().indexOf(edge));
        polygon.doOperation(edge);
        canvas.display(polygon);
    }

    /**
     *计算最佳值
     */
    public void calBest(){
        PolyGameDp dp = new PolyGameDp(v,e);
        maxScore = dp.getMaxValue();
        bestSeq = dp.getMaxSeq();
        edgeQueue = new LinkedList<>();
        int size = polygon.getEdges().size();
        for(int index:bestSeq){
            int i = (index-1+size)%size;
//            int i = index;
//            int i = (index+1)%size;
            edgeQueue.add(polygon.getEdges()
                    .get(i));
//            System.out.print(polygon.getEdges()
//                    .get(i).getOp()+"\t");
        }
    }

    /**
     * 演示最高分方案
     */
    public void showDemo(){
        new Thread(() ->{
            while(!edgeQueue.isEmpty()){
                choose(edgeQueue.remove());
            }
        }).start();
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public PolygonManager copy() throws CloneNotSupportedException {
        PolygonManager manager = (PolygonManager) clone();
        manager.polygon = polygon.copy();
        return manager;
    }

    /**
     * 通过路径读取多边形的信息
     * @throws NumberFormatException
     */
    public void loadPolygon(File file) throws NumberFormatException{
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            int length = line.trim().split(" ").length;
            e = new char[length];
            int index = 0;
            for (String aOp : line.split(" ")){
                e[index++] = aOp.charAt(0);
//                index++;
            }
            index = 0;
            line = br.readLine();
            v = new long[length];
            for(String value:line.split(" ")) {
                v[index++] = Long.parseLong(value);
            }
            polygon = new Polygon(e,v);
            canvas.display(polygon);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void savePolygon(File dir,String name){
        File file = new File(dir.getAbsolutePath()+"\\"+name);

        System.out.println(file.getAbsoluteFile());
        try {
            if(!file.exists())
                file.createNewFile();
            PrintWriter p = new PrintWriter(file);
            for(char e:e){
                p.print(e+" ");
            }
            p.print("\n");
            for(long i:v){
                p.print(i+" ");
            }
            p.flush();
            p.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}