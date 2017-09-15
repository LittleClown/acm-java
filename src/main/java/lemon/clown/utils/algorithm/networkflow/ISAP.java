/*~
 *  Created by lemon-clown on 2017/9/14
 */
package lemon.clown.utils.algorithm.networkflow;

import lemon.clown.utils.algorithm.networkflow.NetworkFlow;
import lemon.clown.utils.algorithm.networkflow.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ISAP implements NetworkFlow {
    /**
     * INF:     infinity
     * MAXN:    最大的节点个数
     * cnt:     用于优化的辅助数组
     * cur:     当前弧下标
     * path:    增广路
     * Q:       BFS 的辅助队列
     * dist:    距离汇点的距离
     * edges:   边表。 edges[e] 和 edges[e^1] 互为反向弧
     * G:       邻接表。 G[i][j] 表示节点 i 的第 j 条边在 edges 数组中的序号
     */
    public final int INF;
    public final int MAXN;
    public final int[] cnt;
    public final int[] cur;
    public final int[] path;
    public final int[] dist;
    protected final int[] Q;
    protected final List<Edge> edges = new ArrayList<>();
    protected final List<Integer>[] G;

    /**
     * s:   源点
     * t:   汇点
     * m:   边数
     * n:   节点数
     */
    protected int s, t, m, n, current_flow;

    public ISAP(int INF, int MAXN) {
        this.INF = INF;
        this.MAXN = MAXN;
        cnt = new int[MAXN];
        cur = new int[MAXN];
        path = new int[MAXN];
        dist = new int[MAXN];
        Q = new int[MAXN];

        G = new List[MAXN];
        for(int i=0; i < MAXN; ++i) G[i] = new ArrayList<>();
    }

    public ISAP(int MAXN) {
        this(0x3f3f3f3f, MAXN);
    }

    @Override
    public void init(int source, int converge, int N) {
        assert N <= MAXN: "N is bigger than maxn. " + N;
        s = source;
        t = converge;
        n = N;
        m = 0;
        current_flow = 0;
        for(int i=0; i < n; ++i) G[i].clear();
        edges.clear();
    }

    @Override
    public void init(int source, int converge) {
        init(source, converge, n);
    }

    @Override
    public void init() {
        init(s, t, n);
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
