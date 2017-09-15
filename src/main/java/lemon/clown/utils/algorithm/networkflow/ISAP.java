/*~
 *  Created by lemon-clown on 2017/9/15
 */
package lemon.clown.utils.algorithm.networkflow;

public abstract class ISAP implements NetworkFlow {
    /**
     * INF:     infinity
     * MAXN:    最大的节点个数
     * cnt:     用于优化的辅助数组
     * cur:     当前弧下标
     * path:    增广路
     * Q:       BFS 的辅助队列
     * dist:    距离汇点的距离
     */
    public final int INF;
    public final int MAXN;
    protected final int[] cnt;
    protected final int[] cur;
    protected final int[] path;
    protected final int[] dist;
    protected final int[] Q;

    /**
     * s:               源点
     * t:               汇点
     * m:               边数
     * n:               节点数
     * current_flow:    当前残余网络的流量
     */
    protected int s, t, m, n, current_flow;

    public ISAP(int MAXN, int INF) {
        this.INF = INF;
        this.MAXN = MAXN;
        cnt = new int[MAXN];
        cur = new int[MAXN];
        path = new int[MAXN];
        dist = new int[MAXN];
        Q = new int[MAXN];
    }

    @Override
    public void init() {
        init(s, t, n);
    }

    @Override
    public void init(int source, int converge) {
        init(source, converge, n);
    }

    @Override
    public void init(int source, int converge, int n) {
        assert n <= MAXN : "n is bigger than maxn. " + n;
        s = source;
        t = converge;
        this.n = n;
        m = current_flow = 0;
    }

    /**
     * 计算层次图： dist 的值
     */
    abstract protected void BFS();

    /**
     * 增广操作，让这条增广路满流
     * @return  增广路的流量
     */
    abstract protected int augment();
}
