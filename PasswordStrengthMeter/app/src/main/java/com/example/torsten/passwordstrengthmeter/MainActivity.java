package com.example.torsten.passwordstrengthmeter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button ok;

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

        ok = new Button(this);
        ok.setText("OK");
        ok.setClickable(false);
        ok.setAlpha(0.5f);

        layout.addView(text);
        layout.addView(psm);
        layout.addView(ok);

        psm.setPasswordCalculator(new PasswordCalculator(psm){

        });

        psm.setOnPasswordLevelChangeListener(new PasswordLevelListener() {
            @Override
            public void onPasswordLevelChange(int strength) {
                if(strength > 2) {
                    ok.setAlpha(1.0f);
                }
                else {
                    ok.setAlpha(0.5f);
                }
            }
        });
        setContentView(layout);
    }
}
