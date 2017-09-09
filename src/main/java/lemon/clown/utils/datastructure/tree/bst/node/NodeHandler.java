package lemon.clown.utils.datastructure.tree.bst.node;

import lemon.clown.utils.datastructure.tree.bst.node.Node;

/**
 * BST 节点操作
 * @param <T>
 */
public interface NodeHandler<T extends Node<T>> {
    /**
     * 对节点进行操作
     * @param idx       In:     待处理的节点的在中序遍历排名（从 1 开始）
     * @param o         In:     待处理的节点
     */
    void handle(int idx, T o);
}