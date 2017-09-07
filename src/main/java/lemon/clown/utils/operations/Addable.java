package lemon.clown.utils.operations;

public interface Addable<T extends Addable<T>> {
    /**
     * 加法操作
     * @param x
     * @return
     */
    T add(T x);

    /**
     * 乘以常数
     * @param times     times must greater than 0
     * @return
     */
    T multiply(int times);
}
