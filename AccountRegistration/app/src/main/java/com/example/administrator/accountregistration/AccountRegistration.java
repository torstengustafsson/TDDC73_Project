package com.example.administrator.accountregistration;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Activity starts whenever something account-related is required
 */

class AccountRegistration {


    private Context context;
    private static boolean isLoggedIn = false;

    AccountRegistration(Context context) {
        this.context = context;
    }

    public void SetViewLogin() {
        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.login_layout,null);

        // Initialize a new instance of popup window
        PopupWindow popupWindow = new PopupWindow(
                customView,
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            popupWindow.setElevation(5.0f);
        }
    }

    public static boolean IsLoggedIn() {
        return isLoggedIn;
    }

}
