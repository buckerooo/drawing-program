package com.springer.draw;

public class InputsHelper {
    public static Integer positiveIntValueFrom(String value, String message) {
        try {
            Integer intValue = Integer.valueOf(value);

            if(intValue < 1) {
                throw new IllegalArgumentException(message);
            }

            return intValue;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException(message, e);
        }
    }
}
