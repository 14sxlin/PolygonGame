package view;

import model.Edge;
import model.Polygon;
import model.Vertex;

import javax.swing.*;


public class TestFrame {

    public static void main(String[] args) throws InterruptedException {
        Edge[] edges = {
                new Edge('+'),
                new Edge('*'),
                new Edge('+'),
                new Edge('*'),
                new Edge('+'),
                new Edge('+'),
                new Edge('+'),
                new Edge('+'),
                new Edge('*'),
                new Edge('+')
        };
        Vertex[] vertices = {
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7),
                new Vertex(8),
                new Vertex(9),
                new Vertex(1)
        };
//        System.out.println(vertices.length);
//        System.out.println(edges.length);
        Polygon polygon = new Polygon(edges,vertices);
        PolygonCanvas canvas = new PolygonCanvas();
        canvas.display(polygon);

        JFrame jFrame = new JFrame();
        jFrame.setSize(600,500);
        jFrame.add(canvas);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        for (Edge edge : edges) {
            Thread.sleep(2000);
            int j = polygon.getEdges().indexOf(edge);
            canvas.markOperation(j);
            polygon.doOperation(edge);
            canvas.display(polygon);
//            System.out.println(polygon.getEdges().size());
//            System.out.println(polygon.getVertices().size());
        }
    }
}
