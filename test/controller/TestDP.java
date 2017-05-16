package controller;

import junit.framework.TestCase;

public class TestDP extends TestCase{

    long vs[][] = {
//            {1,2,3},
//            {1,2,3},
//            {1,2,3},
//            {-1,2,3},
//            {-1,2,3},
//            {1,2,3,4},
            {1,2,3,4,5,6,7}
    };
    char ops[][] ={
//            {'+','+','*'},
//            {'+','*','+'},
//            {'*','+','+'},
//            {'+','+','*'},
//            {'+','*','+'},
//            {'+','+','*','+'},
            {'+','+','*','+','+','+','+'},
    } ;

    long exp[] = {
//            9,
//            8,
//            9,
//            5,
//            4,
//            21,
            192
    };

    public void testGetMax(){
        for(int i=0;i<vs.length;i++){
            PolyGameDp dp = new PolyGameDp(vs[i],ops[i]);
            long value = dp.getMaxValue();
            System.out.println("value = "+value);
            assertEquals(exp[i],value);
            int seq[] = dp.getMaxSeq();
            System.out.print("seq:");
            for(int j=0;j<seq.length;j++)
                System.out.print(seq[j]+"   ");
            System.out.println();
        }
//        assertEquals(7,dp.getMaxValue());
    }
}
