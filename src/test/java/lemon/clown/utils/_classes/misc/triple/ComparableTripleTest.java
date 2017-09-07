package lemon.clown.utils._classes.misc.triple;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComparableTripleTest {
    @Test
    public void compareTo() throws Exception {
        ComparableTriple<Integer, Integer, Integer> a = new ComparableTriple<>(1, 2, 3);
        ComparableTriple<Integer, Integer, Integer> b = new ComparableTriple<>(1, 2, 2);
        ComparableTriple<Integer, Integer, Integer> c = new ComparableTriple<>(1, 3, 2);
        assertTrue(a.compareTo(b) > 0);
        assertTrue(a.compareTo(c) < 0);
        assertTrue(b.compareTo(c) < 0);
    }

}