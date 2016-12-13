package com.example.administrator.carousel;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Handles the navigation of the carousel by allowing the user to move left and right
 * using two arrow buttons. Circles are used to show where user is positioned.
 */

public class PageIndicator extends View {

    public PageIndicator(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
