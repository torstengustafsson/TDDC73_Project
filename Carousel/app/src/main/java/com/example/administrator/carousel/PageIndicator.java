package com.example.administrator.carousel;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Handles the navigation of the carousel by allowing the user to move left and right
 * using two arrow buttons. Circles are used to show where user is positioned.
 */

public class PageIndicator extends View {

    private Paint paint;
    private Rect boundsRect;
    private int width;
    private int height;

    public PageIndicator(Context context) {
        super(context);

        // create the Paint and set its color
        init();
    }

    public PageIndicator(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public PageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        Log.i("PageIndicator", "width: " + getWidth() + " Height: " + getHeight());
        canvas.clipRect(0, 0, getWidth(), getHeight(), Region.Op.REPLACE);
        canvas.drawCircle(getWidth()/2, getHeight()/2, 20, paint);

    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.GRAY);
        boundsRect = new Rect();
    }

}