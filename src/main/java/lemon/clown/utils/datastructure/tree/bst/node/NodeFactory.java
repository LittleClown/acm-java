package lemon.clown.utils.datastructure.tree.bst.node;

import lemon.clown.utils.datastructure.tree.bst.node.BSTNode;

/**
 * Node 工厂
 * @param <VALUE>   BST 维护的 value 的类型
 * @param <NODE>    BST 节点类型
 */
public interface NodeFactory<VALUE, NODE extends BSTNode<NODE>> {
    /**
     * 创建新节点
     * @return
     */
    NODE newNode();

    /**
     * 创建新节点
     * @param value     In:     新节点的 value
     * @return          新节点
     */
    NODE newNode(VALUE value);

    /**
     * 创建新根节点
     * @return          新的根节点
     */
    default NODE newRoot() {
        return newNode(null);
    }

    /**
     * 初始化节点
     * @param o         In:     待初始化节点
     * @param value     In:     待初始化节点值
     */
    default void initNode(NODE o, VALUE value) {}

    /**
     * 初始化根节点
     */
    default void initRoot(NODE root) {
        root.son[0] = null;
    }

    /**
     * 初始化工厂类
     */
    default void clear() {};
}

