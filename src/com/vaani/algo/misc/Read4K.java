package com.vaani.algo.misc;

/**
 * Read arbitrary size of data using read4k.
 */
public class Read4K {

    private static int size = 4096;
    private byte[] buffer;
    private int startInBuf;

    public Read4K() {
        this.buffer = new byte[size];
        this.startInBuf = 0;
    }

    public int read4K(byte[] buf) {
        // dummy
        this.startInBuf = 0;
        return 4096;
    }

    public int read(int size, byte[] buf) {
        int remain = size;

        while (remain > 0) {
            if (startInBuf == buffer.length) {
                read4K(buffer); // refresh the buffer
            }
            for (int i = startInBuf; i < buffer.length; ++i) {
                buf[buf.length - remain] = buffer[i];
                --remain;
                ++startInBuf;
                if (remain == 0) {
                    break;
                }
            }
        }

        return size;
    }

    public int sizeInBuf() {
        return buffer.length - startInBuf;
    }

}
