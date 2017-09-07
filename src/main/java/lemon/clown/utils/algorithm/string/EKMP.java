/*~
 *  Created by lemon-clown on 2017/8/1
 */
package lemon.clown.utils.algorithm.string;

/**
 *  求出模式串 P 在和文本串 T 的每一个后缀的最长公共前缀
 *  设模式串 P 的长度为 N, 文本串 T 的长度为 M
 *  复杂度 O(N+M)
 *
 *  算法描述：
 *      设 A[i] 为 P 与 P[i..N] 的最长公共前缀
 *      设 1 <= k < i
 *      那么，P[1..A[k]] = P[k..k+A[k]-1]
 *      于是，P[i..k+A[k]-1] = p[i-k+1..A[k]]
 *
 *      取使得 k+A[k]-1 最大的 k，记为 k'
 *      1. 如果 i+A[i-k'+1]-1 比 k'+A[k']-1 小，则 A[i]=A[i-k'+1]
 *      2. 否则，暴力求 A[i]
 *
 *      类似地，求出 P 与 T 的所有后缀的最长公共前缀
 */

public class EKMP {
    private static int min(int x, int y) { return x < y? x: y; }
    private static int max(int x, int y) { return x < y? y: x; }

    /**
     * 计算 P 与 P 的每个后缀的最长公共前缀
     * @param P     In:     模式串
     * @param len   In:     P 的长度
     * @param A     Out:    P 与 P 的每个后缀的最长公共前缀长度
     */
    public static void getFail(char[] P, int len, int[] A) {
        int i, j, k, x, y;
        for(i=0; i+1 < len && P[i] == P[i+1];) ++i;
        A[1] = i;

        for(i=2, k=1; i < len; ++i) {
            x = A[i-k];
            y = k + A[k] - i;
            if( x < y ) A[i] = x;
            else {
                for(j=max(0, y); i+j < len && P[j] == P[i+j];) ++j;
                A[i] = j;
                k = i;
            }
        }
    }

    /**
     * 计算 P 与 P 的每个后缀的最长公共前缀
     * @param P         In:     模式串
     * @param offset    In:     P 的偏移量
     * @param len       In:     P 的长度
     * @param A         Out:    P 与 P 的每个后缀的最长公共前缀长度
     */
    public static void getFail(char[] P, int offset, int len, int[] A) {
        int i, j, k, x, y;
        for(i=0; i+1 < len && P[i+offset] == P[i+1+offset];) ++i;
        A[1] = i;

        for(i=2, k=1; i < len; ++i) {
            x = A[i-k];
            y = k + A[k] - i;
            if( x < y ) A[i] = x;
            else {
                for(j=max(0, y); i+j < len && P[j+offset] == P[i+j+offset];) ++j;
                A[i] = j;
                k = i;
            }
        }
    }

    /**
     * 计算 P 与 P 的每个后缀的最长公共前缀
     * @param P     In:     模式串
     * @param A     Out:    P 与 P 的每个后缀的最长公共前缀长度
     */
    public static void getFail(String P, int[] A) {
        int i, j, k, x, y;
        int len = P.length();
        for(i=0; i+1 < len && P.charAt(i) == P.charAt(i+1);) ++i;
        A[1] = i;

        for(i=2, k=1; i < len; ++i) {
            x = A[i-k];
            y = k + A[k] - i;
            if( x < y ) A[i] = x;
            else {
                for(j=max(0, y); i+j < len && P.charAt(j) == P.charAt(i+j);) ++j;
                A[i] = j;
                k = i;
            }
        }
    }

    /**
     * 计算 P 与 P 的每个后缀的最长公共前缀
     * @param P         In:     模式串
     * @param len       In:     P 的长度
     * @return          P 与 P 的每个后缀的最长公共前缀长度
     */
    public static int[] getFail(char[] P, int len) {
        int[] A = new int[len+1];
        getFail(P, len, A);
        return A;
    }

    /**
     * 计算 P 与 P 的每个后缀的最长公共前缀
     * @param P         In:     模式串
     * @param offset    In:     P 的偏移量
     * @param len       In:     P 的长度
     * @return          P 与 P 的每个后缀的最长公共前缀长度
     */
    public static int[] getFail(char[] P, int offset, int len) {
        int[] A = new int[len+1];
        getFail(P, offset, len, A);
        return A;
    }

    /**
     * 计算 P 与 P 的每个后缀的最长公共前缀
     * @param P         In:     模式串
     * @return          P 与 P 的每个后缀的最长公共前缀长度
     */
    public static int[] getFail(String P) {
        int[] A = new int[P.length()+1];
        getFail(P, A);
        return A;
    }

    /**
     * @param P     In:     模式串
     * @param PLen  In:     P 的长度
     * @param T     In:     文本串
     * @param TLen  In:     T 的长度
     * @param A     Out:    P 与 P 的每个后缀的最长公共前缀长度
     * @param ret   Out:    P 与 T 的每个后缀的最长公共前缀长度
     */
    public static void run(char[] P, int PLen, char[] T, int TLen, int[] A, int[] ret) {
        int i, j, k, x, y, L = min(PLen, TLen);
        for(i=0; i < L && P[i] == T[i];) ++i;
        ret[0] = i;

        for(i=1, k=0; i < TLen; ++i) {
            x = A[i-k];
            y = k + ret[k] - i;
            if( x < y ) ret[i] = x;
            else {
                L = min(PLen, TLen-i);
                for(j=max(0, y); j < L && P[j] == T[i+j];) ++j;
                ret[i] = j;
                k = i;
            }
        }
    }

    /**
     * @param P         In:     模式串
     * @param POffset   In:     P 的偏移量
     * @param PLen      In:     P 的长度
     * @param T         In:     文本串
     * @param TOffset   In:     T 的偏移量
     * @param TLen      In:     T 的长度
     * @param A         Out:    P 与 P 的每个后缀的最长公共前缀长度
     * @param ret       Out:    P 与 T 的每个后缀的最长公共前缀长度
     */
    public static void run(char[] P, int POffset, int PLen, char[] T, int TOffset, int TLen, int[] A, int[] ret) {
        int i, j, k, x, y, L = min(PLen, TLen);
        for(i=0; i < L && P[i+POffset] == T[i+TOffset];) ++i;
        ret[0] = i;

        for(i=1, k=0; i < TLen; ++i) {
            x = A[i-k];
            y = k + ret[k] - i;
            if( x < y ) ret[i] = x;
            else {
                L = min(PLen, TLen-i);
                for(j=max(0, y); j < L && P[j+POffset] == T[i+j+TOffset];) ++j;
                ret[i] = j;
                k = i;
            }
        }
    }

    /**
     * @param P     In:     模式串
     * @param T     In:     文本串
     * @param A     Out:    P 与 P 的每个后缀的最长公共前缀长度
     * @param ret   Out:    P 与 T 的每个后缀的最长公共前缀长度
     */
    public static void run(String P, String T, int[] A, int[] ret) {
        int PLen = P.length();
        int TLen = T.length();
        int i, j, k, x, y, L = min(PLen, TLen);
        for(i=0; i < L && P.charAt(i) == T.charAt(i);) ++i;
        ret[0] = i;

        for(i=1, k=0; i < TLen; ++i) {
            x = A[i-k];
            y = k + ret[k] - i;
            if( x < y ) ret[i] = x;
            else {
                L = min(PLen, TLen-i);
                for(j=max(0, y); j < L && P.charAt(j) == T.charAt(i+j);) ++j;
                ret[i] = j;
                k = i;
            }
        }
    }

    /**
     * @param P     In:     模式串
     * @param PLen  In:     P 的长度
     * @param T     In:     文本串
     * @param TLen  In:     T 的长度
     * @param ret   Out:    P 与 T 的每个后缀的最长公共前缀长度 数组
     * @return      P 与 P 的每个后缀的最长公共前缀长度 数组
     */
    public static int[] run(char[] P, int PLen, char[] T, int TLen, int[] ret) {
        int[] A = getFail(P, PLen);
        run(P, PLen, T, TLen, A, ret);
        return A;
    }

    /**
     * @param P         In:     模式串
     * @param POffset   In:     P 的偏移量
     * @param PLen      In:     P 的长度
     * @param T         In:     文本串
     * @param TOffset   In:     T 的偏移量
     * @param TLen      In:     T 的长度
     * @param ret       Out:    P 与 T 的每个后缀的最长公共前缀长度 数组
     * @return          P 与 P 的每个后缀的最长公共前缀长度 数组
     */
    public static int[] run(char[] P, int POffset, int PLen, char[] T, int TOffset, int TLen, int[] ret) {
        int[] A = getFail(P, POffset, PLen);
        run(P, POffset, PLen, T, TOffset, TLen, A, ret);
        return A;
    }

    /**
     * @param P     In:     模式串
     * @param T     In:     文本串
     * @param ret   Out:    P 与 T 的每个后缀的最长公共前缀长度 数组
     * @return      P 与 P 的每个后缀的最长公共前缀长度 数组
     */
    public static int[] run(String P, String T, int[] ret) {
        int[] A = getFail(P);
        run(P, T, A, ret);
        return A;
    }
}
