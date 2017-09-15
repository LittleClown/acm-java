/*~
 *  Created by lemon-clown on 2017/9/15
 */
package lemon.clown.utils.algorithm.networkflow;

import lemon.clown.utils.datastructure.list.ChainForward;

import java.util.Arrays;


public class ISAP_Faster {
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
    public final int MAXE;
    public final int[] cnt;
    public final int[] cur;
    public final int[] path;
    public final int[] dist;
    protected final int[] Q;
    protected final EdgesWrapper edgesWrapper;
    protected final Edge[] edges;
    protected final ChainForward G;

    /**
     * s:    源点
     * t:    汇点
     * n:    节点数
     */
    protected int s, t, n;

    public ISAP_Faster(int INF, int MAXN, int MAXE) {
        this.INF = INF;
        this.MAXN = MAXN;
        this.MAXE = MAXE * 2;

        cnt = new int[this.MAXN];
        cur = new int[this.MAXN];
        path = new int[this.MAXN];
        dist = new int[this.MAXN];
        Q = new int[this.MAXN];
        edgesWrapper = new EdgesWrapper(this.MAXE);
        edges = edgesWrapper.edges;
        G = new ChainForward(this.MAXN, this.MAXE);
    }

    public ISAP_Faster(int MAXN, int MAXE) {
        this(0x3f3f3f3f, MAXN, MAXE);
    }

    /**
     * 初始化成员变量
     */
    protected void initIASP() {
        edgesWrapper.init();
        G.init();
    }

    /**
     * 初始化网络流
     * @param source        源点
     * @param converge      汇点
     * @param N             加上 源点和汇点，网络流节点总个数
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
        G.addEdge(from, edgesWrapper.size());
        edgesWrapper.addEdge(from, to, cap, 0);
        G.addEdge(to, edgesWrapper.size());
        edgesWrapper.addEdge(to, from, 0, 0);
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
            for(int i: G.forEach(o)) {
                Edge e = edges[i];
                if( dist[e.to] == INF && e.cap == 0 ) {
                    dist[e.to] = dist[o] + 1;
                    Q[rear++] = e.to;
                }
            }
        }
    }

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

    public int maxFlow() {
        BFS();
        System.arraycopy(G.head, 0, cur, 0, n);
        Arrays.fill(cnt, 0, n, 0);
        for(int i=0; i < n; ++i)
            if( dist[i] < n ) ++cnt[dist[i]];

        int ans = 0;
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
        return ans;
    }
}
