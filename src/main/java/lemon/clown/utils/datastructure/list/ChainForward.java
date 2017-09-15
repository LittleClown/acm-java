/*~
 *  Created by lemon-clown on 2017/9/15
 */
package lemon.clown.utils.datastructure.list;

import java.util.Arrays;
import java.util.Iterator;

public class ChainForward {
    /**
     * MAXN: the max number of points.
     * MAXE: the max number of edges.
     * head:
     * next:
     * val:
     * size:
     */
    public final int MAXN;
    public final int MAXE;
    public final int[] head;
    public final int[] next;
    public final int[] val;
    protected int size;

    public ChainForward(int MAXN, int MAXE) {
        this.MAXN = MAXN;
        this.MAXE = MAXE;
        this.head = new int[MAXN];
        this.next = new int[MAXE];
        this.val = new int[MAXE];
    }

    /**
     * initialize this chain-forward.
     */
    public void init() {
        size = 0;
        Arrays.fill(head, -1);
    }

    /**
     * add edge &lt; u, v &gt;.
     * @param u
     * @param v
     */
    public void addEdge(int u, int v) {
        next[size] = head[u];
        head[u] = size;
        val[size++] = v;
    }

    public Iterable<Integer> forEach(int idx) {
        return forRemain(head[idx]);
    }

    public Iterable<Integer> forRemain(int idx) {
        return () -> new Iterator<Integer>() {
            int ct = idx;

            @Override
            public boolean hasNext() {
                return ct != -1;
            }

            @Override
            public Integer next() {
                int ret = val[ct];
                ct = next[ct];
                return ret;
            }
        };
    }

    public Iterable<Integer> forEachWithIndex(int idx) {
        return forRemainWithIndex(head[idx]);
    }

    public Iterable<Integer> forRemainWithIndex(int idx) {
        return () -> new Iterator<Integer>() {
            int ct = idx;

            @Override
            public boolean hasNext() {
                return ct != -1;
            }

            @Override
            public Integer next() {
                int ret = ct;
                ct = next[ct];
                return ct;
            }
        };
    }
}
