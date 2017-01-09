package com.example.torsten.passwordstrengthmeter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Holds an EditText view for writing a password, and shows the password strength in
 * text and with a progressbar, with color.
 */

public class PasswordStrengthMeter extends LinearLayout {

    EditText pwField;
    TextView strengthText;
    private ProgressBar strengthBar;

    public PasswordStrengthMeter(Context context) {
        super(context);

        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        LinearLayout topLayout = new LinearLayout(context);
        topLayout.setOrientation(VERTICAL);
        topLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        pwField = new EditText(context);
        pwField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        TextView textLeft = new TextView(context);
        textLeft.setText("Minimum of 8 characters in length.");
        textLeft.setTextSize(10.0f);
        topLayout.addView(pwField);
        topLayout.addView(textLeft);

        // Listener to update password strength views
        pwField.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            } // Not used

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            } // Not used

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkStrength(pwField.getText().toString());
            }
        });

        LinearLayout bottomLayout = new LinearLayout(context);
        bottomLayout.setOrientation(VERTICAL);
        bottomLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        bottomLayout.setPadding(0, 20, 0, 0);

        LinearLayout bottomTopLayout = new LinearLayout(context);
        bottomTopLayout.setOrientation(HORIZONTAL);
        bottomTopLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView textRight = new TextView(context);
        textRight.setText("Password Strength:");

        strengthText = new TextView(context);
        strengthText.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        strengthText.setGravity(Gravity.RIGHT);
        strengthText.setTypeface(null, Typeface.BOLD);

        strengthBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);

        //Initialize with 0 password strength
        setStrength(0);


        bottomTopLayout.addView(textRight);
        bottomTopLayout.addView(strengthText);
        bottomLayout.addView(bottomTopLayout);
        bottomLayout.addView(strengthBar);

        addView(topLayout);
        addView(bottomLayout);
    }


    /**
     * Evaluates the strength of the password
     * Minimum of 8 digits
     * Password gets stronger if password contains:
     * - letters and numbers
     * - Uppercase and lowercase letters
     * - Special characters
     */
    private void checkStrength(String text) {
        if (text.length() < 8) {
            setStrength(0);
            return;
        }

        boolean containsDigits = text.matches(".*\\w+.*");
        boolean containsNumbers = text.matches(".*\\d+.*");
        boolean hasUppercase = !text.equals(text.toLowerCase());
        boolean hasLowercase = !text.equals(text.toUpperCase());

        int digitsAndNumbers = containsDigits && containsNumbers ? 1 : 0;
        int upperAndLower = hasUppercase && hasLowercase ? 1 : 0;
        int hasSpecial = !text.matches("[A-Za-z0-9 ]*") ? 1 : 0;

        setStrength(1 + digitsAndNumbers + upperAndLower + hasSpecial);
    }

    /**
     * Sets the views to show the current password strength
     */
    private void setStrength(int val) {
        // limit val to be in the range 0 - 4
        val = Math.min(Math.max(val, 0), 4);

        int[] colors = {
                Color.parseColor("#555555"),
                Color.parseColor("#CC0000"),
                Color.parseColor("#CCCC00"),
                Color.parseColor("#00CCCC"),
                Color.parseColor("#00FF00")
        };

        strengthText.setText(val == 0 ? "Too Short" : val == 1 ? "Weak" : val == 2 ? "Fair" : val == 3 ? "Good" : "Strong");
        strengthText.setTextColor(colors[val]);

        strengthBar.setProgress(val * 25);
        strengthBar.getProgressDrawable().setColorFilter(colors[val], android.graphics.PorterDuff.Mode.SRC_IN);

    }
}
