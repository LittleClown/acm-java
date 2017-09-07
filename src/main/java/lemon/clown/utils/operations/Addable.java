package lemon.clown.utils.operations;

import lemon.clown.utils.operations.Accumulable;

/**
 * 继承此接口的类可执行加法操作
 * @param <T>
 */
public interface Addable<T extends Addable<T>> extends Accumulable<T> {
    /**
     * 加法操作
     * @param x
     * @return
     */
    T add(T x);

    /**
     * 加法操作，并用计算结果更新自身
     * @param x
     * @return
     */
    T addAndSet(T x);
}
