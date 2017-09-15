/*~
 *  Created by lemon-clown on 2017/9/15
 */
package lemon.clown.utils.algorithm.networkflow;

/**
 * 封装 edges (重复利用空间）
 */
public class EdgesWrapper {
    public final int MAXE;
    public final Edge[] edges;
    protected int size;

    public EdgesWrapper(int MAXE) {
        this.MAXE = MAXE;
        edges = new Edge[MAXE];
        for(int i=0; i < edges.length; ++i)
            edges[i] = new Edge();
    }

    /**
     * 初始化 EdgesWrapper
     */
    public void init() {
        size = 0;
    }

    /**
     * 添加一条边
     * @param from
     * @param to
     * @param cap
     * @param flow
     */
    public void addEdge(int from, int to, int cap, int flow) {
        Edge e = edges[size++];
        e.from = from;
        e.to = to;
        e.cap = cap;
        e.flow = flow;
    }

    /**
     * @return  edges's size
     */
    public int size() {
        return size;
    }
}
