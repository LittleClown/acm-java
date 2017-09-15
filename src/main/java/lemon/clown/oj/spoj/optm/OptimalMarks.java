/*~
 *  Created by lemon-clown on 2017/9/15
 */
package lemon.clown.oj.spoj.optm;

import lemon.clown.utils.algorithm.network.ISAP;
import lemon.clown.utils.io.Scanner;

import java.util.Arrays;

public class OptimalMarks {
    static final int INF = 0x3f3f3f3f;
    static final int MAXN = 500 + 10;
    static final int MAXM = 3000 + 10;
    static final Scanner in = new Scanner(System.in);
    static final StringBuilder out = new StringBuilder();

    static int N, M, K;
    static int[] es = new int[MAXM];
    static int[] et = new int[MAXM];
    static int[] mu = new int[MAXN];
    static int[] marks = new int[MAXN];
    static int[] G = new int[MAXM];

    static ISAP2 isap = new ISAP2(MAXN+2, marks);

    static int readAndSetEdges() {
        for(int i=0; i < M; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            if( u < v ) G[i] = (u<<10) | v;
            else G[i] = (v<<10) | u;
        }

        Arrays.sort(G, 0, M);

        int m = 0;
        for(int i=0; i < M; ++i) {
            if( i > 0 && G[i] == G[i-1] ) continue;
            es[m] = G[i] >> 10;
            et[m] = G[i] & 1023;
            ++m;
        }
        return m;
    }

    static int readAndSetMarks() {
        int maxMark = 0;
        Arrays.fill(marks, 1, N+1, 0);

        K = in.nextInt();
        for(int i=0; i < K; ++i) {
            mu[i] = in.nextInt();
            int v = in.nextInt();
            marks[mu[i]] = v;
            maxMark = Math.max(maxMark, v);
        }

        return maxMark;
    }

    public static void main(String[] args) {
        int T_T = in.nextInt();
        for(int kase=1; kase <= T_T; ++kase) {
            N = in.nextInt();
            M = in.nextInt();

            int m = readAndSetEdges();
            int maxMark = readAndSetMarks();

            final int source = 0;
            final int converge = N+1;
            for(int digit=1; digit > 0 && digit <= maxMark; digit<<=1) {
                isap.init(source, converge, N+2);
                for(int i=0; i < m; ++i) {
                    isap.addEdge(es[i], et[i], 1);
                    isap.addEdge(et[i], es[i], 1);
                }
                for(int i=0; i < K; ++i) {
                    int u = mu[i];
                    if( (marks[u]&digit) > 0 ) isap.addEdge(source, u, INF);
                    else isap.addEdge(u, converge, INF);
                }
                int max_flow = isap.maxFlow();
                isap.DFS(source, digit);
            }

            for(int i=1; i <= N; ++i) out.append(marks[i]).append('\n');
        }
        System.out.println(out);
    }

    static class ISAP2 extends ISAP {
        int[] marks;
        boolean[] vis;
        public ISAP2(int maxn, int[] marks) {
            super(maxn);
            this.marks = marks;
            vis = new boolean[maxn];
        }

        public void DFS(int o, int digit) {
            vis[o] = true;
            marks[o] |= digit;
            for(int i: G[o]) {
                Edge e = edges.get(i);
                if( e.cap > e.flow && !vis[e.to] ) DFS(e.to, digit);
            }
            vis[o] = false;
        }
    }
}



