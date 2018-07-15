package com.vaani.algo.ds.algos.list.linked;

public class SumDigit {

    private int digit;
    private int carry;

    public SumDigit(int _dig, int _car) {
        digit = _dig;
        carry = _car;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public int getCarry() {
        return carry;
    }

    public void setCarry(int carry) {
        this.carry = carry;
    }


}
