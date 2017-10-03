package lemon.clown.utils.datastructure.tree.bst.treap;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class TreapTest {
    <T> void showTreap(Treap<T> treap) {
        int cnt = 0;
        final int BASE = 20;
        for(T value: treap.values()) {
            System.out.print(value);
            if( ++cnt % BASE == 0 ) System.out.print("\n\t");
            else System.out.print(" ");
        }
        if( cnt % BASE != 0 ) System.out.println();
        System.out.println();
    }

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

        treap.insert(2);
        treap.insert(-1);
        treap.insert(-2);
        treap.insert(11);
        treap.insert(13);
        treap.insert(8);
        treap.insert(0);

        System.out.println(treap.lower_bound(2).value);
        System.out.println(treap.lower_bound(1).value);
        System.out.println(treap.upper_bound(2).value);
        System.out.println(treap.upper_bound(1).value);

        Treap.TreapNode<Integer> o = treap.search(2);
        System.out.println(o + ", " + o.value);

//        treap.erase(o);
        showTreap(treap);
        int cnt = treap.erase(2);
        System.out.println("delete count: " + cnt);
        showTreap(treap);
    }
}