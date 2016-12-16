package com.example.administrator.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by Administrator on 08/12/2016.
 */

public class Carousel extends LinearLayout {

    NetworkRetriever networkRetriever;
    String imageDBURL;

    LinearLayout layoutTop, layoutBottom;
    TextView header;
    PageIndicator pageIndicator;

    ArrayList<Item> items;

    Paint paint;

    public Carousel(Context context, String imageDBURL) {
        super(context);

        this.imageDBURL = imageDBURL;

        setPadding(10, 10, 10, 10);
        setOrientation(VERTICAL);
        setBackgroundColor(Color.parseColor("#FFCCCC"));
        LayoutParams params = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        setLayoutParams(params);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.carousel, this);


        layoutTop = (LinearLayout) findViewById(R.id.headerLayout);
        layoutBottom = (LinearLayout) findViewById(R.id.carouselItemLayout);
        header = (TextView) findViewById(R.id.headerText);
        pageIndicator = (PageIndicator) findViewById(R.id.pageIndicatorView);

        paint = new Paint();
    }

    public void setHeaderText(String _text) {
        header.setText(_text);
    }

    public void search(String _text) {
        networkRetriever = new NetworkRetriever(this, imageDBURL, 10);
        networkRetriever.execute(_text.replaceAll(" ", "%20"));
    }

    public void updateResults(ArrayList<Item> res) {
        items = res;
        updateItems(pageIndicator.getCurrentPage());
    }

    private void updateItems(int page) {
        Log.d("Carousel", "Updating items");
        layoutBottom.removeAllViews();
        if (items.size() == 0) {
            Toast toast = Toast.makeText(getContext(), "Missing internet connection, please connect to internet and restart app.", Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();
            return;
        }
        pageIndicator.setItems(items.size());
        for (int i = 0; i < Math.min(items.size(), 4); i++) {
            ItemView item = new ItemView(getContext(), items.get(i).name, items.get(i).imageUrl);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1f);
            item.setLayoutParams(llp);
            layoutBottom.addView(item);
        }
        pageIndicator.invalidate();
    }
}
