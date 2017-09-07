package lemon.clown.utils.operations;

/**
 * 实现此接口的类拥有单位元
 * @param <T>
 */
public interface UnitElement<T extends UnitElement<T>>  {
    /**
     * 返回单位元
     * @return
     */
    T getUnitElement();

    /**
     * 将自己设置成 `单位元`，并返回
     * @return
     */
    T setAsUnitElement();
}
