/*~
 *  Created by lemon-clown on 2017/9/15
 */
package lemon.clown.utils.algorithm.networkflow;

/**
 * 网络流接口
 */
public interface NetworkFlow {
    /**
     * 初始化网络流，沿用此前设置的 源点、汇点、网络流节点个数
     */
    void init();

    /**
     * 初始化网络流，沿用此前设置的 网络流节点个数
     * @param source    源点
     * @param converge  汇点
     */
    void init(int source, int converge);

    /**
     * 初始化网络流
     * @param source    源点
     * @param converge  汇点
     * @param n         节点个数
     */
    void init(int source, int converge, int n);

    /**
     * 添加边
     * @param from  起始点
     * @param to    目标位置
     * @param cap   边的容量
     */
    void addEdge(int from, int to, int cap);

    /**
     * @return  最大流
     */
    int maxFlow();
}
