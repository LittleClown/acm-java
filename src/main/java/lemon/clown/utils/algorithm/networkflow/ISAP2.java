/*~
 *  Created by lemon-clown on 2017/9/15
 */
package lemon.clown.utils.algorithm.networkflow;

import lemon.clown.utils.datastructure.list.ChainForward;
import java.util.Arrays;

public class ISAP2 extends ISAP {
    /**
     * MAXE:    最大的边数
     * edges:   边表。 edges[e] 和 edges[e^1] 互为反向弧
     * G:       邻接表。 G[i][j] 表示节点 i 的第 j 条边在 edges 数组中的序号
     */
    public final int MAXE;
    protected final Edge[] edges;
    protected final ChainForward G;

    public ISAP2(int MAXN, int MAXE, int INF) {
        super(MAXN, INF);
        this.MAXE = MAXE * 2;
        G = new ChainForward(this.MAXN, this.MAXE);
        edges = new Edge[this.MAXE];
    }

    public ISAP2(int MAXN, int MAXE) {
        this(MAXN, MAXE, 0x3f3f3f3f);
    }

    @Override
    public void init(int source, int converge, int N) {
        super.init(source, converge, n);
        G.init();
    }

    @Override
    public void addEdge(int from, int to, int cap) {
        G.addEdge(from, m);
        edges[m++].set(from, to, cap, 0);
        G.addEdge(to, m);
        edges[m++].set(to, from, 0, 0);
    }

    @Override
    public int maxFlow() {
        BFS();
        System.arraycopy(G.head, 0, cur, 0, n);
        Arrays.fill(cnt, 0, n, 0);
        for(int i=0; i < n; ++i)
            if( dist[i] < n ) ++cnt[dist[i]];

        int ans = current_flow;
        for(int o=s; dist[o] < n;) {
            if( o == t ) {
                ans += augment();
                o = s;
            }

            boolean flag = false;
            for(int i: G.forRemainWithIndex(cur[o])) {
                int v = G.val[i];
                Edge e = edges[v];
                if( e.cap > e.flow && dist[o] == dist[e.to]+1 ) { // Advance
                    flag = true;
                    cur[o] = i;
                    path[e.to] = v;
                    o = e.to;
                    break;
                }
            }

            if( !flag ) {   // Retreat
                int d = n - 1;
                for(int i: G.forEach(o)) {
                    Edge e = edges[i];
                    if( e.cap > e.flow ) d = Math.min(d, dist[e.to]);
                }
                // gap 优化
                if( --cnt[dist[o]] == 0 ) break;
                ++cnt[ dist[o]=d+1 ];
                cur[o] = G.head[o];
                if( o != s ) o = edges[path[o]].from;
            }
        }
        return current_flow = ans;
    }

    @Override
    protected void BFS() {
        Arrays.fill(dist, 0, n, INF);
        dist[t] = 0;

        int front = 0, rear = 0;
        Q[rear++] = t;

        while( front < rear ) {
            int o = Q[front++];
            for(int i: G.forEach(o)) {
                Edge e = edges[i];
                if( dist[e.to] == INF && e.cap == 0 ) {
                    dist[e.to] = dist[o] + 1;
                    Q[rear++] = e.to;
                }
            }
        }
    }

    @Override
    protected int augment() {
        int mif = INF;
        for(int o=t; o != s;) {
            Edge e = edges[path[o]];
            mif = Math.min(mif, e.cap-e.flow);
            o = e.from;
        }
        for(int o=t; o != s;) {
            edges[path[o]].flow += mif;
            edges[path[o]^1].flow -= mif;
            o = edges[path[o]].from;
        }
        return mif;
    }
}
