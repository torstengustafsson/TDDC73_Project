package com.example.administrator.carousel;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 08/12/2016.
 */

public class ItemView extends LinearLayout {

    TextView name;
    ImageView image;

    public ItemView(Context context, String nameText) {
        super(context);
        setPadding(10, 10, 10 ,10);
        setOrientation(VERTICAL);

        TextView name = new TextView(context);
        name.setText(nameText);
        addView(name);
    }

}
