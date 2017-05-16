package controller;

import junit.framework.TestCase;
import model.Polygon;

public class TestPolygonManager extends TestCase {

    int num = 10;
    public void testRandomGen(){
        PolygonManager polygonManager =
                new PolygonManager(null);
        polygonManager.randomGen(num,-20,20);
        Polygon polygon = polygonManager.getPolygon();
        for(int i=0;i<num;i++){
            System.out.println("Edges :"+
                polygon.getEdges().get(i).getOp()+
                    "  vertex :"+polygon.getVertices().get(i).getNum()
            );
        }
        assertEquals(num,polygon.getEdges().size());
        assertEquals(num,polygon.getVertices().size());
    }
}
