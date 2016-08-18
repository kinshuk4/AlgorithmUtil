package com.vaani.algo.misc;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * <p>
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * <p>
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * <p>
 * Note:
 * The read function will only be called once for each test case.
 */
public class ReadNCharactersGivenRead4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        boolean eof = false;
        int readBytes = 0;
        while (!eof && readBytes < n) {
            int size = read4(buffer);
            if (size < 4) eof = true;
            int bytes = Math.min(n - readBytes, size);
            System.arraycopy(buffer, 0, buf, readBytes, bytes);
            readBytes += bytes;
        }
        return readBytes;
    }

    private int read4(char[] buf) {
        return 0;
    }
}
