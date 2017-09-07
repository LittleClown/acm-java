/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils._classes.misc.triple;

/**
 * 三元组
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class Triple<A, B, C> {
    public A first;
    public B second;
    public C third;

    public Triple() {};

    public Triple(A a, B b, C c) {
        first = a;
        second = b;
        third = c;
    }

    public Triple<A, B, C> make_triple(A a, B b, C c) {
        return new Triple<A, B, C>(a, b, c);
    }
}
