package com.example.administrator.accountregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myPages = (Button) findViewById(R.id.buttonMyPages);

        myPages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            EnterMyPages();
            }
        });
    }

    private void EnterMyPages() {
        if(AccountRegistration.IsLoggedIn()) {
            Intent intent = new Intent(this, MyPagesActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "You must log in first!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
