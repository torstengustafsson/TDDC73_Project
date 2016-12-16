package com.example.administrator.carousel;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 08/12/2016.
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


        setOrientation(VERTICAL);
        name = (TextView) findViewById(R.id.itemView_text);
        name.setTextSize(10);
        nameText = nameText.length() > 25 ? nameText.substring(0, 22) + "..." : nameText;
        name.setText(nameText);
        image = (ImageView) findViewById(R.id.itemView_image);
        new ImageLoader(context, image, urlText, this).execute();
    }
}
