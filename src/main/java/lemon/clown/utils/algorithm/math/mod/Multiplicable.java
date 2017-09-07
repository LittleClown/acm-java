package lemon.clown.utils.algorithm.math.mod;

/**
 * 重载此接口以支持 Power.Power 方法
 * @param <T>
 * @param <M>
 */
public interface Multiplicable<T, M> {
    /**
     * 获取单位元
     * @return  单位元
     */
    Multiplicable<T, M> getInstanceAsOne();

    /**
     * 对 m 取模
     * @param m     模数
     * @return  取模后的值
     */
    Multiplicable<T, M> mod(M m);

    /**
     * 乘以 o
     * @param o     被乘数
     * @return  乘以 o 的值
     */
    Multiplicable<T, M> multiply(Multiplicable<T, M> o);
}
