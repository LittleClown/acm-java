/*~
 *  Created by lemon-clown on 2017/9/6
 */
package lemon.clown.algorithm.misc;

public class Compare {
    public static int min(int a, int b) {
        return a < b? a: b;
    }

    public static int max(int a, int b) {
        return a > b? a: b;
    }

    public static double min(double a, double b) {
        return a < b? a: b;
    }

    public static double max(double a, double b) {
        return a > b? a: b;
    }

    public static <T extends Comparable> T min(T A, T B) {
        return A.compareTo(B) < 0? A: B;
    }

    public static <T extends Comparable> T max(T A, T B) {
        return A.compareTo(B) > 0? A: B;
    }
}
