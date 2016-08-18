package com.vaani.algo.misc;

/**
 * Given API: int Read4096(char* buf);
 * <p>
 * It reads data from a file and records the position so that the next time when it is called it read the next 4k chars (or the rest of the file, whichever is smaller) from the file.
 * The return is the number of chars read.
 * <p>
 * Use above API to Implement API “int Read(char* buf, int n)” which reads any number of chars from the file.
 * <p>
 * Reference: http://okckd.github.io/blog/2014/08/27/google-api-read-4096/
 * <p>
 * Created by Xiaomeng on 10/24/2014.
 */
public class Read4096 {
    String buffer = null;
    int p = 0;

    /**
     * For java, we change the api to:
     * String GoogleApi.read4096()
     */
    public String read(int n) {
        if (n < 0) return null;
        if (n == 0) return "";

        StringBuilder result = new StringBuilder();
        while (n > 0) {
            if (buffer == null || p == buffer.length()) {
                buffer = read4096();
                p = 0;
                if (buffer.length() == 0) break;
            } else {
                int numChars = buffer.length() - p;
                if (n <= numChars) {
                    result.append(buffer.substring(p, p + n));
                    p += n;
                    n = 0;
                } else {
                    result.append(buffer.substring(p));
                    p = buffer.length();
                    n -= numChars;
                }
            }
        }
        return result.toString();
    }

    public String read4096() {
        //given implement
        return "";
    }
}
