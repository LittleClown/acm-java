/*~
 *  Created by lemon-clown on 2017/9/9
 */
package lemon.clown.utils.datastructure.tree.bst;

/**
 * 平衡二叉树的节点接口
 * @param <T>
 */
public interface Node<T extends Node<T>> {
    /**
     * 找到以当前节点为根节点的子树中，中序遍历第一个节点
     * @return      Node 引用
     */
    T min();

    /**
     * 以当前节点为根节点的子树中，中序遍历最后一个节点
     * @return      Node 引用
     */
    T max();

    /**
     * 找到以当前节点为根节点的子树中，中序遍历时，当前节点的下一个节点
     * @return      若存在则返回下一个节点引用；不存在返回 null
     */
    T next();

    /**
     * 找到以当前节点为根节点的子树中，中序遍历时，当前节点的上一个节点
     * @return      若存在则返回下一个节点引用；不存在返回 null（实际使用时以根节点为边界）
     */
    T prev();
}
