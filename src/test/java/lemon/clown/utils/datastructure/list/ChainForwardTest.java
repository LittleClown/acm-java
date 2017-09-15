package lemon.clown.utils.datastructure.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChainForwardTest {
    @Test
    public void forEach() throws Exception {
        ChainForward chainForward = new ChainForward(4, 20);
        chainForward.init();
        chainForward.addEdge(1, 2);

        for(int i: chainForward.forEach(1))
            System.out.println(i);
    }

}
