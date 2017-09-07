package lemon.clown.utils.datastructure.tree.bit;

/**
 * 树状数组
 * @param <T>
 */
public interface BIT<T> {
    /**
     * 将 [1,N] 全初始化为 val
     * @param val
     * @param N
     */
    void init(T val, int N);

    /**
     * 用数组 A 初始化树状数组
     * @param A
     * @param N
     */
    void init(T[] A, int N);

    /**
     * 用已经计算好前缀和的 PS 数组来初始化树状数组
     * @param PS
     * @param N
     */
    void initWithPrefixSum(T[] PS, int N);

    /**
     * 获取 lower-bit
     * @param x
     * @return
     */
    static int lower_bit(int x) {
        return x & -x;
    }

    /**
     * 计算 [1, x] 的区间和
     * @param x
     * @return
     */
    T sum(int x);

    /**
     * 计算 [lft, rht] 的区间和
     * @param lft
     * @param rht
     * @return
     */
    T sum(int lft, int rht);

    /**
     * 单点加值，将位置 x 的值 +v
     * @param x     目标位置
     * @param v     要加的值
     */
    void add(int x, T v);

    /**
     * 单点更新，将位置 x 的值设为 v
     * @param x     目标位置
     * @param v     更新后的值
     */
    void set(int x, T v);
}
