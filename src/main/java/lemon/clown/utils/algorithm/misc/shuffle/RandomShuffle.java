/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.algorithm.misc.shuffle;

import lemon.clown.utils.algorithm.misc.shuffle.Shuffle;

import java.lang.reflect.Array;
import java.util.Random;

public class RandomShuffle<T> implements Shuffle<T> {
    protected final Random random = new Random();
    protected final JosephCircle jc;
    protected final Class<T> classType;
    public final int size;
    protected T[] assist_array;

    public RandomShuffle(int size, Class<T> classType) {
        this.size = size;
        jc = new JosephCircle(size);
        this.classType = classType;
    }

    public void init(int start, int N) {
        assert N <= size: "Out of bound of array:\n";
        jc.init(start, N);
    }

    public void init(int N) {
        assert N <= size: "Out of bound of array:\n";
        jc.init(1, N);
    }

    @Override
    public int[] getShuffleOrders(int N) {
        int[] orders = new int[N];
        init(N);
        for(int siz=N, i=0; siz > 0; --siz, ++i) {
            int step = random.nextInt(siz) + 1;
            orders[i] = jc.next(step) - 1;
        }
        return orders;
    }

    @Override
    public void shuffle(T[] targets, int offset, int length) {
        if( assist_array == null )
            assist_array = (T[]) Array.newInstance(classType, size+1);

        init(length);
        System.arraycopy(targets, offset, assist_array, 1, length);
        for(int size=length, i=offset; size > 0; --size, ++i) {
            int step = random.nextInt(size) + 1;
            int next = jc.next(step);
            targets[i] = assist_array[next];
        }
    }
}
