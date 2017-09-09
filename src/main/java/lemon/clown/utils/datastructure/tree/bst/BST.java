/*~
 *  Created by lemon-clown on 2017/9/9
 */
package lemon.clown.utils.datastructure.tree.bst;

import lemon.clown.utils.datastructure.tree.bst.node.ElementHandler;
import lemon.clown.utils.datastructure.tree.bst.node.Node;
import lemon.clown.utils.datastructure.tree.bst.node.NodeHandler;

public interface BST<VALUE, NODE extends Node<NODE>> {
    /**
     * 初始化/重置 BST
     */
    void clear();

    /**
     * 查找 BST 中值为 x 的节点
     * @param x     In:     待查找值
     * @return      BST 中值为 x 的节点（若有多个，则任意返回一个，若不存在，返回 null）
     */
    NODE search(VALUE x);

    /**
     * 插入值到 BST 中
     * @param x     In:     待插入值
     */
    void insert(VALUE x);

    /**
     * 插入节点到 BST 中
     * @param o     In:     待插入节点
     */
    void insert(NODE o);

    /**
     * 删除 BST 中值为 x 的所有值
     * @param x     In:     待删除值
     * @return      返回删除掉的节点个数
     */
    int erase(VALUE x);

    /**
     * 删除 BST 中的节点
     * @param o     In:     待删除节点
     * @return      返回删除掉的节点个数(0 或 1）
     */
    int erase(NODE o);

    /**
     * 中序遍历 BST 中的节点，并将节点传给 handler 处理
     * @param handler   In:     处理类
     */
    void forEachNode(NodeHandler<NODE> handler);

    /**
     * 中序遍历 BST 中的节点，并依次将节点在中序遍历的排名（从 1 开始），以及节点的值(value) 传给 handler 处理
     * @param handler   In:     处理类
     */
    void forEachElement(ElementHandler<VALUE> handler);

    /**
     * 将 BST 的所有节点的元素值以中序遍历的结果保存到 targets 数组中
     * @param targets   Out:    中序遍历的结果
     * @return          targets 的有效长度
     */
    int toArray(VALUE[] targets);

    /**
     * BST 的所有节点的元素值以中序遍历的结果
     * @param classType     In:     节点的 value 的类型信息
     * @return              BST 的所有节点的元素值以中序遍历的结果
     */
    VALUE[] toArray(Class<VALUE> classType);

    /**
     * 找到第一个值大于或等于 x 的节点
     * @param x         In:     anchor
     * @return          第一个值大于或等于 x 的节点
     */
    NODE lower_bound(VALUE x);

    /**
     * 找到第一个值大于 x 的节点
     * @param x         In:     anchor
     * @return          第一个值大于 x 的节点
     */
    NODE upper_bound(VALUE x);

    /**
     * BST 中序遍历的第一个节点
     * @return          BST 中序遍历的第一个节点
     */
    NODE begin();

    /**
     * 执行迭代时，用于判断边界
     * @return          边界（开）
     */
    NODE end();
}
