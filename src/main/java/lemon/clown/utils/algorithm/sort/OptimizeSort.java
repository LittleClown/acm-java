package lemon.clown.utils.algorithm.sort;


/**
 * 优化的排序（在区间长度很短时使用插入排序）
 */
public interface OptimizeSort<T> extends Sort<T> {
    /**
     * 设置当区间长度小于等于 limitLength 时，进行优化排序
     * @param limitLength   临界长度
     */
    void setOptimizeLength(int limitLength);
}
