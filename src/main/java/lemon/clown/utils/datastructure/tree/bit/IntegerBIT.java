/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.datastructure.tree.bit;

import lemon.clown.utils.datastructure.tree.bit.BIT;

public class IntegerBIT implements BIT<Integer> {
    protected int N;
    final public int size;
    final public int[] nodes;

    public IntegerBIT(int size) {
        N = this.size = size;
        nodes = new int[size+1];
    }

    public void init(int val) {
        for(int i=1; i <= N; ++i)
            nodes[i] = val * BIT.lower_bit(i);
    }

    public void init(Integer[] A) {
        for(int i=1; i <= N; ++i) {
            int s = sum(i-1) - sum(i-BIT.lower_bit(i)) + A[i];
            nodes[i] = s;
        }
    }

    public void initWithPrefixSum(Integer[] PS) {
        for(int i=1; i <= N; ++i)
            nodes[i] = PS[i] - PS[i-BIT.lower_bit(i)];
    }

    @Override
    public void init(Integer val, int N) {
        assert 0 < N && N <= size : "Out of bound of array";
        this.N = N;
        init(val);
    }

    @Override
    public void init(Integer[] A, int N) {
        assert 0 < N && N <= size : "Out of bound of array";
        this.N = N;
        init(A);
    }

    @Override
    public void initWithPrefixSum(Integer[] PS, int N) {
        assert 0 < N && N <= size : "Out of bound of array";
        this.N = N;
        initWithPrefixSum(PS);
    }

    @Override
    public Integer sum(int x) {
        int ret = 0;
        for(; x > 0; x^=BIT.lower_bit(x))
            ret += nodes[x];
        return ret;
    }

    @Override
    public Integer sum(int lft, int rht) {
        return sum(rht) - sum(lft-1);
    }

    @Override
    public void add(int x, Integer v) {
        if( x <= 0 ) return;
        for(; x <= N; x+=BIT.lower_bit(x))
            nodes[x] += v;
    }

    @Override
    public void set(int x, Integer v) {
        if( x <= 0 ) return;
        int delta = v - nodes[x];
        add(x, delta);
    }
}
