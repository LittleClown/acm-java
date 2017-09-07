/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.operations;

public interface Accumulable<T extends Accumulable<T>> {
    /**
     * 乘以常数
     * @param times     times must greater than 0
     * @return
     */
    T multiply(int times);

    /**
     * 乘以常数，并用计算结果更新自身
     * @param times
     * @return
     */
    T multiplyAndSet(int times);
}
