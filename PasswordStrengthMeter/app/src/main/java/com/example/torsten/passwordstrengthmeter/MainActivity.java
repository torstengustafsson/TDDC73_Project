package com.example.torsten.passwordstrengthmeter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView strengthText;
    ProgressBar strengthBar;
    Button ok;

    //interesting colors that we use
    int[] colors = {
            Color.parseColor("#555555"),
            Color.parseColor("#CC0000"),
            Color.parseColor("#CCCC00"),
            Color.parseColor("#00CCCC"),
            Color.parseColor("#00FF00")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(64, 64, 64, 64);

        TextView text = new TextView(this);
        text.setText("Enter password:");
        text.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                4
        ));
        text.setGravity(Gravity.CENTER);

        PasswordStrengthMeter psm = new PasswordStrengthMeter(this);
        psm.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1
        ));


        LinearLayout topLayout = new LinearLayout(this);
        topLayout.setOrientation(LinearLayout.VERTICAL);
        topLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView textLeft = new TextView(this);
        textLeft.setText("Minimum of 8 characters in length.");
        textLeft.setTextSize(10.0f);
//        topLayout.addView(pwField);
        topLayout.addView(textLeft);


        LinearLayout bottomLayout = new LinearLayout(this);
        bottomLayout.setOrientation(LinearLayout.VERTICAL);
        bottomLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        bottomLayout.setPadding(0, 20, 0, 0);

        LinearLayout bottomTopLayout = new LinearLayout(this);
        bottomTopLayout.setOrientation(LinearLayout.HORIZONTAL);
        bottomTopLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView textRight = new TextView(this);
        textRight.setText("Password Strength:");

        strengthText = new TextView(this);
        strengthText.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        strengthText.setGravity(Gravity.RIGHT);
        strengthText.setTypeface(null, Typeface.BOLD);

        strengthBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);


        bottomTopLayout.addView(textRight);
        bottomTopLayout.addView(strengthText);
        bottomLayout.addView(bottomTopLayout);
        bottomLayout.addView(strengthBar);

        ok = new Button(this);
        ok.setText("OK");
        ok.setClickable(false);
        ok.setAlpha(0.5f);

        layout.addView(text);
        layout.addView(psm);
        layout.addView(topLayout);
        layout.addView(bottomLayout);
        layout.addView(ok);

        psm.setPasswordCalculator(new PasswordCalculator() {
            @Override
            public int passwordRequirements(String text) {
                if (text.length() < 8) {
                    return 0;
                }

                boolean containsDigits = text.matches(".*\\w+.*");
                boolean containsNumbers = text.matches(".*\\d+.*");
                boolean hasUppercase = !text.equals(text.toLowerCase());
                boolean hasLowercase = !text.equals(text.toUpperCase());

                int digitsAndNumbers = containsDigits && containsNumbers ? 1 : 0;
                int upperAndLower = hasUppercase && hasLowercase ? 1 : 0;
                int hasSpecial = !text.matches("[A-Za-z0-9 ]*") ? 1 : 0;

                return 1 + digitsAndNumbers + upperAndLower + hasSpecial;
            }
        });

        psm.setOnPasswordLevelChangeListener(new PasswordLevelListener() {
            @Override
            public void onPasswordLevelChange(int strength) {
                // limit strength to be in the range 0 - 4
                strength = Math.min(Math.max(strength, 0), 4);

                strengthText.setText(strength == 0 ? "Too Short" : strength == 1 ? "Weak" : strength == 2 ? "Fair" : strength == 3 ? "Good" : "Strong");
                strengthText.setTextColor(colors[strength]);

                strengthBar.setProgress(strength * 25);
                strengthBar.getProgressDrawable().setColorFilter(colors[strength], android.graphics.PorterDuff.Mode.SRC_IN);

                if (strength > 2) {
                    ok.setAlpha(1.0f);
                } else {
                    ok.setAlpha(0.5f);
                }
            }
        });
        setContentView(layout);
    }
}
