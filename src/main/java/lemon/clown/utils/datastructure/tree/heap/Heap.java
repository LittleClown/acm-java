package lemon.clown.utils.datastructure.tree.heap;

import java.util.Comparator;

/**
 * 堆
 */
public interface Heap<T> {
    /**
     * 通过 targets 数组来构建堆，复杂度 O(N)
     * @param targets   目标数组
     * @param offset
     * @param length
     */
    void build(T[] targets, int offset, int length);

    /**
     * 清空堆
     */
    void clear();

    /**
     * 添加元素
     * @param x
     */
    void push(T x);

    /**
     * 弹出栈顶元素，并将栈顶元素返回
     * @return
     */
    T pop();

    /**
     * 返回栈顶元素
     * @return  若栈顶元素不存在，返回 null
     */
    T top();

    /**
     * 返回当前堆的元素个数
     * @return
     */
    int size();

    /**
     * 判断当前堆是否为空
     * @return  若为空返回 true，否则返回 false
     */
    boolean empty();

    /**
     * 设置比较器
     * @param comparator
     */
    void setComparator(Comparator<T> comparator);
}
