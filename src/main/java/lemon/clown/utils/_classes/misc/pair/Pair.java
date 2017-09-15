/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils._classes.misc.pair;

/**
 * 二元组
 * @param <A>
 * @param <B>
 */
public class Pair<A, B> {
    public A first;
    public B second;

    public Pair(A a, B b) {
        first = a;
        second = b;
    }

    public static <A, B> Pair<A, B> make_pair(A a, B b) {
        return new Pair<A, B>(a, b);
    }
}
