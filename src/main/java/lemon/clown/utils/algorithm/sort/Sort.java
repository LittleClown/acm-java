/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.algorithm.sort;

import java.util.Comparator;

public interface Sort<T> {
    /**
     * 排序
     * @param targets   待排序数组
     * @param offset    起始位置的偏移量
     * @param length    待排序的长度
     */
    void sort(T[] targets, int offset, int length);

    /**
     * 设置比较器
     * @param comparator 用于排序的比较器
     */
    void setComparator(Comparator<T> comparator);
}
