package lemon.clown.utils.datastructure.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChainForwardTest {
    ChainForward chainForward = new ChainForward(4, 20);

    @Test
    public void forEachWithIndex() throws Exception {
        chainForward.init();
        chainForward.addEdge(1, 2);

        for(int i: chainForward.forEachWithIndex(1))
            System.out.println(i);
    }

    @Test
    public void forEach() throws Exception {
        chainForward.init();
        chainForward.addEdge(1, 2);

        for(int i: chainForward.forEach(1))
            System.out.println(i);
    }

}
