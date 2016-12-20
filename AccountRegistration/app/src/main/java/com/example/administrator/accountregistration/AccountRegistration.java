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
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Activity starts whenever something account-related is required
 */

class AccountRegistration {

    ArrayList<Account> accounts;

    private Context context;
    private static boolean isLoggedIn = false;

    AccountRegistration(Context context) {
        this.context = context;
        accounts = new ArrayList<>();
    }

    public void SetViewLogin() {
        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.login_layout, null);

        // Initialize a new instance of popup window
        PopupWindow popupWindow = new PopupWindow(
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

        Button login = (Button) customView.findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }

    public void SetViewCreateAccount() {
        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.createaccount_layout, null);

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

        Button create = (Button) customView.findViewById(R.id.createAccount);
        final EditText nameField = (EditText) customView.findViewById(R.id.inputUserName);
        final EditText pswrdField = (EditText) customView.findViewById(R.id.inputPasswordFirst);
        final EditText pswrdReField = (EditText) customView.findViewById(R.id.inputPasswordRepeat);

        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = nameField.getText().toString();
                String pswrd = pswrdField.getText().toString();
                String pswrdRe = pswrdReField.getText().toString();
                if(pswrd.equals(pswrdRe)){
                    createAccount(name, pswrd);
                    Toast.makeText(context, "Account created", Toast.LENGTH_SHORT);
                    popupWindow.dismiss();
                }
                else{
                    Toast.makeText(context, "Unequal passwords, retype, idiot.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static boolean IsLoggedIn() {
        return isLoggedIn;
    }

    private void createAccount(String name, String pswrd){
        accounts.add(new Account(name, pswrd));
    }
}
