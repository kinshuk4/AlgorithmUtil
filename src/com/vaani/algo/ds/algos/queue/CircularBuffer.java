package com.vaani.algo.ds.algos.queue;

public class CircularBuffer {

    private char[] buffer;
    private int start;
    private int length;


    public CircularBuffer(int size) {
        this.buffer = new char[size];
        this.start = 0;
        this.length = 0;
    }

    public int getLength() {
        return this.length;
    }

    public int getStart() {
        return this.start;
    }

    public int enqueue(char[] buf) {
        // connot enqueue more than the buffer can contain
        if (buf == null || buf.length > this.buffer.length - this.length) {
            return 0;
        }
        int end = (this.start + this.length) % this.buffer.length;
        int firstHalf = Math.min(this.buffer.length - end, buf.length);
        for (int i = 0; i < firstHalf; ++i) {
            this.buffer[end + i] = buf[i];
        }
        if (firstHalf < buf.length) {
            for (int i = 0; i < buf.length - firstHalf; ++i) {
                this.buffer[i] = buf[firstHalf + i];
            }
        }
        this.length += buf.length;
        return buf.length;
    }

    public int dequeue(char[] buf) {
        // cannot dequeue more than the buffer contains
        if (buf == null || buf.length > this.length) {
            return 0;
        }
        int firstHalf = Math.min(this.buffer.length - this.start, buf.length);
        for (int i = 0; i < firstHalf; ++i) {
            buf[i] = this.buffer[this.start + i];
        }
        if (firstHalf < buf.length) {
            for (int i = 0; i < buf.length - firstHalf; ++i) {
                buf[firstHalf + i] = buf[i];
            }
        }
        this.start = (this.start + buf.length) % this.buffer.length;
        this.length -= buf.length;
        return buf.length;
    }

}
