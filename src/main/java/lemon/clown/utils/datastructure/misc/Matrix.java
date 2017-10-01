/*~
 *  Created by lemon-clown on 2017/9/16
 */
package lemon.clown.utils.datastructure.misc;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Matrix {
    final int row, col;
    final int[][] mat;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        mat = new int[row][col];
    }

    public Matrix(Matrix m) {
        this(m.row, m.col);
        for(int r=0; r < row; ++r)
            System.arraycopy(m.mat[r], 0, mat[r], 0, col);
    }

    public static void setZero(Matrix m) {
        for(int r=0; r < m.row; ++r)
            Arrays.fill(m.mat[r], 0);
    }

    public static void setIdentity(Matrix m) {
        assert m.row == m.col: "row is not strictly equals with col.";
        setZero(m);
        for(int r=0; r < m.row; ++r)
            m.mat[r][r] = 1;
    }

    public Matrix multi(Matrix m) {
        Matrix ret = new Matrix(row, m.col);
        for(int r=0; r < row; ++r)
            for(int c=0; c < m.col; ++c)
                for(int k=0; k < col; ++k)
                    ret.mat[r][c] += mat[r][k] * mat[k][c];
        return ret;
    }

    public Matrix multi(Matrix m, int MOD) {
        ByteBuffer buffer;
        Matrix ret = new Matrix(row, m.col);
        for(int r=0; r < row; ++r)
            for(int c=0; c < m.col; ++c)
                for(int k=0; k < col; ++k)
                    ret.mat[r][c] = (int) (((long) mat[r][k] * mat[k][c] + ret.mat[r][c]) % MOD);
        return ret;
    }
}
