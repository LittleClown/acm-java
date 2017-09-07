/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.operations;

import lemon.clown.utils.operations.Accumulable;

/**
 * 继承此接口的类可进行减法操作
 * @param <T>
 */
public interface Subtractable<T extends Subtractable<T>> extends Accumulable<T> {
    /**
     * 减法操作
     * @param x
     * @return
     */
    T subtract(T x);

    /**
     * 减法操作，并用计算结果更新自身
     * @param x
     * @return
     */
    T subtractAndSet(T x);

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
