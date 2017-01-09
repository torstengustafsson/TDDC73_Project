package com.example.torsten.passwordstrengthmeter;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Holds an EditText view for writing a password, and shows the password strength in
 * text and with a progressbar, with color.
 */

public class PasswordStrengthMeter extends EditText {
    private PasswordCalculator calculator;
    private PasswordLevelListener listener;


    public PasswordStrengthMeter(Context context) {
        super(context);

        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        // Listener to update password strength views
        addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            } // Not used

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            } // Not used

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkStrength(getText().toString());
            }
        });
    }

    public void setPasswordCalculator(PasswordCalculator pc) {
        this.calculator = pc;
    }

    public void setOnPasswordLevelChangeListener(PasswordLevelListener pll) {
        listener = pll;
        listener.onPasswordLevelChange(0); // initialize to 0
    }


    /**
     * Evaluates the strength of the password
     * Minimum of 8 digits
     * Password gets stronger if password contains:
     * - letters and numbers
     * - Uppercase and lowercase letters
     * - Special characters
     */
    protected void checkStrength(String text) {
        int strength = calculator.passwordRequirements(text);
        listener.onPasswordLevelChange(strength);
    }

    /**
     * Sets the views to show the current password strength
     */

    protected void setStrength(int val) {
        if (listener != null) {
            listener.onPasswordLevelChange(val);
        }
    }
}
