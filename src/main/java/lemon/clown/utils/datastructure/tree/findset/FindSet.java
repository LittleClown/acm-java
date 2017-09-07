package lemon.clown.utils.datastructure.tree.findset;

public interface FindSet {
    /**
     * 初始化并查集
     * @param N     并查集在创建的时候，大小已经确定， N 是用来指定有效范围的
     */
    void init(int N);

    /**
     * 获取 x 所在的树的根节点
     * @param x     待查询节点
     * @return  x 所在的树的根节点
     */
    int getRoot(int x);

    /**
     * 启发式合并两棵树
     * @param x     In:     节点 x
     * @param y     In:     节点 y
     */
    void union(int x, int y);
}
