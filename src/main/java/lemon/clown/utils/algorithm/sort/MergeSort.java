/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.algorithm.sort;

import java.lang.reflect.Array;
import java.util.Comparator;
import lemon.clown.utils.algorithm.sort.Sort;

public class MergeSort<T> implements Sort<T> {
    private Comparator<T> comparator;
    private T[] assist_array;

    public MergeSort() {}

    public MergeSort(Comparator<T> comparator) {
        setComparator(comparator);
    }

    @Override
    public void sort(T[] targets, int offset, int length) {
        int end = offset + length;
        assert length >= 1 && offset >= 0 && offset < targets.length && end <= targets.length
                : "Out of bound of array:\nlength: " + targets.length + ", end: " + end;
        assert assist_array != null: "You haven't set the `assist_array` yet.";
        mergeSort(targets, offset, end);
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void setAssistArray(T[] assist_array) {
        this.assist_array = assist_array;
    }

    public void setAssistArray(Class<T> classType, int size) {
        this.assist_array = (T[]) Array.newInstance(classType, size);
    }

     /**
     * 合并两个有序数组
     * @param A         In:     第一个数组
     * @param B         In:     第二个数组
     * @param C         Out:    保存合并后的两个有序数组
     * @param ABegin    In:     A 的起始坐标（闭）
     * @param AEnd      In:     A 的末尾坐标（开)
     * @param BBegin    In:     B 的起始坐标（闭）
     * @param BEnd      In:     B 的末尾坐标（开)
     * @param CBegin    In:     C 的起始坐标（闭）
     */
    public void mergeOrderedArray(T[] A, T[] B, T[] C, int ABegin, int AEnd, int BBegin, int BEnd, int CBegin) {
        int i = ABegin, j = BBegin, k = CBegin;
        while( i < AEnd && j < BEnd ) {
            if( comparator.compare(A[i], B[j]) < 0 ) C[k++] = A[i++];
            else C[k++] = B[j++];
        }
        while( i < AEnd ) C[k++] = A[i++];
        while( j < BEnd ) C[k++] = B[j++];
    }

    /**
     * 对 targets 进行排序
     * @param targets   目标数组
     * @param begin     起始坐标（闭）
     * @param end       结束坐标（开）
     */
    private void mergeSort(T[] targets, int begin, int end) {
        int length = end - begin;
        if( length <= 1 ) return;

        int mid = begin + (length>>1);
        mergeSort(targets, begin, mid);
        mergeSort(targets, mid, end);
        mergeOrderedArray(targets, targets, assist_array, begin, mid, mid, end, begin);
        System.arraycopy(assist_array, begin, targets, begin, end-begin);
    }
}
