/*~
 *  Created by lemon-clown on 2017/8/1
 */
package lemon.clown.utils.algorithm.string;


/**
 *  计算最长回文子串
 *  复杂度 O(N)
 */
public class Manacher {
    private static int min(int x, int y) { return x < y? x: y; }

    /**
     * @param T         In:     文本串
     * @param len       Out:    len 数组：
     *                              1. i \equiv 0 \mod 2: 以 T[i>>1] 为中心的最长回文子串长度的一半（上取整）
     *                              2. i \equiv 1 \mod 2: 以 T[i>>1] 和 T[i>>1|1] 为中心的最长回文子串长度的一半（上取整）
     * @return          len 的有效长度
     */
    public static int run(String T, int len[]) {
        int N = T.length();
        int length = N * 2 - 1;
        len[0] = 1;
        for(int i=1, j=0; i < length; ++i) {
            int lft = i >> 1;
            int rht = i - lft;
            int rst = ((j+1)>>1) + len[j] - 1;
            len[i] = rst < rht? 0: min(rst-rht+1, len[(j<<1)-i]);
            while( lft >= len[i] && rht+len[i] < N && T.charAt(lft-len[i]) == T.charAt(rht+len[i])) ++len[i];
            if( rht+len[i] >= rst ) j = i;
        }
        return length;
    }

    /**
     * @param T         In:     文本串
     * @return          len 数组：
     *                      1. i \equiv 0 \mod 2: 以 T[i>>1] 为中心的最长回文子串长度的一半（上取整）
     *                      2. i \equiv 1 \mod 2: 以 T[i>>1] 和 T[i>>1|1] 为中心的最长回文子串长度的一半（上取整）
     */
    public static int[] run(String T) {
        int[] len = new int[T.length()*2-1];
        run(T, len);
        return len;
    }

    /**
     * @param p     In:     len 下标
     * @param v     In:     len[p]
     * @return      以 p>>1 和 p-(p>>1) 为中心的最长回文串长度
     */
    public static int parse(int p, int v) {
        v <<= 1;
        if( (p&1) == 0 ) --v;
        return v;
    }

    /**
     * 计算最长回文子串的长度
     * @param len       In:     通过 run 计算得到的记录了回文子串信息的数组
     * @param length    In:     len 的有效长度
     * @return          最长回文子串的长度
     */
    public static int maxLength(int[] len, int length) {
        int ans = 0;
        for(int i=0; i < length; ++i) {
            int target = len[i] << 1;
            if( (i&1) == 0 ) --target;
            if( target > ans ) ans = target;
        }
        return ans;
    }
}
