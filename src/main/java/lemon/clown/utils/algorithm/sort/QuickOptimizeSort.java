/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.algorithm.sort;

import java.util.Comparator;
import java.util.Random;

public class QuickOptimizeSort<T> implements OptimizeSort<T> {
    private Random random = new Random();
    private Comparator<T> comparator;

    private int limitOptimizeLength = 4;
    private AssistanceOptimizeSort assistanceOptimizeSort = new AssistanceOptimizeSort();

    public QuickOptimizeSort() {}

    public QuickOptimizeSort(Comparator<T> comparator) {
        setComparator(comparator);
    }

    @Override
    public void setOptimizeLength(int limitOptimizeLength) {
        this.limitOptimizeLength = limitOptimizeLength;
    }

    @Override
    public void sort(T[] targets, int offset, int length) {
        int end = offset + length;
        assert offset >= 0 && offset < targets.length && end < targets.length
                : "Out of bound of array";
        if( length > 1 ) quickOptimizeSort(targets, offset, end);
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
        assistanceOptimizeSort.setComparator(comparator);
    }

    private void quickOptimizeSort(T[] targets, int begin, int end) {
        if( end-begin <= limitOptimizeLength ) {
            assistanceOptimizeSort.sort(targets, begin, end);
            return;
        }

        int lft = begin, rht = end-1;
        int mid = lft + random.nextInt(end-begin);

        T target = targets[mid];
        while( lft <= rht ) {
            while( comparator.compare(targets[lft], target) < 0 ) ++lft;
            while( comparator.compare(targets[rht], target) > 0 ) --rht;
            if( lft <= rht ) {
                T tmp = targets[lft];
                targets[lft] = targets[rht];
                targets[rht] = tmp;
                ++lft; --rht;
            }
        }

        ++rht;
        if( begin+1 < rht ) quickOptimizeSort(targets, begin, rht);
        if( lft+1 < end ) quickOptimizeSort(targets, lft, end);
    }
}
