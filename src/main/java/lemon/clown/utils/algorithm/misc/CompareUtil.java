/*~
 *  Created by lemon-clown on 2017/9/6
 */
package lemon.clown.utils.algorithm.misc;

public class CompareUtil {
    public static int min(int a, int b) {
        return a < b? a: b;
    }

    public static int max(int a, int b) {
        return a > b? a: b;
    }

    public static long min(long a, long b) {
        return a < b? a: b;
    }

    public static long max(long a, long b) {
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
