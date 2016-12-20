package com.example.administrator.carousel;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;

/**
 * Handles the navigation of the carousel by allowing the user to move left and right
 * using two arrow buttons. Circles are used to show where user is positioned.
 */

public class PageIndicator extends View {

    Carousel callback;
    private Paint paint;
    private int items;
    private int pages;
    private ShapeDrawable next;
    private ShapeDrawable back;

    private int currentPage = 0;

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
        canvas.clipRect(0, 0, getWidth(), getHeight(), Region.Op.REPLACE);
        int radius = Math.min(getWidth()/20, getHeight()/2);
        for(int i = 0; i < pages; i++){
            paint.setColor(currentPage == i ? GREEN : GRAY);
            canvas.drawCircle(((i+1)*radius*2.5f), getHeight()/2, radius, paint);
        }

        next.setBounds(getWidth()-getWidth()*4/20, getHeight()/4, getWidth()-getWidth()/20, getHeight()*3/4);
        back.setBounds(getWidth()-getWidth()*8/20, getHeight()/4, getWidth()-getWidth()*5/20, getHeight()*3/4);

        next.draw(canvas);
        back.draw(canvas);


    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.GRAY);
        pages = 1;
        next = new ShapeDrawable(new RectShape());
        back = new ShapeDrawable(new RectShape());
    }

    public void setItems(int _items){
        this.items = _items;
        pages = (int) Math.ceil(items/4.0);
    }

    public int getItems(){
        return items;
    }

    public void reset() {
        currentPage = 0;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // MotionEvent object holds X-Y values
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(checkBoundsNext(event.getX(), event.getY()) && currentPage < pages-1){
                currentPage++;
                callback.updateResults();
            }else if(checkBoundsBack(event.getX(), event.getY()) && currentPage > 0) {
                currentPage--;
                callback.updateResults();
            }
        }

        return super.onTouchEvent(event);
    }

    public boolean checkBoundsNext(float x, float y){
        if(x > next.getBounds().left && x < next.getBounds().right && y < next.getBounds().bottom && y > next.getBounds().top){
            return true;
        }
        return false;
    }

    public boolean checkBoundsBack(float x, float y){
        if(x > back.getBounds().left && x < back.getBounds().right && y < back.getBounds().bottom && y > back.getBounds().top){
            return true;
        }
        return false;
    }

    public void setCallback(Carousel callback) {
        this.callback = callback;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}