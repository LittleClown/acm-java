package lemon.clown.utils.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Scanner2 {
    BufferedReader reader;

    public Scanner2(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
    }

    public int nextInt() throws IOException {
        int c = reader.read();
        while (c <= 32) {
            c = reader.read();
        }
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = reader.read();
        }
        int x = 0;
        while (c > 32) {
            x = x * 10 + c - '0';
            c = reader.read();
        }
        return negative ? -x : x;
    }

    public long nextLong() throws IOException {
        int c = reader.read();
        while (c <= 32) {
            c = reader.read();
        }
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = reader.read();
        }
        long x = 0;
        while (c > 32) {
            x = x * 10 + c - '0';
            c = reader.read();
        }
        return negative ? -x : x;
    }

    public String next() throws IOException {
        int c = reader.read();
        while (c <= 32) {
            c = reader.read();
        }
        StringBuilder sb = new StringBuilder();
        while (c > 32) {
            sb.append((char) c);
            c = reader.read();
        }
        return sb.toString();
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
}
