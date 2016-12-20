package com.example.administrator.accountregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
        Intent intent = new Intent(this, AccountRegistrationActivity.class);
        intent.putExtra("REQUEST", "CHECKLOGGEDIN");
        startActivity(intent);
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
                setContentView(R.layout.createaccount_layout);
                break;
            case R.id.log_in:
                setContentView(R.layout.login_layout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
