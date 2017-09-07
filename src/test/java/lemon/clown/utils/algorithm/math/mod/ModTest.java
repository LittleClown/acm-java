package lemon.clown.utils.algorithm.math.mod;

import org.junit.Test;

import static org.junit.Assert.*;

public class ModTest {
    @Test
    public void GCD() throws Exception {
        assertEquals(3, Mod.GCD(3, 6));
        assertEquals(3, Mod.GCD(9, 6));
        assertEquals(3, Mod.GCD(0, 3));
        assertEquals(3, Mod.GCD(3, 0));
    }

    /**
     * test Mod.power(int, int, int)
     * @throws Exception
     */
    @Test
    public void power() throws Exception {
        assertEquals(9, Mod.Power(3, 2,10));
        assertEquals(1, Mod.Power(3, 0,10));
        assertEquals(7, Mod.Power(3, 3,10));
    }

    /**
     * test Mod.power(long, long, long)
     * @throws Exception
     */
    @Test
    public void power1() throws Exception {
        assertEquals(9, Mod.Power(3L, 2L, 10L));
        assertEquals(2464, Mod.Power(2L, 100L, 5232L));
    }

    /**
     * test Mod.power(Multiplicable<T, M), long, M)
     * @throws Exception
     */
    @Test
    public void power2() throws Exception {
        class A implements Multiplicable<Integer, Integer> {
            int value;

            public A(int value) {
                this.value = value;
            }

            @Override
            public Multiplicable<Integer, Integer> getInstanceAsOne() {
                return new A(1);
            }

            @Override
            public Multiplicable<Integer, Integer> mod(Integer m) {
                return new A(value % m);
            }

            @Override
            public Multiplicable<Integer, Integer> multiply(Multiplicable<Integer, Integer> o) {
                return new A(value * ((A) o).value);
            }
        }

        A a = new A(3);

        assertEquals(9, ((A) Mod.Power(a, 2, 10)).value);
    }

}