/*~
 *  Created by lemon-clown on 2017/9/5
 */
package lemon.clown.utils.algorithm.math.mod;

import java.util.ArrayList;
import java.util.List;

/**
 * 线性筛素数
 */
public class Primes {
    final int MAXN;
    boolean[] isNotPrime;
    List<Integer> primes;

    public Primes(int MAXN) {
        this.MAXN = MAXN;
        isNotPrime = new boolean[MAXN];
        primes = new ArrayList<>();

        init();
    }

    /**
     * 返回素数列表
     * @return
     */
    public List<Integer> getPrimes() {
        return primes;
    }

    protected void init() {
        for(int i=2; i < MAXN; ++i) {
            if( !isNotPrime[i] ) primes.add(i);
            for(int e: primes) {
                int x = e * i;
                if( x >= MAXN ) break;
                isNotPrime[x] = true;
                if( i % e == 0 ) break;
            }
        }
    }
}
