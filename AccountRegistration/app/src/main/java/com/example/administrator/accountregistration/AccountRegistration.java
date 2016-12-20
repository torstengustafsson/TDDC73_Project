package com.example.administrator.accountregistration;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Activity starts whenever something account-related is required
 */

class AccountRegistration {

    ArrayList<Account> accounts;

    private Context context;
    private static Account activeAccount = null;

    AccountRegistration(Context context) {
        this.context = context;
        accounts = new ArrayList<>();
        accounts.add(new Account("bob", "abc123"));
    }

    public void SetViewLogin() {
        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.login_layout, null);

        // Initialize a new instance of popup window
        final PopupWindow popupWindow = new PopupWindow(
                customView,
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            popupWindow.setElevation(50.0f);
        }

        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(customView, Gravity.CENTER,0,0);

        final EditText usernameText = (EditText) customView.findViewById(R.id.loginUsername);
        final EditText passwordText = (EditText) customView.findViewById(R.id.loginPassword);
        Button login = (Button) customView.findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show();
                activeAccount = CheckAccountExist(
                        usernameText.getText().toString(),
                        passwordText.getText().toString());
                if(activeAccount != null) {
                    ((MainActivity) context).loggedinText.setText("Logged in as: " + activeAccount.username);
                    popupWindow.dismiss();
                }
                else {
                    Toast.makeText(context, "Username and/or password did not match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    static boolean IsLoggedIn() {
        return activeAccount != null;
    }

    private Account CheckAccountExist(String username, String password) {
        for(Account a : accounts) {
            if(a.username.equals(username) && a.password.equals(password)) {
                return a;
            }
        }
        return null;
    }
}
