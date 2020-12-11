package com.example.mobyardandroid.utils;

import java.util.Random;

public class RandomString {

    private String output;
    private char[] alphabet =  {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public String gen(int num){
        output = "";

        for (int i = 0; i < num; i++ ) {
            output += genSymbol();
        }

        return output;
    }

    public String genSymbolStr() {
        return String.copyValueOf(new char[]{ genSymbol() });
    }

    public char genSymbol() {
        int endNum = alphabet.length;
        int pos = (int) ( Math.random() * endNum );

        return alphabet[pos];
    }


}
