/*~
 *  Created by lemon-clown on 2017/9/14
 */
package lemon.clown.utils.algorithm.network;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ISAP {
    /**
     * from:    边的起点
     * to:      边的终点
     * cpa:     边的容量
     * flow:    边的流量
     */
    public static class Edge {
        int from, to, cap, flow;
        public Edge() {}
        public Edge(int from, int to, int cap, int flow) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.flow = flow;
        }
    }

    /**
     * INF:     infinity
     * MAXN:    最大的节点个数
     * cnt:     用于优化的辅助数组
     * cur:     当前弧下标
     * path:    增广路
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
    protected final List<Edge> edges = new ArrayList<>();
    protected final List<Integer>[] G;

    /**
     * s:   源点
     * t:   汇点
     * m:   边数
     * n:   节点数
     */
    protected int s, t, m, n;

    public ISAP(int inf, int maxn) {
        INF = inf;
        MAXN = maxn;
        cnt = new int[MAXN];
        cur = new int[MAXN];
        path = new int[MAXN];
        dist = new int[MAXN];

        G = new List[MAXN];
        for(int i=0; i < MAXN; ++i) G[i] = new ArrayList<>();
    }

    public ISAP(int maxn) {
        this(0x3f3f3f3f, maxn);
    }

    /**
     * 初始化成员变量
     */
    protected void initIASP() {
        m = 0;
        for(int i=0; i < n; ++i) G[i].clear();
        edges.clear();
    }

    /**
     * 初始化网络流
     * @param source        源点
     * @param converge      汇点
     * @param N             网络流节点个数
     */
    public void init(int source, int converge, int N) {
        assert N <= MAXN: "N is bigger than maxn. " + N;
        s = source;
        t = converge;
        n = N;
        initIASP();
    }

    /**
     * 沿用此前设置的网络流节点个数，重新设置源点和汇点（需要重新构边）
     * @param source        源点
     * @param converge      汇点
     */
    public void init(int source, int converge) {
        init(source, converge, n);
    }

    /**
     * 沿用此前的 源点、汇点、网络流节点个数
     */
    public void init() {
        init(s, t, n);
    }

    /**
     * 添加边
     * @param from
     * @param to
     * @param cap
     */
    public void addEdge(int from, int to, int cap) {
        edges.add(new Edge(from, to, cap, 0));
        edges.add(new Edge(to, from, 0, 0));
        G[from].add(m++);
        G[to].add(m++);
    }

    /**
     * 计算层次图： dist 的值
     */
    protected void BFS() {
        for(int i=0; i < n; ++i) dist[i] = INF;
        Queue<Integer> Q = new LinkedList<>();
        dist[t] = 0;

        while( !Q.isEmpty() ) {
            int o = Q.poll();
            for(int i: G[o]) {
                Edge e = edges.get(i);
                if( dist[e.to] == INF && e.cap == 0 ) {
                    dist[e.to] = dist[o] + 1;
                    Q.add(e.to);
                }
            }
        }
    }

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

    public int maxFlow() {
        BFS();
        for(int i=0; i < n; ++i) cur[i] = cnt[i] = 0;
        for(int i=0; i < n; ++i) if( dist[i] < n ) ++cnt[dist[i]];

        int ans = 0;
        for(int o=s; dist[o] < n;) {
            if( o == t ) {
                ans += augment();
                o = s;
            }

            boolean flag = false;
            for(int i: G[o]) {
                Edge e = edges.get(i);
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
        return ans;
    }
}
