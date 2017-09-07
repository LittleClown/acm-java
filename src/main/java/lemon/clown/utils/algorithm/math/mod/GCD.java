/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.algorithm.math.mod;

import lemon.clown.utils._classes.misc.triple.Triple;

public class GCD {
    /**
     * @return a 和 b 的最小公倍数
     */
    public static int gcd(int a, int b) {
        return b != 0? gcd(b, a%b): a;
    }

    public static long gcd(long a, long b) {
        return b != 0? gcd(b, a%b): a;
    }

    private static void egcd(Integer a, Integer b, Triple<Integer, Integer, Integer> c) {
        if( b == 0 ) {
            c.first = 1;
            c.second = 0;
            c.third = a;
        } else {
            egcd(b, a%b, c);
            int tmp = c.first;
            c.first = c.second;
            c.second = tmp - c.first*(a/b);
        }
    }

    /**
     * 扩展欧几里得:
     *      求 ax+by=d，其中 d=gcd(a,b)
     * @param a
     * @param b
     * @return Trip(x,y,d)
     */
    public static Triple<Integer, Integer, Integer> egcd(Integer a, Integer b) {
        Triple<Integer, Integer, Integer> ans = new Triple<>();
        egcd(a, b, ans);
        return ans;
    }

    private static void egcd(Long a, Long b, Triple<Long, Long, Long> c) {
        if( b == 0 ) {
            c.first = 1L;
            c.second = 0L;
            c.third = a;
        } else {
            egcd(b, a%b, c);
            long tmp = c.first;
            c.first = c.second;
            c.second = tmp - c.first*(a/b);
        }
    }

    /**
     * 扩展欧几里得:
     *      求 ax+by=d，其中 d=gcd(a,b)
     * @param a
     * @param b
     * @return Trip(x,y,d)
     */
    public static Triple<Long, Long, Long> egcd(Long a, Long b) {
        Triple<Long, Long, Long> ans = new Triple<>();
        egcd(a, b, ans);
        return ans;
    }
}
