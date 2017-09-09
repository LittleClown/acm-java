/*~
 *  Created by lemon-clown on 2017/9/9
 */
package lemon.clown.utils.datastructure.tree.bst.node;

import lemon.clown.utils.datastructure.tree.bst.node.Node;

/**
 * Node 接口的简单实现，只有树结构，节点没有维护任何值
 * @param <NODE>
 */
public abstract class BSTNode<NODE extends BSTNode<NODE>> implements Node<NODE> {
    public static final int LSON_IDX = 0;
    public static final int RSON_IDX = 1;

    public NODE father;
    public NODE[] son;

    /**
     * 修复节点
     */
    abstract protected void maintain();

    /**
     * 连接两个节点
     * @param child     In:     子节点
     * @param d         In:     将 child 作为 son[d]
     */
    protected void link(NODE child, int d) {
        son[d] = child;
        if( child != null ) child.father = father;
    }

    /**
     * 旋转
     * @param d  旋转方向
     */
    protected void rotate(int d) {
        assert d == LSON_IDX || d == RSON_IDX: "in rotate: d is " + d;
        NODE f = father;
        NODE o = son[d^1];
        int idx = o.getIndexOfFather();
        this.link(o.son[d], d^1);
        o.link((NODE) this, d);
        f.link(o, idx);
        maintain();
        o.maintain();
    }

    /**
     * 找到当前节点在其父节点的子节点列表的下标
     * @return      整数下标
     */
    protected int getIndexOfFather() {
        return father.son[0] == this? 0: 1;
    }

    @Override
    public NODE min() {
        NODE ret = (NODE) this;
        while( ret.son[0] != null ) ret = ret.son[0];
        return ret;
    }

    @Override
    public NODE max() {
        NODE ret = (NODE) this;
        while( ret.son[1] != null ) ret = ret.son[1];
        return ret;
    }

    @Override
    public NODE next() {
        if( son[1] != null ) return son[1].min();

        NODE o = (NODE) this;
        NODE father = o.father;
        while( father != null && father.son[1] == o ) {
            o = father;
            father = o.father;
        }
        return father;
    }

    @Override
    public NODE prev() {
        if( son[0] != null ) return son[0].max();

        NODE o = (NODE) this;
        NODE father = o.father;
        while( father != null && father.son[0] == o ) {
            o = father;
            father = o.father;
        }
        return father;
    }
}
