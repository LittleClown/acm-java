package lemon.clown.utils.algorithm.misc.shuffle;

/**
 * 扰动数据
 */
public interface Shuffle<T> {
    /**
     * 返回 0~N-1 的一个乱序排列
     * @param N
     * @return
     */
    int[] getShuffleOrders(int N);

    /**
     * 将 targets 数组的元素打乱
     * @param targets   目标数组
     * @param offset    起始偏移量
     * @param length    要打乱的长度
     */
    void shuffle(T[] targets, int offset, int length);

    /**
     * 将整个 targets 数组的元素打乱
     * @param targets
     */
    default void shuffle(T[] targets) {
        shuffle(targets, 0, targets.length);
    }
}
