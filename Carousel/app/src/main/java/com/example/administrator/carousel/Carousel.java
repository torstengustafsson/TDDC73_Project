package com.example.administrator.carousel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Displays a sequence of images in  a carousel format. It does not scroll on its own. Instead the
 * user may scroll by using the existing back- and forth buttons.
 */

public class Carousel extends LinearLayout {

    Context context;

    LinearLayout layoutTop, layoutBottom;
    TextView header;
    PageIndicator pageIndicator;

    ArrayList<Item> items;

    Paint paint;

    public Carousel(Context context) {
        super(context);

        this.context = context;

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
        pageIndicator.setCallback(this);

        paint = new Paint();
    }

    public void setHeaderText(String _text) {
        header.setText(_text);
    }

    public void updateResults(ArrayList<Item> res, String searchString, boolean isOnline) {
        items = res;
        pageIndicator.reset();
        setHeaderText("Search results for '" + searchString.replaceAll("%20", " ") + "'");
        updateItems(pageIndicator.getCurrentPage(), isOnline);
    }

    public void updateResults(ArrayList<Item> res) {
        items = res;
        pageIndicator.reset();
        updateItems(pageIndicator.getCurrentPage(), true);
    }

    public void updateResults() {
        updateItems(pageIndicator.getCurrentPage(), true);
    }

    private void updateItems(int page, boolean isOnline) {
        if(items == null) return;

        layoutBottom.removeAllViews();
        if (items.size() == 0) {
            Toast toast = Toast.makeText(getContext(),
                    (isOnline ? "No results found" : "Missing internet connection, please reconnect to internet and search again."),
                    Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();
            return;
        }
        pageIndicator.setItems(items.size());
        for (int i = 4 * page; i < Math.min(4 * page + 4, items.size()); i++) {
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
