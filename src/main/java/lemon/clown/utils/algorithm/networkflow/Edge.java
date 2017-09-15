/*~
 *  Created by lemon-clown on 2017/9/15
 */
package lemon.clown.utils.algorithm.networkflow;

/**
 * from:    边的起点
 * to:      边的终点
 * cpa:     边的容量
 * flow:    边的流量
 */
public class Edge {
    public int from, to, cap, flow;
    public Edge() {}
    public Edge(int from, int to, int cap, int flow) {
        this.from = from;
        this.to = to;
        this.cap = cap;
        this.flow = flow;
    }
}
