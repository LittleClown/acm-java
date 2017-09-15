/*~
 * ***[IndiaHacks 2016 - Online Edition (Div. 1 + Div. 2) D](http://codeforces.com/contest/653/problem/D)***
 *
 * tag:
 *  - 网络流
 *  - 二分
 */
package lemon.clown.oj.codeforces.Y2017.M09.D15;

import lemon.clown.utils.algorithm.networkflow.ISAP_Faster;
import lemon.clown.utils.io.Scanner;

public class DeliveryBears_2 {
    static final int INF = 0x3f3f3f3f;
    static final int MAXN = 500 + 10;
    static final Scanner in = new Scanner(System.in);

    static int N, M, X;
    static int[] a = new int[MAXN];
    static int[] b = new int[MAXN];
    static int[] c = new int[MAXN];
    static ISAP_Faster isap = new ISAP_Faster(MAXN, MAXN*MAXN*2);

    static boolean check(double x) {
        final int source = 0;
        final int converge = N+1;
        isap.init(source, converge, N+2);

        isap.addEdge(N, converge, INF);
        isap.addEdge(source, 1, X);

        for(int i=0; i < M; ++i) {
            int cap = (int) Math.min(X, c[i]/x);
            isap.addEdge(a[i], b[i], cap);
        }

        return isap.maxFlow() == X;
    }

    public static void main(String[] args) {
        N = in.nextInt();
        M = in.nextInt();
        X = in.nextInt();

        for(int i=0; i < M; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
            c[i] = in.nextInt();
        }


        double lo = 0.0, hi = 1e9;
        for(int i=0; i < 70; ++i) {
            double mid = (lo+hi) / 2.0;
            if( check(mid) ) lo = mid;
            else hi = mid;
        }

        System.out.println(String.format("%.12f", lo*X));
    }
}
