package com.vaani.algo.misc;

import java.util.Scanner;
//https://github.com/kchanin/CaesarCipher/blob/master/src/com/chanin/Main.java
public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int stringLength = scanner.nextInt();
        String message = scanner.next();
        int rotation = scanner.nextInt();
        String codedMessage = "";

        for (int i = 0; i < stringLength; i++){

            codedMessage += (char) rotate((int)message.charAt(i), rotation);

        }
        System.out.println(codedMessage);
    }

    public static int rotate(int value, int rotation){
        rotation %= 26;
        if (value > 64 && value < 91) {
            value += rotation;
            if (value > 90) {
                value = 65 + (value % 91);
            }
            return value;
        }else if (value > 96 && value < 123){
            value += rotation;
            if (value > 122){
                value = 97 + (value % 123);
            }
            return value;
        }
        return value;
    }

}
