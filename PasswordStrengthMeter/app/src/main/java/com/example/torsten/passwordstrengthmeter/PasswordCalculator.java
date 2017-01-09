package com.example.torsten.passwordstrengthmeter;

/**
 * Created by Administrator on 09/01/2017.
 */

public class PasswordCalculator {

    private PasswordStrengthMeter callback;

    public PasswordCalculator(PasswordStrengthMeter psm) {
        callback = psm;
    }

    /**
     * Evaluates the strength of the password
     * Minimum of 8 digits
     * Password gets stronger if password contains:
     *  - letters and numbers
     *  - Uppercase and lowercase letters
     *  - Special characters
     */
    protected void checkStrength(String text) {
        if(text.length() < 8) {
            callback.setStrength(0);
            return;
        }

        boolean containsDigits = text.matches(".*\\w+.*");
        boolean containsNumbers = text.matches(".*\\d+.*");
        boolean hasUppercase = !text.equals(text.toLowerCase());
        boolean hasLowercase = !text.equals(text.toUpperCase());

        int digitsAndNumbers = containsDigits && containsNumbers ? 1 : 0;
        int upperAndLower = hasUppercase && hasLowercase ? 1 : 0;
        int hasSpecial = !text.matches("[A-Za-z0-9 ]*") ? 1 : 0;

        callback.setStrength(1 + digitsAndNumbers + upperAndLower + hasSpecial);
    }
}
