/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.datastructure.tree.findset;

import lemon.clown.utils.datastructure.tree.findset.FindSet;

/**
 * 并查集
 */
public class HeuristicFindSet implements FindSet {
    protected int N;
    public final int[] f;
    public final int[] cnt;

    /**
     * 创建并查集
     * @param size  并查集大小，以后不可再更改
     */
    public HeuristicFindSet(int size) {
        f = new int[size+1];
        cnt = new int[size+1];
    }

    @Override
    public void init(int N) {
        assert N < f.length: "Out of bound of array";
        this.N = N;
        for(int i=1; i <= N; ++i) f[i] = 0;
        for(int i=1; i <= N; ++i) cnt[i] = 1;
    }

    @Override
    public int getRoot(int x) {
        return f[x] > 0? f[x]=getRoot(f[x]): x;
    }

    @Override
    public void union(int x, int y) {
        int rx = getRoot(x);
        int ry = getRoot(y);
        if( rx == ry ) return;
        if( cnt[rx] < cnt[ry] ) {
            f[rx] = ry;
            cnt[ry] += cnt[rx];
        } else {
            f[ry] = rx;
            cnt[rx] += cnt[ry];
        }
    }
}
