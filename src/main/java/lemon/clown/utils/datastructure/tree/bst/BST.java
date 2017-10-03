/*~
 *  Created by lemon-clown on 2017/9/9
 */
package lemon.clown.utils.datastructure.tree.bst;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;

public abstract class BST<VALUE, NODE extends BSTNode<VALUE, NODE>> implements Iterable<NODE> {
    protected int size;
    protected final NODE root;
    protected final Comparator<VALUE> comparator;
    protected final BSTNodeFactory<VALUE, NODE> factory;


    protected BST(Comparator<VALUE> comparator, BSTNodeFactory<VALUE, NODE> factory) {
        this.comparator = comparator;
        this.factory = factory;
        this.root = factory.newRoot();
    }

    /**
     * 初始化/重置 BST
     */
    public void clear() {
        size = 0;
        factory.initRoot(root);
        factory.clear();
    }

    /**
     * 查找 BST 中值为 x 的节点
     * @param x     In:     待查找值
     * @return      BST 中值为 x 的节点（若有多个，则任意返回一个，若不存在，返回 null）
     */
    public NODE search(VALUE x) {
        return search(root.lson, x);
    }

    /**
     * 插入值到 BST 中
     * @param x     In:     待插入值
     */
    public void insert(VALUE x) {
        insert(factory.newNode(x));
    }

    /**
     * 插入节点到 BST 中
     * @param o     In:     待插入节点
     */
    public abstract void insert(NODE o);

    /**
     * 删除 BST 中值为 x 的所有值
     * @param x     In:     待删除值
     * @return      返回删除掉的节点个数
     */
    public int erase(VALUE x) {
        NODE o = lower_bound(x);
        if( o == null ) return 0;

        int cnt = 0;
        for(NODE end=end(); o != end && o.value == x;) {
            NODE target = o;
            o = o.next();
            cnt += erase(target);
        }
        return cnt;
    }

    /**
     * 删除 BST 中的节点
     * @param o     In:     待删除节点
     * @return      返回删除掉的节点个数(0 或 1）
     */
    public abstract int erase(NODE o);

    @Override
    public Iterator<NODE> iterator() {
        assert root.lson != null: "root.lson is null!!";
        return new Iterator<NODE>() {
            NODE o = root.lson.min();
            final NODE end = end();

            @Override
            public boolean hasNext() {
                return o != end;
            }

            @Override
            public NODE next() {
                NODE target = o;
                o = o.next();
                return target;
            }
        };
    }

    public Iterable<VALUE> values() {
        assert root.lson != null: "root.lson is null!!";
        return ()-> new Iterator<VALUE>() {
            NODE o = root.lson.min();
            final NODE end = end();

            @Override
            public boolean hasNext() {
                return o != end;
            }

            @Override
            public VALUE next() {
                NODE target = o;
                o = o.next();
                return target.value;
            }
        };
    }

    /**
     * 将 BST 的所有节点的元素值以中序遍历的结果保存到 targets 数组中
     * @param targets   Out:    中序遍历的结果
     * @return          targets 的有效长度
     */
    public int toArray(VALUE[] targets) {
        assert targets.length >= size: "Out of bound of targets.";
        return toArray(root.lson, targets, 0);
    }

    /**
     * BST 的所有节点的元素值以中序遍历的结果
     * @param classType     In:     节点的 value 的类型信息
     * @return              BST 的所有节点的元素值以中序遍历的结果
     */
    public VALUE[] toArray(Class<VALUE> classType) {
        VALUE[] targets = (VALUE[]) Array.newInstance(classType, size);
        toArray(targets);
        return targets;
    }

    /**
     * 找到第一个值大于等于 x 的节点
     * @param x
     * @return          第一个值大于等于 x 的节点
     */
    public NODE lower_bound(VALUE x) {
        return lower_bound(root.lson, x);
    }

    /**
     * 找到第一个值大于 x 的节点
     * @param x         In:     anchor
     * @return          第一个值大于 x 的节点
     */
    public NODE upper_bound(VALUE x) {
        return upper_bound(root.lson, x);
    }

    /**
     * BST 中序遍历的第一个节点
     * @return          BST 中序遍历的第一个节点
     */
    public NODE begin() {
        NODE target = root.lson;
        if( target == null ) return null;
        return target.min();
    }

    /**
     * 执行迭代时，用于判断边界
     * @return          边界（开）
     */
    public NODE end() {
        return root;
    }

    /**
     * 查找 BST 中值为 x 的节点
     * @param o     当前节点
     * @param x
     * @return      BST 中值为 x 的节点；若不存在，返回 null
     */
    protected NODE search(NODE o, VALUE x) {
        if( o == null ) return null;

        int delta = comparator.compare(x, o.value);
        if( delta < 0 ) return search(o.lson, x);
        if( delta > 0 ) return search(o.rson, x);
        return o;
    }

    protected int count(NODE o, VALUE x) {
        if( o == null ) return 0;

        int delta = comparator.compare(x, o.value);
        int cnt = (delta == 0? 1: 0);
        if( delta <= 0 ) cnt += count(o.lson, x);
        if( delta >= 0 ) cnt += count(o.rson, x);
        return cnt;
    }

    /**
     * 找到第一个值大于等于 x 的节点
     * @param o     当前节点
     * @param x
     * @return      第一个值大于等于 x 的节点；若不存在，返回 null
     */
    protected NODE lower_bound(NODE o, VALUE x) {
        if( o == null ) return null;

        NODE target = null;
        int delta = comparator.compare(o.value, x);
        if( delta < 0 ) target = lower_bound(o.rson, x);
        else {
            target = lower_bound(o.lson, x);
            if( target == null ) target = o;
        }

        return target;
    }

    /**
     * 找到第一个值大于 x 的节点
     * @param o     当前节点的父节点
     * @param x
     * @return      第一个值大于 x 的节点；若不存在，返回 null
     */
    protected NODE upper_bound(NODE o, VALUE x) {
        if( o == null ) return null;

        NODE target = null;
        int delta = comparator.compare(o.value, x);
        if( delta <= 0 ) target = upper_bound(o.rson, x);
        else {
            target = upper_bound(o.lson, x);
            if( target == null ) target = o;
        }

        return target;
    }

    /**
     * 将 BST 的所有节点的元素值以中序遍历的结果保存到 targets 数组中
     * @param o         当前节点
     * @param targets   保存 value 的数组
     * @param idx       当前节点的标号（从 0 开始）
     * @return  targets 的有效长度
     */
    protected int toArray(NODE o, VALUE[] targets, int idx) {
        if( o == null ) return idx;
        idx = toArray(o.lson, targets, idx);
        targets[idx++] = o.value;
        idx = toArray(o.rson, targets, idx);
        return idx;
    }
}
