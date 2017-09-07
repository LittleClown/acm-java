/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.datastructure.tree.heap;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * 小根堆
 * @param <T>
 */
public class SmallRootHeap<T> implements Heap<T> {
    int tot;
    T[] node_pool;
    Comparator<T> comparator;

    public SmallRootHeap(Comparator<T> comparator, Class<T> classType, int POOL_SIZE) {
        this.comparator = comparator;
        node_pool = (T[]) Array.newInstance(classType, POOL_SIZE+1);
        tot = 0;
    }

    @Override
    public void build(T[] targets, int offset, int length) {
        assert node_pool.length >= length;
        System.arraycopy(targets, offset, node_pool, 1, length);

        tot = length;
        for(int i=tot>>1; i > 0; --i) down(i);
    }

    @Override
    public void clear() {
        tot = 0;
    }

    @Override
    public void push(T x) {
        assert tot < node_pool.length;
        node_pool[++tot] = x;
        up(tot);
    }

    @Override
    public T pop() {
        assert tot > 0;
        T o = node_pool[1];
        node_pool[1] = node_pool[tot--];
        down(1);
        return o;
    }

    @Override
    public T top() {
        return node_pool[1];
    }

    @Override
    public int size() {
        return tot;
    }

    @Override
    public boolean empty() {
        return tot == 0;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private void up(int p) {
        T o = node_pool[p];
        for(int q=p>>1; q > 0 && comparator.compare(node_pool[q], o) > 0; p=q, q>>=1)
            node_pool[p] = node_pool[q];
        node_pool[p] = o;
    }

    private void down(int p) {
        T o = node_pool[p];
        for(int q=p<<1; q <= tot; p=q, q<<=1) {
            if( q < tot && comparator.compare(node_pool[q], node_pool[q|1]) > 0 ) q |= 1;
            if( comparator.compare(node_pool[q], o) >= 0 ) break;
            node_pool[p] = node_pool[q];
        }
        node_pool[p] = o;
    }
}
