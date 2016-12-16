package com.example.administrator.carousel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView text1 = new TextView(this);
        text1.setText("Search for movies:");

        final EditText searchText = new EditText(this);
        searchText.setInputType(InputType.TYPE_CLASS_TEXT);
        searchText.setHint("e.g. 'star wars'");

        final Carousel carousel = new Carousel(this, "http://www.omdbapi.com/?s=");

        TextView text2 = new TextView(this);
        text2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eu ipsum et tellus pellentesque egestas. Donec ut rutrum ligula. Pellentesque erat nisl, ultrices ut justo sit amet, sagittis molestie felis. Sed at rhoncus dui. Fusce tempus aliquam eleifend. Morbi id erat eu metus finibus tincidunt. Sed non dolor metus.");

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    carousel.setHeaderText("Search results for '" + searchText.getText().toString() + "'");
                    carousel.search(searchText.getText().toString());

                    return true;
                }
                return false;
            }
        });

        layout.addView(text1);
        layout.addView(searchText);
        layout.addView(carousel);
        layout.addView(text2);

        //ItemView i = new ItemView(this, "name", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTI1MDIwMTczOV5BMl5BanBnXkFtZTcwNTI4MDE3MQ@@._V1_SX300.jpg");
        //layout.addView(i);

        setContentView(layout);
        //setContentView(R.layout.test);
    }
}
