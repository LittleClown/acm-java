/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.algorithm.misc.shuffle;

import java.util.Random;
import lemon.clown.utils.algorithm.misc.shuffle.Shuffle;

public class FastRandomShuffle<T> implements Shuffle<T> {
    private Random random = new Random();

    @Override
    public int[] getShuffleOrders(int N) {
        int[] orders = new int[N];
        for(int i=0; i < N; ++i) orders[i] = i;

        for(int x=0, y, tmp; x < N; ++x) {
            y = random.nextInt(N);
            tmp = orders[x];
            orders[x] = orders[y];
            orders[y] = tmp;
        }
        return orders;
    }

    @Override
    public void shuffle(T[] targets, int offset, int length) {
        assert offset >= 0 && offset < targets.length && offset+length <= targets.length
                 : "Out of bound of array";

        T target;
        for(int x=offset, y, e=offset+length; x < e; ++x) {
            y = random.nextInt(length) + offset;
            target = targets[x];
            targets[x] = targets[y];
            targets[y] = target;
        }
    }
}
