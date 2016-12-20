package com.example.administrator.accountregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AccountRegistration accountRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountRegistration = new AccountRegistration(this);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.create_account:
                accountRegistration.SetViewCreateAccount();
                break;
            case R.id.log_in:
                accountRegistration.SetViewLogin();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
