package lemon.clown.algorithm.misc;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompareTest {
    @Test
    public void min() throws Exception {
        assertEquals(3, Compare.min(12, 3));
    }

    @Test
    public void max() throws Exception {
        assertEquals(15, Compare.max(1, 15));
    }

    @Test
    public void min1() throws Exception {
        assertEquals(13.2, Compare.min(111.1, 13.2), 1e9);
    }

    @Test
    public void max1() throws Exception {
        assertEquals(111.1, Compare.max(111.1, 13.2), 1e9);
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
        assertEquals(a, Compare.min(a, b));
        assertEquals(a, Compare.min(a, c));
        assertEquals(a, Compare.min(a, d));
        assertEquals(b, Compare.min(b, c));
        assertEquals(b, Compare.min(b, d));
        assertEquals(c, Compare.min(c, d));
    }

    @Test
    public void max2() throws Exception {
        assertEquals(b, Compare.max(a, b));
        assertEquals(c, Compare.max(a, c));
        assertEquals(d, Compare.max(a, d));
        assertEquals(c, Compare.max(b, c));
        assertEquals(d, Compare.max(b, d));
        assertEquals(d, Compare.max(c, d));
    }

}