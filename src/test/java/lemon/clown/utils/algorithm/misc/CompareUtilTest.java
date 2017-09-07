package lemon.clown.utils.algorithm.misc;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompareUtilTest {
    @Test
    public void min() throws Exception {
        assertEquals(3, CompareUtil.min(12, 3));
    }

    @Test
    public void max() throws Exception {
        assertEquals(15, CompareUtil.max(1, 15));
    }

    @Test
    public void min1() throws Exception {
        assertEquals(13.2, CompareUtil.min(111.1, 13.2), 1e9);
    }

    @Test
    public void max1() throws Exception {
        assertEquals(111.1, CompareUtil.max(111.1, 13.2), 1e9);
    }


    static class A implements Comparable<A> {
        int a, b;

        A(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(A o) {
            if( a == o.a ) return b - o.b;
            return a - o.a;
        }
    }

    A a = new A(1, 1);
    A b = new A(1, 2);
    A c = new A(2, 1);
    A d = new A(2, 3);

    @Test
    public void min2() throws Exception {
        assertEquals(a, CompareUtil.min(a, b));
        assertEquals(a, CompareUtil.min(a, c));
        assertEquals(a, CompareUtil.min(a, d));
        assertEquals(b, CompareUtil.min(b, c));
        assertEquals(b, CompareUtil.min(b, d));
        assertEquals(c, CompareUtil.min(c, d));
    }

    @Test
    public void max2() throws Exception {
        assertEquals(b, CompareUtil.max(a, b));
        assertEquals(c, CompareUtil.max(a, c));
        assertEquals(d, CompareUtil.max(a, d));
        assertEquals(c, CompareUtil.max(b, c));
        assertEquals(d, CompareUtil.max(b, d));
        assertEquals(d, CompareUtil.max(c, d));
    }

}