/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.datastructure.tree.bit;

import lemon.clown.utils.operations.Addable;
import lemon.clown.utils.operations.Subtractable;
import lemon.clown.utils.operations.ZeroElement;
import lemon.clown.utils.datastructure.tree.bit.BIT;

import java.lang.reflect.Array;

public class SimpleBIT<T extends Addable<T> & Subtractable<T> & ZeroElement<T>> implements BIT<T> {
    protected int N;
    final public int size;
    final public T[] nodes;
    final T instance;

    public SimpleBIT(int size, Class<T> classType, T instance) {
        N = this.size = size;
        this.instance = instance;
        nodes = (T[]) Array.newInstance(classType, size+1);
    }

    public void init(T val) {
        for(int i=1; i <= N; ++i)
            nodes[i] = val.multiply(BIT.lower_bit(i));
    }

    public void init(T[] A) {
        for(int i=1; i <= N; ++i) {
            T s = sum(i-1).subtract(sum(i-BIT.lower_bit(i)));
            nodes[i] = s.add(A[i]);
        }
    }

    public void initWithPrefixSum(T[] PS) {
        for(int i=1; i <= N; ++i)
            nodes[i] = PS[i].subtract(PS[i-BIT.lower_bit(i)]);
    }

    @Override
    public void init(T val, int N) {
        assert 0 < N && N <= size : "Out of bound of array";
        this.N = N;
        init(val);
    }

    @Override
    public void init(T[] A, int N) {
        assert 0 < N && N <= size : "Out of bound of array";
        this.N = N;
        init(A);
    }

    @Override
    public void initWithPrefixSum(T[] PS, int N) {
        assert 0 < N && N <= size : "Out of bound of array";
        this.N = N;
        initWithPrefixSum(PS);
    }

    @Override
    public T sum(int x) {
        T ret = instance.getZeroElement();
        for(; x > 0; x^=BIT.lower_bit(x))
            ret.addAndSet(nodes[x]);
        return ret;
    }

    @Override
    public T sum(int lft, int rht) {
        return sum(rht).subtract(sum(lft-1));
    }

    @Override
    public void add(int x, T v) {
        if( x <= 0 ) return;
        for(; x <= N; x+=BIT.lower_bit(x))
            nodes[x].addAndSet(v);
    }

    @Override
    public void set(int x, T v) {
        if( x <= 0 ) return;
        T delta = v.subtract(nodes[x]);
        add(x, delta);
    }
}
