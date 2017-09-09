package lemon.clown.utils.datastructure.tree.bst.node;

import lemon.clown.utils.datastructure.tree.bst.node.BSTNode;

public interface NodeFactory<E, T extends BSTNode<T>> {
    /**
     * 创建新节点
     * @return
     */
    T newNode();

    /**
     * 创建新节点
     * @param value     In:     新节点的 value
     * @return          新节点
     */
    T newNode(E value);

    /**
     * 创建新根节点
     * @return          新的根节点
     */
    default T newRoot() {
        return newNode(null);
    }

    /**
     * 初始化节点
     * @param o         In:     待初始化节点
     * @param value     In:     待初始化节点值
     */
    default void initNode(T o, E value) {}

    /**
     * 初始化根节点
     */
    default void initRoot(T root) {
        root.son[0] = null;
    }

    /**
     * 初始化工厂类
     */
    default void clear() {};
}

