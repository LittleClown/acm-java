package lemon.clown.utils.datastructure.tree.bst.node;

public interface ElementHandler<E> {
    /**
     * 对节点的元素进行操作
     * @param idx       In:     待处理的节点的在中序遍历排名（从 1 开始）
     * @param val       In:     待处理节点的 value
     */
    void handle(int idx, E val);
}

