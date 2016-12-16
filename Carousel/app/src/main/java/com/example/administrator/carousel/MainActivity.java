package com.example.administrator.carousel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
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
        searchText.setHint("e.g. 'star wars'");

        final Carousel carousel = new Carousel(this);

        TextView text2 = new TextView(this);
        text2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eu ipsum et tellus pellentesque egestas. Donec ut rutrum ligula. Pellentesque erat nisl, ultrices ut justo sit amet, sagittis molestie felis. Sed at rhoncus dui. Fusce tempus aliquam eleifend. Morbi id erat eu metus finibus tincidunt. Sed non dolor metus.");

        searchText.setInputType(InputType.TYPE_CLASS_TEXT);
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    carousel.setHeaderText("Search results for '" + searchText.getText().toString() + "'");
                    carousel.search(searchText.getText().toString());
                }
                return true;
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
