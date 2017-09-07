/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.operations;

public interface Subtractable<T extends Subtractable<T>> {
    /**
     * 减法操作
     * @param x
     * @return
     */
    T subtract(T x);

    /**
     * 乘以常数
     * @param times     times must greater than 0
     * @return
     */
    T multiply(int times);

    /**
     * 返回相反数
     * @return
     */
    T getNegative();

    /**
     * 乘以 -1
     * @return
     */
    T turnToNegative();
}
