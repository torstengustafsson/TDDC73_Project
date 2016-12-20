package com.example.administrator.accountregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity starts whenever something account-related is required
 */

public class AccountRegistrationActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String request = intent.getStringExtra("REQUEST");

        switch (request) {
            case "CHECKLOGGEDIN":

                break;

            case "LOGIN":
                setContentView(R.layout.login_layout);
                break;

            case "CREATEACCOUNT":
                setContentView(R.layout.login_layout);
                break;
        }
    }

}
