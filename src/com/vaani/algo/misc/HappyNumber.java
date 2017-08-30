package com.vaani.algo.misc;
//https://github.com/JoshuaYang36/Java-Exercises/blob/master/Algorithms/HappyNumber.java
/*  ----------------------------------------------------------------------------------------------------------------/
/   Problem: A happy number is one where taking each digit in the given integer, and summing their squares will     /
/            result in 1 or continue before.                                                                        /
/                                                                                                                   /
/   Example: 19                                                                                                     /
/            1*1 + 9*9 = 81                                                                                         /
/            8*8 + 1*1 = 64                                                                                         /
/            6*6 + 4*4 = 100                                                                                        /
/            1*1 + 0*0 + 0*0 = 1                                                                                    /
/            return true                                                                                            /
/   ---------------------------------------------------------------------------------------------------------------*/
public class HappyNumber {
    public boolean isHappy(int n) {
        int output = 0;
        int lastDigit;
        while(n > 9){
            lastDigit = n % 10;
            n = n/10;
            output = output + (lastDigit * lastDigit);
        }
        output = output + (n * n);

        if(output == 1){

            return true;
        }
        else{
            isHappy(output);
        }
        return false;
    }
}
