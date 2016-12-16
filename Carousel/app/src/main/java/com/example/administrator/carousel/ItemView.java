package com.example.administrator.carousel;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Holds an item view that is used by the Carousel class.
 */

public class ItemView extends LinearLayout {

    TextView name;
    ImageView image;

    public ItemView(Context context, String nameText, String urlText) {
        super(context);
        setPadding(10, 10, 10 ,10);
        setOrientation(VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.itemview, this);

        name = (TextView) findViewById(R.id.itemView_text);
        name.setTextSize(10);
        nameText = nameText.length() > 40 ? nameText.substring(0, 37) + "..." : nameText;
        name.setText(nameText);
        image = (ImageView) findViewById(R.id.itemView_image);
        // OMDB uses the string 'N/A' to represent no image found. May be different for others.
        if(!urlText.equals("N/A"))
            new ImageLoader(context, image, urlText, this).execute();
    }
}
