package com.example.administrator.carousel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        text1.setText("A nice carousel demo app!");

        Carousel carousel = new Carousel(this);

        TextView text2 = new TextView(this);
        text2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eu ipsum et tellus pellentesque egestas. Donec ut rutrum ligula. Pellentesque erat nisl, ultrices ut justo sit amet, sagittis molestie felis. Sed at rhoncus dui. Fusce tempus aliquam eleifend. Morbi id erat eu metus finibus tincidunt. Sed non dolor metus.");


        layout.addView(text1);
        layout.addView(carousel);
        layout.addView(text2);

        setContentView(layout);
        //setContentView(R.layout.main);
    }
}
