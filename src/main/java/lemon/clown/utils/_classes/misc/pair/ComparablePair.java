/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils._classes.misc.pair;

/*
 * 可以执行比较的二元组，对于两个二元组 <a,b> 和 <c,d>，其大小为：
 * 若 a == c，则返回 b 和 d 的大小关系
 * 否则，返回 a 和 c 的大小关系
 */
public class ComparablePair<A extends Comparable<A>, B extends Comparable<B>>
        extends Pair<A, B> implements Comparable<ComparablePair<A, B>> {

    public ComparablePair() {
        super();
    }

    public ComparablePair(A a, B b) {
        super(a, b);
    }

    @Override
    public int compareTo(ComparablePair<A, B> o) {
        int delta = first.compareTo(o.first);
        if( delta == 0 ) return second.compareTo(o.second);
        return delta;
    }
}
