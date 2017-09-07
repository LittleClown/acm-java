/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.algorithm.sort;

import java.util.Comparator;
import lemon.clown.utils.algorithm.sort.Sort;

/**
 * 优化排序
 * @param <T>
 */
public class AssistanceOptimizeSort<T> implements Sort<T> {
    Comparator<T> comparator;

    @Override
    public void sort(T[] targets, int offset, int length) {
        int end = offset + length;
        for(int i=offset; i < end; ++i) {
            T target = targets[i];
            int j = i-1;
            for(; j >= offset; --j) {
                if( comparator.compare(targets[j], target) <= 0 ) break;
                targets[j+1] = targets[j];
            }
            targets[j+1] = target;
        }
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void sort(T[] targets, int offset, int length, Comparator<T> comparator) {
        setComparator(comparator);
        sort(targets, offset, length);
    }
}
