/*~
 *  Created by lemon-clown on 2017/9/14
 */
package lemon.clown.utils.algorithm.networkflow;

import lemon.clown.utils.algorithm.networkflow.ISAP;
import lemon.clown.utils.algorithm.networkflow.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ISAP1 extends ISAP {
    /**
     * m:               边数
     * current_flow:    当前参与网络中的流量
     * edges:           边表。 edges[e] 和 edges[e^1] 互为反向弧
     * G:               邻接表。 G[i][j] 表示节点 i 的第 j 条边在 edges 数组中的序号
     */
    protected int m, current_flow;
    protected final List<Edge> edges = new ArrayList<>();
    protected final List<Integer>[] G;

    public ISAP1(int MAXN, int INF) {
        super(MAXN, INF);
        G = new List[MAXN];
        for(int i=0; i < MAXN; ++i) G[i] = new ArrayList<>();
    }

    public ISAP1(int MAXN) {
        this(MAXN, 0x3f3f3f3f);
    }

    @Override
    public void init(int source, int converge, int N) {
        super.init(source, converge, N);
        current_flow = 0;
        edges.clear();
        for(int i=0; i < n; ++i) G[i].clear();
    }

    @Override
    public void addEdge(int from, int to, int cap) {
        edges.add(new Edge(from, to, cap, 0));
        edges.add(new Edge(to, from, 0, 0));
        G[from].add(m++);
        G[to].add(m++);
    }

    @Override
    public int maxFlow() {
        BFS();
        Arrays.fill(cur, 0, n, 0);
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
            for(int i=cur[o]; i < G[o].size(); ++i) {
                Edge e = edges.get(G[o].get(i));
                if( e.cap > e.flow && dist[o] == dist[e.to]+1 ) { // Advance
                    flag = true;
                    cur[o] = i;
                    path[e.to] = G[o].get(i);
                    o = e.to;
                    break;
                }
            }

            if( !flag ) {   // Retreat
                int d = n - 1;
                for(int i: G[o]) {
                    Edge e = edges.get(i);
                    if( e.cap > e.flow ) d = Math.min(d, dist[e.to]);
                }
                // gap 优化
                if( --cnt[dist[o]] == 0 ) break;
                ++cnt[ dist[o]=d+1 ];
                cur[o] = 0;
                if( o != s ) o = edges.get(path[o]).from;
            }
        }
        return current_flow = ans;
    }

    /**
     * 计算层次图： dist 的值
     */
    protected void BFS() {
        Arrays.fill(dist, 0, n, INF);
        dist[t] = 0;

        int front = 0, rear = 0;
        Q[rear++] = t;
        while( front < rear ) {
            int o = Q[front++];
            for(int i: G[o]) {
                Edge e = edges.get(i);
                if( dist[e.to] == INF && e.cap == 0 ) {
                    dist[e.to] = dist[o] + 1;
                    Q[rear++] = e.to;
                }
            }
        }
    }

    /**
     * 增广操作，让这条增广路满流
     * @return  增广路的流量
     */
    protected int augment() {
        int mif = INF;
        for(int o=t; o != s;) {
            Edge e = edges.get(path[o]);
            mif = Math.min(mif, e.cap-e.flow);
            o = e.from;
        }
        for(int o=t; o != s;) {
            edges.get(path[o]).flow += mif;
            edges.get(path[o]^1).flow -= mif;
            o = edges.get(path[o]).from;
        }
        return mif;
    }
}
