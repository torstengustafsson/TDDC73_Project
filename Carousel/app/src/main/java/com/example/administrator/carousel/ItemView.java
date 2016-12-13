package com.example.administrator.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Administrator on 08/12/2016.
 */

public class ItemView extends LinearLayout {

    TextView name;
    ImageView image;
    ImageLoader imageLoader;

    public ItemView(Context context, String nameText, String urlText) {
        super(context);
        setPadding(10, 10, 10 ,10);
        setOrientation(VERTICAL);

        name = new TextView(context);
        name.setText(nameText);
        name.setTextSize(8);
        addView(name);

        image = new ImageView(context);
        addView(image);
        new ImageLoader(context, image, urlText).execute();
    }
}
