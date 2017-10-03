package lemon.clown.utils.datastructure.tree.bst.treap;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class TreapTest {
    @Test
    public void saveTest() {
        Treap<Integer> treap = new Treap<Integer>(Comparator.comparingInt(o -> o), 20);
        treap.insert(2);
        treap.insert(-1);
        treap.insert(-2);
        treap.insert(11);
        treap.insert(13);
        treap.insert(8);
        treap.insert(0);

        for(Treap.TreapNode<Integer> o: treap)
            System.out.println(o.value);

        for(Integer value: treap.values()) {
            System.out.println(value);
        }
    }
}