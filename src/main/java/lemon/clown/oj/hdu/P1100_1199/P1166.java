/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.oj.hdu.P1100_1199;

import lemon.clown.utils.datastructure.tree.bit.BIT;
import lemon.clown.utils.datastructure.tree.bit.SimpleBIT;
import lemon.clown.utils.io.Scanner;
import lemon.clown.utils.operations.Addable;
import lemon.clown.utils.operations.Subtractable;
import lemon.clown.utils.operations.ZeroElement;

public class P1166 {
    static final Scanner in = new Scanner(System.in);
    static final int MAXN = 50000 + 10;
    static final BIT<A> bit = new SimpleBIT<>(MAXN, A.class, new A());
    static final A[] nums = new A[MAXN];

    static {
        for(int i=0; i < nums.length; ++i)
            nums[i] = new A();
    }

    public static void main(String[] args) {
        int T_T = in.nextInt();
        for(int kase=1; kase <= T_T; ++kase) {
            int N = in.nextInt();
            for(int i = 1; i <= N; ++i) nums[i].value = nums[i-1].value + in.nextInt();
            bit.initWithPrefixSum(nums, N);

            System.out.println("Case "+kase+":");

            A c = new A();
            while( true ) {
                String cmd = in.next();
                if( cmd.charAt(0) == 'E' ) break;

                int a = in.nextInt();
                c.value = in.nextInt();
                switch( cmd.charAt(0) ) {
                    case 'Q': System.out.println(bit.sum(a, c.value).value); break;
                    case 'A': bit.add(a, c); break;
                    case 'S': bit.add(a, c.turnToNegative()); break;
                }
            }
        }
    }

    static class A implements Addable<A>, Subtractable<A>, ZeroElement<A> {
        int value;

        public A() {}

        public A(int value) {
            this.value = value;
        }

        @Override
        public A add(A x) {
            return new A(value + x.value);
        }

        @Override
        public A addAndSet(A x) {
            value += x.value;
            return this;
        }

        @Override
        public A subtract(A x) {
            return new A(value - x.value);
        }

        @Override
        public A subtractAndSet(A x) {
            value -= x.value;
            return this;
        }

        @Override
        public A multiply(int times) {
            return new A(value * times);
        }

        @Override
        public A multiplyAndSet(int times) {
            value *= times;
            return this;
        }

        @Override
        public A getNegative() {
            return new A(-value);
        }

        @Override
        public A turnToNegative() {
            value = -value;
            return this;
        }

        @Override
        public A getZeroElement() {
            return new A(0);
        }

        @Override
        public A setAsZeroElement() {
            value = 0;
            return this;
        }
    }
}
