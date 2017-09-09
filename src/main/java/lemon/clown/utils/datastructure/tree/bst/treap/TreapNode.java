/*~
 *  Created by lemon-clown on 2017/9/9
 */
package lemon.clown.utils.datastructure.tree.bst.treap;

import lemon.clown.utils.datastructure.tree.bst.node.BSTNode;

import java.util.Random;

public class TreapNode<VALUE> extends BSTNode<TreapNode<VALUE>> {
    private static final Random random = new Random();

    protected int rank;
    public VALUE value;

    public TreapNode() {
        rank = random.nextInt();
    }

    public TreapNode(VALUE value) {
        this();
        this.value = value;
    }

    @Override
    protected void maintain() {

    }
}
