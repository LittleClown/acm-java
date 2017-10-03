/*~
 *  Created by lemon-clown on 2017/10/1
 */
package lemon.clown.utils.datastructure.tree.bst;

import java.util.Iterator;

public abstract class BSTNode<VALUE, NODE extends BSTNode<VALUE, NODE>> implements Iterable<NODE> {
    public NODE father;
    public NODE lson, rson;
    public VALUE value;

    public void init() {
        lson = rson = null;
    }

    public void init(VALUE value) {
        lson = rson = null;
        this.value = value;
    }

    /**
     * @return  以当前节点为根节点的子树中，中序遍历第一个节点
     */
    public NODE min() {
        NODE target = (NODE) this;
        while( target.lson != null ) target = target.lson;
        return target;
    }

    /**
     * @return  以当前节点为根节点的子树中，中序遍历最后一个节点
     */
    public NODE max() {
        NODE target = (NODE) this;
        while( target.rson != null ) target = target.rson;
        return target;
    }

    /**
     * 找到以当前节点为根节点的子树中，中序遍历时，当前节点的下一个节点
     * @return  若存在则返回下一个节点引用；不存在返回 null
     */
    public NODE next() {
        if( rson != null ) return rson.min();

        NODE o = (NODE) this;
        NODE father = o.father;
        while( father != null && father.rson == o ) {
            o = father;
            father = father.father;
        }
        return father;
    }

    /**
     * 找到以当前节点为根节点的子树中，中序遍历时，当前节点的上一个节点
     * @return  若存在则返回下一个节点引用；不存在返回 null
     */
    public NODE prev() {
        if( lson != null ) return lson.max();

        NODE o = (NODE) this;
        NODE father = o.father;
        while( father != null && father.lson == o ) {
            o = father;
            father = father.father;
        }
        return father;
    }

    @Override
    public Iterator<NODE> iterator() {
        NODE self = (NODE) this;
        return new Iterator<NODE>() {
            NODE o = self;
            @Override
            public boolean hasNext() {
                return o != null;
            }

            @Override
            public NODE next() {
                NODE target = o;
                o = o.next();
                return target;
            }
        };
    }

    /**
     * 连接两个节点，并将当前节点作为父节点，`child` 作为左子节点
     * @param child
     */
    public void linkAsLeftChild(NODE child) {
        assert child != null: "child is null.";
        lson = child;
        child.father = (NODE) this;
    }

    /**
     * 连接两个节点，并将当前节点作为父节点，`child` 作为右子节点
     * @param child
     */
    public void linkAsRightChild(NODE child) {
        assert child != null: "child is null.";
        rson = child;
        child.father = (NODE) this;
    }

    /**
     * 连接两个节点，并将当前节点作为父节点，`child` 作为左子节点
     * 和 @linkAsLeftChild 不同的是，会判断 child 是否为 null
     * @param child
     */
    public void safeLinkAsLeftChild(NODE child) {
        lson = child;
        if( child != null ) child.father = (NODE) this;
    }

    /**
     * 连接两个节点，并将当前节点作为父节点，`child` 作为右子节点
     * 和 @linkAsRightChild 不同的是，会判断 child 是否为 null
     * @param child
     */
    public void safeLinkAsRightChild(NODE child) {
        rson = child;
        if( child != null ) child.father = (NODE) this;
    }

    /**
     * 修复节点
     */
    protected abstract void maintain();

    /**
     * 左旋
     */
    public void zag() {
        assert father != null: "father can't be null.";
        NODE o = rson;

        if( father.lson == this ) father.linkAsLeftChild(o);
        else father.linkAsRightChild(o);
        safeLinkAsRightChild(o.lson);
        o.linkAsLeftChild((NODE) this);

        maintain();
        o.maintain();
    }

    /**
     * 右旋
     */
    public void zig() {
        assert father != null: "father can't be null.";
        NODE o = lson;

        if( father.lson == this ) father.linkAsLeftChild(o);
        else father.linkAsRightChild(o);
        safeLinkAsLeftChild(o.rson);
        o.linkAsRightChild((NODE) this);

        maintain();
        o.maintain();
    }

    /**
     * 左旋，和 zag 相比，能正确处理当前节点的父指针为 null 的情况
     */
    public void safeZag() {
        NODE o = rson;

        if( father == null ) {
            o.father = null;
        } else {
            if( father.lson == this ) father.linkAsLeftChild(o);
            else father.linkAsRightChild(o);
        }

        linkAsRightChild(o.lson);
        o.linkAsLeftChild((NODE) this);

        maintain();
        o.maintain();
    }

    /**
     * 右旋，和 zig 相比，能正确处理当前节点的父指针为 null 的情况
     */
    public void safeZig() {
        NODE o = lson;

        if( father == null ) {
            o.father = null;
        } else {
            if( father.lson == this ) father.linkAsLeftChild(o);
            else father.linkAsRightChild(o);
        }
        linkAsLeftChild(o.rson);
        o.linkAsRightChild((NODE) this);

        maintain();
        o.maintain();
    }
}
