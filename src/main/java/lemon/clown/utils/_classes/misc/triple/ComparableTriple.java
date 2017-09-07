/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils._classes.misc.triple;

/*
 * 可以执行比较的三元组，对于两个三元组 <a,b,c> 和 <d,e,f>，其大小为：
 * 若 a != d， 返回 a 和 c 的大小关系
 * 否则，若 b != e，返回 b 和 e 的大小关系
 * 否则，返回 c 和 f 的大小关系
 */
public class ComparableTriple<A extends Comparable<A>, B extends Comparable<B>, C extends Comparable<C>>
    extends Triple<A, B, C> implements Comparable<ComparableTriple<A, B, C>> {

    public ComparableTriple(A a, B b, C c) {
        super(a, b, c);
    }

    @Override
    public int compareTo(ComparableTriple<A, B, C> o) {
        int delta = first.compareTo(o.first);
        if( delta == 0 ) {
            delta = second.compareTo(o.second);
            if( delta == 0 ) return third.compareTo(o.third);
        }
        return delta;
    }
}
