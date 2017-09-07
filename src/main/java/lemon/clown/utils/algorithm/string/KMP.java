/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.algorithm.string;

import java.util.ArrayList;

/**
 *  获得模式串 P 在文本串 T 中的所有匹配点
 *  复杂度： O(N+M)
 */
public class KMP {
    /**
     * 计算失配指针数组
     * @param P         In:     模板串
     * @param len       In:     P 的长度
     * @param fail      Out:     失配指针数组
     */
    public static void getFail(char[] P, int len, int[] fail) {
        fail[0] = fail[1] = 0;
        for(int i=1; i < len; ++i) {
            int j = fail[i];
            while( j != 0 && P[j] != P[i] ) j = fail[j];
            fail[i+1] = (P[i] == P[j]? j+1: 0);
        }
    }

    /**
     * 计算失配指针数组
     * @param P         In:     模板串
     * @param offset    In:     P 的偏移量
     * @param len       In:     P 的长度
     * @param fail      Out:    失配指针数组
     */
    public static void getFail(char[] P, int offset, int len, int[] fail) {
        fail[0] = fail[1] = 0;
        for(int i=1; i < len; ++i) {
            int j = fail[i];
            while( j != 0 && P[j+offset] != P[i+offset] ) j = fail[j];
            fail[i+1] = (P[i+offset] == P[j+offset]? j+1: 0);
        }
    }

     /**
     * 计算失配指针数组
     * @param P         In:     模板串
     * @param fail      Out:     失配指针数组
     */
    public static void getFail(String P, int[] fail) {
        fail[0] = fail[1] = 0;
        int len = P.length();
        for(int i=1; i < len; ++i) {
            int j = fail[i];
            while( j != 0 && P.charAt(j) != P.charAt(i) ) j = fail[j];
            fail[i+1] = (P.charAt(i) == P.charAt(j)? j+1: 0);
        }
    }

    /**
     * 获得失配指针数组
     * @param P         In:     模板串
     * @param len       In:     P 的长度
     * @return          失配指针数组
     */
    public static int[] getFail(char[] P, int len) {
        int[] fail = new int[len+1];
        getFail(P, len, fail);
        return fail;
    }

    /**
     * 获得失配指针数组
     * @param P         In:     模板串
     * @param offset    In:     P 的偏移量
     * @param len       In:     P 的长度
     * @return          失配指针数组
     */
    public static int[] getFail(char[] P, int offset, int len) {
        int[] fail = new int[len+1];
        getFail(P, offset, len, fail);
        return fail;
    }

    /**
     * 获得失配指针数组
     * @param P         In:     模板串
     * @return          失配指针数组
     */
    public static int[] getFail(String P) {
        int[] fail = new int[P.length()+1];
        getFail(P, fail);
        return fail;
    }

    /**
     * 返回 P 在 T 中出现次数
     * @param P         In:     模板串
     * @param PLen      In:     P 的长度
     * @param T         In:     文本串
     * @param TLen      In:     T 的长度
     * @param fail      Out:    P 的失配指针数组
     * @return          P 在 T 中的匹配点的个数
     */
    public static int run(char[] P, int PLen, char[] T, int TLen, int[] fail) {
        int cnt = 0;
        for(int i=0, j=0; i < TLen; ++i) {
            while( j != 0 && P[j] != T[i] ) j = fail[j];
            if( P[j] == T[i] ) ++j;
            if( j == PLen ) {
                ++cnt;
                j = fail[j];
            }
        }
        return cnt;
    }

    /**
     * 返回 P 在 T 中出现次数
     * @param P         In:     模板串
     * @param POffset   In:     P 的偏移量
     * @param PLen      In:     P 的长度
     * @param T         In:     文本串
     * @param TOffset   In:     T 的偏移量
     * @param TLen      In:     T 的长度
     * @param fail      Out:    P 的失配指针数组
     * @return          P 在 T 中的匹配点的个数
     */
    public static int run(char[] P, int POffset, int PLen, char[] T, int TOffset, int TLen, int[] fail) {
        int cnt = 0;
        for(int i=0, j=0; i < TLen; ++i) {
            while( j != 0 && P[j+POffset] != T[i+TOffset] ) j = fail[j];
            if( P[j+POffset] == T[i+TOffset] ) ++j;
            if( j == PLen ) {
                ++cnt;
                j = fail[j];
            }
        }
        return cnt;
    }

    /**
     * 返回 P 在 T 中出现次数
     * @param P         In:     模板串
     * @param T         In:     文本串
     * @param fail      Out:    P 的失配指针数组
     * @return          P 在 T 中的匹配点的个数
     */
    public static int run(String P, String T, int[] fail) {
        int cnt = 0;
        int PLen = P.length();
        int TLen = T.length();
        for(int i=0, j=0; i < TLen; ++i) {
            while( j != 0 && P.charAt(j) != T.charAt(i) ) j = fail[j];
            if( P.charAt(j) == T.charAt(i) ) ++j;
            if( j == PLen ) {
                ++cnt;
                j = fail[j];
            }
        }
        return cnt;
    }

    /**
     * @param P         In:     模板串
     * @param PLen      In:     P 的长度
     * @param T         In:     文本串
     * @param TLen      In:     T 的长度
     * @param fail      In:     失配指针数组
     * @param points    Out:    P 在 T 中的所有匹配点
     */
    public static void run(char[] P, int PLen, char[] T, int TLen, int[] fail, ArrayList<Integer> points) {
        points.clear();
        for(int i=0, j=0; i < TLen; ++i) {
            while( j != 0 && P[j] != T[i] ) j = fail[j];
            if( P[j] == T[i] ) ++j;
            if( j == PLen ) {
                j = fail[j];
                points.add(i-PLen+1);
            }
        }
    }

    /**
     * @param P         In:     模板串
     * @param POffset   In:     P 的偏移量
     * @param PLen      In:     P 的长度
     * @param T         In:     文本串
     * @param TOffset   In:     T 的偏移量
     * @param TLen      In:     T 的长度
     * @param fail      In:     失配指针数组
     * @param points    Out:    P 在 T 中的所有匹配点
     */
    public static void run(char[] P, int POffset, int PLen, char[] T, int TOffset, int TLen, int[] fail, ArrayList<Integer> points) {
        points.clear();
        for(int i=0, j=0; i < TLen; ++i) {
            while( j != 0 && P[j+POffset] != T[i+TOffset] ) j = fail[j];
            if( P[j+POffset] == T[i+TOffset] ) ++j;
            if( j == PLen ) {
                j = fail[j];
                points.add(i-PLen+1);
            }
        }
    }

    /**
     * @param P         In:     模板串
     * @param T         In:     文本串
     * @param fail      In:     失配指针数组
     * @param points    Out:    P 在 T 中的所有匹配点
     */
    public static void run(String P, String T, int[] fail, ArrayList<Integer> points) {
        points.clear();
        int PLen = P.length();
        int TLen = T.length();
        for(int i=0, j=0; i < TLen; ++i) {
            while( j != 0 && P.charAt(j) != T.charAt(i) ) j = fail[j];
            if( P.charAt(j) == T.charAt(i) ) ++j;
            if( j == PLen ) {
                j = fail[j];
                points.add(i-PLen+1);
            }
        }
    }

    /**
     * 返回 P 在 T 中出现次数
     * @param P         In:     模板串
     * @param PLen      In:     P 的长度
     * @param T         In:     文本串
     * @param TLen      In:     T 的长度
     * @return          P 在 T 中的匹配点的个数
     */
    public static int run(char[] P, int PLen, char[] T, int TLen) {
        int[] fail = getFail(P, PLen);
        return run(P, PLen, T, TLen, fail);
    }

    /**
     * 返回 P 在 T 中出现次数
     * @param P         In:     模板串
     * @param POffset   In:     P 的偏移量
     * @param PLen      In:     P 的长度
     * @param T         In:     文本串
     * @param TOffset   In:     T 的偏移量
     * @param TLen      In:     T 的长度
     * @return          P 在 T 中的匹配点的个数
     */
    public static int run(char[] P, int POffset, int PLen, char[] T, int TOffset, int TLen) {
        int[] fail = getFail(P, POffset, PLen);
        return run(P, POffset, PLen, T, TOffset, TLen, fail);
    }

    /**
     * 返回 P 在 T 中出现次数
     * @param P         In:     模板串
     * @param T         In:     文本串
     * @return          P 在 T 中的匹配点的个数
     */
    public static int run(String P, String T) {
        int[] fail = getFail(P);
        return run(P, T, fail);
    }

    /**
     * @param P         In:     模板串
     * @param PLen      In:     P 的长度
     * @param T         In:     文本串
     * @param TLen      In:     T 的长度
     * @param points    Out:    P 在 T 中的所有匹配点
     * @return          失配指针数组
     */
    public static int[] run(char[] P, int PLen, char[] T, int TLen, ArrayList<Integer> points) {
        int[] fail = getFail(P, PLen);
        run(P, PLen, T, TLen, fail, points);
        return fail;
    }

    /**
     * @param P         In:     模板串
     * @param POffset   In:     P 的偏移量
     * @param PLen      In:     P 的长度
     * @param T         In:     文本串
     * @param TOffset   In:     T 的偏移量
     * @param TLen      In:     T 的长度
     * @param points    Out:    P 在 T 中的所有匹配点
     * @return          失配指针数组
     */
    public static int[] run(char[] P, int POffset, int PLen, char[] T, int TOffset, int TLen, ArrayList<Integer> points) {
        int[] fail = getFail(P, POffset, PLen);
        run(P, POffset, PLen, T, TOffset, TLen, fail, points);
        return fail;
    }

    /**
     * @param P         In:     模板串
     * @param T         In:     文本串
     * @param points    Out:    P 在 T 中的所有匹配点
     * @return          失配指针数组
     */
    public static int[] run(String P, String T, ArrayList<Integer> points) {
        int[] fail = getFail(P);
        run(P, T, fail, points);
        return fail;
    }
}
