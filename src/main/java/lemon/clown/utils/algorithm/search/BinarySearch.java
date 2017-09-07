/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.algorithm.search;

import java.util.Comparator;
import lemon.clown.utils.algorithm.search.Search;

/**
 * 二分查找
 * @param <T>
 */
public class BinarySearch<T> implements Search<T> {
    private static final int MIN_LENGTH = 4;
    private Comparator<T> comparator;

    public BinarySearch() {}

    public BinarySearch(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int assist_lower_bound(T[] targets, int begin, int end, T target) {
        while( begin < end && comparator.compare(targets[begin], target) < 0 ) ++begin;
        return begin;
    }

    private int assist_upper_bound(T[] targets, int begin, int end, T target) {
        while( begin < end && comparator.compare(targets[begin], target) <= 0 ) ++begin;
        return begin;
    }

    @Override
    public int lower_bound(T[] targets, int begin, int end, T target) {
        assert comparator != null: "comparator is null!";
        assert begin < end && 0 <= begin && end <= targets.length
                : "Out of bound of array";
        if( end-begin <= MIN_LENGTH )
            return assist_lower_bound(targets, begin, end, target);

        while( begin < end ) {
            int mid = begin+end >> 1;
            if( comparator.compare(targets[mid], target) >= 0 ) end = mid;
            else begin = mid + 1;
        }
        return end;
    }

    @Override
    public int upper_bound(T[] targets, int begin, int end, T target) {
        assert comparator != null: "comparator is null!";
        assert begin < end && 0 <= begin && end <= targets.length
                : "Out of bound of array";
        if( end-begin <= MIN_LENGTH )
            return assist_upper_bound(targets, begin, end, target);

        while( begin < end ) {
            int mid = begin+end >> 1;
            if( comparator.compare(targets[mid], target) > 0 ) end = mid;
            else begin = mid + 1;
        }
        return end;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }
}
