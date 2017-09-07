package lemon.clown.utils.algorithm.search;

public interface Search<T> {
    /**
     * 找到第一个大于或等于 target 的数组下标
     * @param targets       In:     源数组
     * @param begin         In:     左边界（闭）
     * @param end           In:     右边界（开）
     * @param target        In:     目标值
     * @return              第一个大于或等于 target 的元素的下标
     */
    int lower_bound(T[] targets, int begin, int end, T target);

    /**
     * 找到第一个大于 target 的数组下标
     * @param targets       In:     源数组
     * @param begin         In:     左边界（闭）
     * @param end           In:     右边界（开）
     * @param target        In:     目标值
     * @return              第一个大于 target 的元素的下标
     */
    public int upper_bound(T[] targets, int begin, int end, T target);
}
