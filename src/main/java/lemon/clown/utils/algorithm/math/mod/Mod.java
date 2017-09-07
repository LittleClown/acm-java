/*~
 *  Created by lemon-clown on 2017/9/6
 */
package lemon.clown.utils.algorithm.math.mod;

public class Mod {
    public static int GCD(int a, int b) {
        return b != 0? GCD(b, a%b): a;
    }

    public static int Power(int a, int x, int MOD) {
        int ans = 1;
        for(; x > 0; x>>=1, a=a*a%MOD)
            if( (x&1) != 0 ) ans = ans*a % MOD;
        return ans;
    }

    public static long Power(long a, long x, long MOD) {
        long ans = 1;
        for(; x > 0; x>>=1, a=a*a%MOD)
            if( (x&1) != 0 ) ans = ans*a % MOD;
        return ans;
    }
    
    public static <T, M> Multiplicable<T, M> Power(Multiplicable<T, M> a, long x, M MOD) {
        Multiplicable<T, M> ans = a.getInstanceAsOne();
        for(; x > 0; x>>=1, a=a.multiply(a).mod(MOD))
            if( (x&1) != 0 ) ans = ans.multiply(a).mod(MOD);
        return ans;
    }
}
