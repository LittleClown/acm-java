/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.oj.codeforces.Y2017.M09.D06.div2;


import lemon.clown.utils.algorithm.misc.Compare;
import lemon.clown.utils.datastructure.tree.findset.HeuristicFindSet;

public class FindSetImp extends HeuristicFindSet {
    public final int[] mx;

    public FindSetImp(int size) {
        super(size);
        mx = new int[size+1];
    }

    public void init(int lft, int rht) {
        for(int i=lft; i <= rht; ++i) {
            f[i] = 0;
            cnt[i] = 1;
            mx[i] = i;
        }
    }

    @Override
    public void union(int x, int y) {
        int rx = getRoot(x);
        int ry = getRoot(y);
        if( rx == ry ) return;
        if( cnt[rx] < cnt[ry] ) {
            f[rx] = ry;
            cnt[ry] += cnt[rx];
            mx[ry] = Compare.max(mx[rx], mx[ry]);
        } else {
            f[ry] = rx;
            cnt[rx] += cnt[ry];
            mx[rx] = Compare.max(mx[rx], mx[ry]);
        }
    }
}

class Item {
    public int cost, idx;
}


public class C {
    static
    public static void main(String[] args) {

    }
}
