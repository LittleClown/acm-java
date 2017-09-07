/*~
 *  Created by lemon-clown on 2017/9/7
 */
package lemon.clown.utils.operations;

/**
 * 实现此接口的类拥有零元
 * @param <T>
 */
public interface ZeroElement<T extends ZeroElement<T>> {
    /**
     * 返回零元
     * @return
     */
    T getZeroElement();

    /**
     * 将自己设置成 `零元`，并返回
     * @return
     */
    T setAsZeroElement();
}
