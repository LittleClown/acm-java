package lemon.clown.utils.algorithm.math.mod;

import lemon.clown.utils._classes.misc.triple.Triple;
import org.junit.Test;

import static org.junit.Assert.*;

public class GCDTest {
    /**
     * test gcd.egcd(int, int)
     * @throws Exception
     */
    @Test
    public void EGCD() throws Exception {
        Triple<Integer, Integer, Integer> c = GCD.egcd(4, 6);
        assertTrue(c.first == -1 && c.second == 1 && c.third == 2);
        System.out.printf("<%d, %d, %d>\n", c.first, c.second, c.third);

        Triple<Integer, Integer, Integer> d = GCD.egcd(6, 4);
        assertTrue(d.first == 1 && d.second == -1 && d.third == 2);
        System.out.printf("<%d, %d, %d>\n", d.first, d.second, d.third);
    }

    /**
     * test gcd.gcd(int, int, int)
     * @throws Exception
     */
    @Test
    public void GCD() throws Exception {
        assertEquals(3, GCD.gcd(3, 6));
        assertEquals(3, GCD.gcd(9, 6));
        assertEquals(3, GCD.gcd(0, 3));
        assertEquals(3, GCD.gcd(3, 0));
    }

    @Test
    public void GCD1() throws Exception {
    }
}