/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.algorithm.misc.shuffle;

import lemon.clown.utils.algorithm.misc.CompareUtil;
import lemon.clown.utils.datastructure.tree.bit.BIT;
import lemon.clown.utils.datastructure.tree.bit.IntegerBIT;

/**
 * 约瑟夫环
 */
public class JosephCircle {
    public final int size;
    protected final BIT bit;
    protected int start;
    protected int step;
    protected int N;

    public JosephCircle(int size) {
        N = this.size = size;
        bit = new IntegerBIT(size);
    }

    /**
     * 初始化
     * @param start     In:     起始位置
     * @param N         In:     约瑟夫环的大小
     */
    public void init(int start, int N) {
        assert 1 <= start && start <= size: "Out of bound of array";
        this.N = N;
        this.start = start;
        bit.init(1, N);
    }

    /**
     * 初始化
     * @param start     In:     起始位置
     * @param N         In:     约瑟夫环的大小
     * @param step      In:     步长
     */
    public void init(int start, int N, int step) {
        this.step = step;
        init(start, N);
    }

    /**
     * 下一个位置
     * @param step      In:     步长
     * @return          下一个位置
     */
    public int next(int step) {
        int target = -1;
        while( step > 0 ) {
            target = CompareUtil.min(start+step-1, N);
            step -= (int) bit.sum(start, target);
            if( step <= 0 ) break;
            start = target % N + 1;
        }
        bit.add(target, -1);
        return target;
    }

    /**
     * 下一个位置
     * @return          下一个位置
     */
    public int next() {
        return next(step);
    }
}
