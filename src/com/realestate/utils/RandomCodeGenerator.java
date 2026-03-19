package com.realestate.utils;

import java.security.SecureRandom;

public class RandomCodeGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final int LENGTH = 4;
    private static final String CHARACTERS = "0123456789ABCDFEGHJIKLMNOPQRSTUVWXYZ";

    public static String generateCode(){
        String newCode = "";
        for(int count = 0; count < LENGTH; count++){
            int index =  random.nextInt(CHARACTERS.length());
            newCode += CHARACTERS.charAt(index);
        }
        return newCode;
    }
}
