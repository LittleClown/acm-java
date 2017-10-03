/*~
 *  Created by lemon-clown on 2017/10/1
 */
package lemon.clown.utils.datastructure.tree.bst.treap;

import lemon.clown.utils.datastructure.tree.bst.BST;
import lemon.clown.utils.datastructure.tree.bst.BSTNode;
import lemon.clown.utils.datastructure.tree.bst.BSTNodeFactory;

import java.util.Comparator;
import java.util.Random;

public class Treap<VALUE> extends BST<VALUE, Treap.TreapNode<VALUE>> {
    public Treap(Comparator<VALUE> comparator, BSTNodeFactory<VALUE, TreapNode<VALUE>> factory) {
        super(comparator, factory);
    }

    public Treap(Comparator<VALUE> comparator, int size) {
        this(comparator, new TreapNodeFactory<VALUE>(size));
    }

    @Override
    public void insert(TreapNode<VALUE> o) {
        insert(root, root.lson, 0, o);
    }

    @Override
    public int erase(TreapNode<VALUE> o) {
        int d = (o.father.lson == o? 0: 1);
        return erase(o.father, o, d);
    }

    /**
     * 连接两个节点
     * @param father    父节点
     * @param son       子节点
     * @param d         连接方向（左/右）
     * @param <NODE>    继承自 TreapNode 的节点
     */
    protected static <NODE extends TreapNode> void link(NODE father, NODE son, int d) {
        if( d == 0 ) father.safeLinkAsLeftChild(son);
        else father.safeLinkAsRightChild(son);
    }

    /**
     * 插入节点到 treap 中
     * @param father    父节点
     * @param o         当前节点
     * @param d         当前节点在其父节点的方向（左/右）
     * @param x         待插入节点
     * @param <NODE>    继承自 TreapNode 的节点
     */
    protected <NODE extends TreapNode<VALUE>> void insert(NODE father, NODE o, int d, NODE x) {
        if( o == null ) link(father, x, d);
        else {
            int delta = comparator.compare(x.value, o.value);
            if( delta < 0 ) {
                insert(o, o.lson, 0, x);
                if( o.lson.rank > o.rank ) o.zig();
                else o.maintain();
            } else {
                insert(o, o.rson, 1, x);
                if( o.rson.rank > o.rank ) o.zag();
                else o.maintain();
            }
        }
        father.maintain();
    }

    /**
     * 删除节点 o
     * @param father    父节点
     * @param o         待删除节点
     * @param d         当前节点在其父节点的方向（左/右）
     * @param <NODE>    继承自 TreapNode 的节点
     */
    protected <NODE extends TreapNode<VALUE>> int erase(NODE father, NODE o, int d) {
        if( o.lson == null ) link(father, o.rson, d);
        else if( o.rson == null ) link(father, o.lson, d);
        else {
            if( o.lson.rank < o.rson.rank ) {
                o.zag();
                erase(o.father, o, 0);
            } else {
                o.zig();
                erase(o.father, o, 1);
            }
        }
        father.maintain();
        return 1;
    }

    /**
     * TreapNode 节点类
     * @param <VALUE>
     */
    public static class TreapNode<VALUE> extends BSTNode<VALUE, TreapNode<VALUE>> {
        private static final Random random = new Random();
        protected int rank;

        public TreapNode() {
            super();
            rank = random.nextInt();
        }

        public TreapNode(VALUE value) {
            this();
            this.value = value;
        }

        @Override
        public void init() {
            rank = random.nextInt();
            super.init();
        }

        @Override
        public void init(VALUE value) {
            rank = random.nextInt();
            super.init(value);
        }

        @Override
        protected void maintain() {

        }
    }

    public static class TreapNodeFactory<VALUE> implements BSTNodeFactory<VALUE, TreapNode<VALUE>> {
        protected int tot;
        protected final TreapNode<VALUE>[] node_pool;

        public TreapNodeFactory(TreapNode<VALUE>[] node_pool) {
            tot = 0;
            this.node_pool = node_pool;
        }

        public TreapNodeFactory(int size) {
            tot = 0;
            node_pool = new TreapNode[size];
            for(int i=0; i < size; ++i) node_pool[i] = new TreapNode<>();
        }

        @Override
        public TreapNode<VALUE> newNode() {
            assert tot < node_pool.length: "memory not enough.";
            TreapNode<VALUE> target = node_pool[tot++];
            target.init();
            return target;
        }

        @Override
        public TreapNode<VALUE> newNode(VALUE value) {
            assert tot < node_pool.length: "memory not enough.";
            TreapNode<VALUE> target = node_pool[tot++];
            target.init();
            target.value = value;
            return target;
        }

        @Override
        public void clear() {
            tot = 0;
        }
    }
}
