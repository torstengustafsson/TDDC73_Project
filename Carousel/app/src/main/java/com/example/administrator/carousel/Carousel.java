package com.example.administrator.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by Administrator on 08/12/2016.
 */

public class Carousel extends LinearLayout {

    NetworkRetriever networkRetriever;

    LinearLayout layoutTop, layoutBottom;
    TextView header;

    ArrayList<Item> items;

    Paint paint;

    public Carousel(Context context) {
        super(context);

        setPadding(10, 10, 10 ,10);
        setOrientation(VERTICAL);
        LayoutParams params = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        setLayoutParams(params);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.carousel, this);

/*
        layoutTop = (LinearLayout) findViewById(R.id.headerLayout);
        layoutBottom = (LinearLayout) findViewById(R.id.carouselItemLayout);
        header = (TextView) findViewById(R.id.headerText);
        if(header == null) Log.d("hej", "hej"); else Log.d("hej", "hallo");
        header.setText("A Header Text");

        for(int i = 0; i < 4; i++) {
            TextView t = new TextView(context);
            t.setText("dsd");
            layoutBottom.addView(t);
        }
*/
        //networkRetriever = new NetworkRetriever(this, 10, 0);
        //networkRetriever.execute("star%20wars");

        paint = new Paint();
    }

    public void setHeaderText(String _text) {
        header.setText(_text);
    }

    public void updateResults(ArrayList<Item> res, int id) {
        items = res;
        updateItems();
    }

    private void updateItems() {
        layoutBottom.removeAllViews();
        for(int i = 0; i < Math.min(items.size(), 4); i++) {
            layoutBottom.addView(new ItemView(getContext(), items.get(i).name));
        }
    }
}
