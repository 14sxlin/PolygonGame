package controller;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by sparr on 2017/5/14.
 */
public class TestClone extends TestCase {

    class P{
        int i = 100;
        P(int x) {
            this.i = x;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    /**
     * array.clone 对于基本类型是深拷贝
     */
    public void testArrayListClone(){
        ArrayList<P> a = new ArrayList<>();
        a.add(new P(100));
        ArrayList<P> b = (ArrayList<P>)a.clone();
        assertEquals(a.size(),b.size());
        assertTrue(a!=b); // array 分配了新空间
        assertTrue(a.get(0)!=b.get(0)); // p引用
    }

    public void testArrayClone(){
        int[] a = {1,2,3,4};
        int[] b = a.clone();
        a[0] = 999;
        assertTrue(b[0]!=999);
    }
}
