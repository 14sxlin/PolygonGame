package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * 
 */
public class PolygonCanvas extends Canvas{

    private ArrayList<DrawVertex> drawVertices; // 注意里面的值实际上是引用的,poly改变的时候,里面的数值也会改变
    private ArrayList<DrawEdge> drawEdges;

    long max;
    private int margin = 50;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int)dim.getWidth()/2;  //frame中使用的width
    private int height = (int)dim.getHeight()-200; //frame中使用的height
    private int initWidth = Math.min(height,width);
    private int initHeight = Math.min(height,width)-25;
    private int x0 = (initWidth-margin)/2;
    private int y0 = (initHeight-margin)/2-50;
    private double r = (Math.min(initWidth,initHeight)-4*margin)/2.0;

    private int lastChoice = -1;

    /**
     * Default constructor
     */
    public PolygonCanvas() {
        drawVertices = new ArrayList<>();
        drawEdges = new ArrayList<>();
    }


    @Override
    public void paint(Graphics gg) {
        super.paint(gg);
        if(drawEdges.size()<=0)
            return;
//        gg.fillRect(0,0,getWidth(),getHeight());

        Vertex targetVer = new Vertex(0);

        Graphics2D g = (Graphics2D)gg;
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.lightGray);
        g.fillRect(0,0,getWidth(),getHeight());

        x0 = (getWidth()-margin)/2;     // 计算圆心的位置
        y0 = (getHeight()-margin)/2;

        g.setColor(Color.blue);
//        g.fillOval(x0,y0,10,10);


        boolean onlyOne = false;
        if(drawVertices.size()<=1)
            onlyOne = true;
        if(!onlyOne){   // 如果只剩下一个点
            r = (Math.min(getWidth(),getHeight())-2*margin)/2.0;
//        System.out.println("width = "+getWidth()+"  height = "+getHeight());
//        System.out.println("x0 = "+x0+" y0="+y0);
//        System.out.println("-------------------");

//            //画出轮廓圆
//            g.setColor(Color.ORANGE);
//            g.drawOval(margin,margin,2*(int)r,2*(int)r);


            /*画出符号和线*/
            g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
            if(drawEdges.size()>2)          // 如果剩下多于两个点
            {
                int i = 0;
                for (DrawEdge drawEdge : drawEdges) {
//                    System.out.println("op = " + drawEdge.getEdge().getOp());
                    int x1 = drawEdge.getV1().getX();
                    int y1 = drawEdge.getV1().getY();
                    int x2 = drawEdge.getV2().getX();
                    int y2 = drawEdge.getV2().getY();
                    //画出线
                    g.setColor(Color.ORANGE);
                    g.drawLine(x1,y1,x2,y2);
                    g.setColor(Color.blue);

                    if(lastChoice==i)
                        g.setColor(Color.red);
                    g.drawString("" + drawEdge.getEdge().getOp()+"("+i+")",
                            (x1 + x2) / 2,
                            (y1 + y2) / 2);
                    i++;

                }

            }
            else{                       // 如果剩下两个点
                int count = 0;
                for(DrawEdge drawEdge:drawEdges){
                    int x1 = drawEdge.getV1().getX();
                    int y1 = drawEdge.getV1().getY();
                    int x2 = drawEdge.getV2().getX();
                    int y2 = drawEdge.getV2().getY();
                    int gap = 100;
                    g.setColor(Color.blue);
                    if(count==0) {
                        if(lastChoice==count)
                            g.setColor(Color.red);
                        g.drawString("" + drawEdge.getEdge().getOp() + "(" + count + ")",
                                (x0 - gap),
                                (y1 + y2) / 2);   //第一个操作符 放在左边

                    }
                    if(count==1) {
                        if(lastChoice==count)
                            g.setColor(Color.red);
                        g.drawString("" + drawEdge.getEdge().getOp() + "(" + count + ")",
                                (x0 + gap),
                                (y1 + y2) / 2);   //第二个操作符 放在右边
                    }
                    g.setColor(Color.orange);
                    g.drawOval(x1-gap,y1,2*gap,y2-y1);
                    count++;

                }
            }

            /*画出节点*/
            for(DrawVertex drawVertex:drawVertices){
                int x = drawVertex.getX();
                int y = drawVertex.getY();
//            System.out.println(x+","+y);
                g.setColor(Color.black);
                if(drawVertex.getVertex().isNew()) {
                    g.setColor(Color.green);
                    targetVer = drawVertex.getVertex();
                }
                g.fillOval(x-10,y-10,30,30);
                g.setColor(Color.white);
                if(drawVertex.getVertex().isNew())
                    g.setColor(Color.black);
                g.drawString(""+drawVertex.getVertex().getNum(),x,y+5);
            }

//            System.out.print("rest op in canvas = ");
//            for(DrawEdge e:drawEdges){
//                System.out.print(e.getEdge().getOp()+"\t");
//            }
//            System.out.println();

//            System.out.println("edge size = "+drawEdges.size())

        }else{ // 只剩下最后一个点的情况
            DrawVertex drawVertex = drawVertices.get(0);
//            System.out.println(x+","+y);
            if(drawVertex.getVertex().getNum()==max) {
                g.setColor(Color.RED);
                g.drawString("Max Score  !!!",100,100);
                g.setColor(Color.orange);
            }
            else
                g.setColor(Color.black);
            g.fillOval(x0,y0,50,50);
            g.setColor(Color.white);
            g.drawString(""+drawVertex.getVertex().getNum(),x0+15,y0+25);
        }

        targetVer.setNew(false);
    }

    /**
     * 对输入的点和边计算其位置,封装成DrawVertex和DrawEdge
     * @param polygon
     */
    public void display(model.Polygon polygon) {
        drawVertices.clear();
        drawEdges.clear();
//        x0 += margin/2;
//        y0 += margin/2;
        ArrayList<Vertex> vertices = polygon.getVertices();
        ArrayList<Edge> edges = polygon.getEdges();
        double degree = 360.0/vertices.size();
        int count = 0;
        for(Vertex vertex:vertices){
            double x = x0+r*Math.sin(count*degree/180*Math.PI);
            double y = y0+r*Math.cos(count*degree/180*Math.PI);
            DrawVertex drawVertex = new DrawVertex((int)x,(int)y,vertex);
            drawVertices.add(drawVertex);
            if(count>=1){
                DrawEdge drawEdge = new DrawEdge(
                        drawVertices.get(count),
                        drawVertices.get(count-1),
                        edges.get(count-1));
                drawEdges.add(drawEdge);
            }
            count++;
        }
        DrawEdge drawEdge = new DrawEdge(
                drawVertices.get(0),
                drawVertices.get(drawVertices.size()-1),
                edges.get(edges.size()-1));
        drawEdges.add(drawEdge);
//        System.out.println("line size = "+drawEdges.size());
//        System.out.print("rest op in canvas = ");
//        for(Edge e:edges){
//            System.out.print(e.getOp()+"\t");
//        }
//        System.out.println();
//
//        System.out.print("rest Draw op in canvas = ");
//        for(DrawEdge e:drawEdges){
//            System.out.print(e.getEdge().getOp()+"\t");
//        }
//        System.out.println();
//        System.out.println("call repaint");

        repaint();
    }

    /**
     * 用户选择了某个序号,以此选择操作
     * @param index
     * @return
     */
    public DrawEdge getDrawEdge(int index){
        return drawEdges.get(index);
    }

    public void markOperation(int lastChoice){
        this.lastChoice = lastChoice;
        repaint();
        for(int i=0;i<4;i++){
            try {
                if(i%2!=0)
                    this.lastChoice = lastChoice;
                else this.lastChoice = -1;
                repaint();
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.lastChoice = -1;
    }

    private Image iBuffer;
    private Graphics gBuffer;

    @Override
    public void update(Graphics scr)
    {
        if(iBuffer==null)
        {
            iBuffer=createImage(this.getSize().width,this.getSize().height);
            gBuffer=iBuffer.getGraphics();
        }
//        gBuffer.setColor(getBackground());
        gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
        gBuffer.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        paint(gBuffer);
        scr.drawImage(iBuffer,0,0,this);
    }

}