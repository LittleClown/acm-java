package lemon.clown.utils.datastructure.tree.bst.treap;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreapNodeTest<T> extends TreapNode<T> {
    @Test
    public void JustATest() {
        TreapNode<Integer> node = new TreapNode<>();
        node.value = 2;

        Integer ans = 2;
        assertEquals(ans, node.value);
        assertEquals(ans, node.min().value);
        assertEquals(ans, node.max().value);
        assertEquals(null, node.next());
        assertEquals(null, node.prev());
    }

    @Test
    public void Just2ndTest() {
        TreapNode<Integer> o = new TreapNode<>(2);
        TreapNode<Integer> lson = new TreapNode<>(1);
        TreapNode<Integer> rson = new TreapNode<>(3);

    }
}