/*~
 *  Created by lemon-clown on 2017/9/9
 */
package lemon.clown.utils.datastructure.tree.bst;

/**
 * Node 接口的简单实现，只有树结构，节点没有维护任何值
 * @param <T>
 */
public abstract class BSTNode<T extends BSTNode<T>> implements Node<T> {
    public static final int LSON_IDX = 0;
    public static final int RSON_IDX = 1;

    public T father;
    public T[] son;

    /**
     * 修复节点
     */
    abstract protected void maintain();

    /**
     * 连接两个节点
     * @param child     In:     子节点
     * @param d         In:     将 child 作为 son[d]
     */
    protected void link(T child, int d) {
        son[d] = child;
        if( child != null ) child.father = father;
    }

    /**
     * 旋转
     * @param d  旋转方向
     */
    protected void rotate(int d) {
        assert d == LSON_IDX || d == RSON_IDX: "in rotate: d is " + d;
        T f = father;
        T o = son[d^1];
        int idx = o.getIndexOfFather();
        this.link(o.son[d], d^1);
        o.link((T) this, d);
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
    public T min() {
        T ret = (T) this;
        while( ret.son[0] != null ) ret = ret.son[0];
        return ret;
    }

    @Override
    public T max() {
        T ret = (T) this;
        while( ret.son[1] != null ) ret = ret.son[1];
        return ret;
    }

    @Override
    public T next() {
        if( son[1] != null ) return son[1].min();

        T o = (T) this;
        T father = o.father;
        while( father != null && father.son[1] == o ) {
            o = father;
            father = o.father;
        }
        return father;
    }

    @Override
    public T prev() {
        if( son[0] != null ) return son[0].max();

        T o = (T) this;
        T father = o.father;
        while( father != null && father.son[0] == o ) {
            o = father;
            father = o.father;
        }
        return father;
    }
}
