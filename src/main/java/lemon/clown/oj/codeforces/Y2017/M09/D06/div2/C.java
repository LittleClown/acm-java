/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.oj.codeforces.Y2017.M09.D06.div2;


import lemon.clown.utils.algorithm.misc.Compare;
import lemon.clown.utils.algorithm.sort.QuickSort;
import lemon.clown.utils.algorithm.sort.Sort;
import lemon.clown.utils.datastructure.tree.findset.HeuristicFindSet;
import lemon.clown.utils.io.Scanner;

class FindSetImp extends HeuristicFindSet {
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
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int N = in.nextInt();
        int K = in.nextInt();

        Item[] items = new Item[N+1];
        for(int i=1; i <= N; ++i) {
            Item item = new Item();
            item.cost = in.nextInt();
            item.idx = i;
            items[i] = item;
        }

        Sort<Item> sort = new QuickSort<Item>((Item a, Item b)-> {
            return b.cost - a.cost;
        });
        sort.sort(items, 1, N);

        FindSetImp fs = new FindSetImp(N*2);
        fs.init(K+1, N+K+1);

        int[] orders = new int[N+1];
        long ans = 0;

        for(int i=1; i <= N; ++i) {
            Item item = items[i];
            int pos = Compare.max(item.idx, K+1);
            int rt = fs.getRoot(pos);

            pos = fs.mx[rt];
            orders[item.idx] = pos;
            ans += (long) item.cost * (long) (pos-item.idx);

            fs.union(rt, pos+1);
        }

        System.out.println(ans);
        System.out.print(orders[1]);
        for(int i=2; i <= N; ++i) System.out.print(" " + orders[i]);
        System.out.println();
    }
}
