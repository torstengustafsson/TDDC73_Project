package com.example.administrator.carousel;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class NetworkRetriever extends AsyncTask<String, Void, ArrayList<Item>> {

    private Carousel callback;
    private String imageDBURL;
    private int maxResults;

    NetworkRetriever(Carousel callback, String imageDBURL, int max) {
        this.callback = callback;
        this.imageDBURL = imageDBURL;
        maxResults = max;
    }

    protected ArrayList<Item> doInBackground(String... strings) {
        ArrayList<Item> res = new ArrayList<>();
        if(strings[0].equals("")) return res; // We may get error when asking for an empty string
        try {
            //The URL id is actually not used here, because of the way NetworkRetriever is implemented, it is enough to store the id variable locally.
            URL url = new URL(imageDBURL + strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                String resultString = s.hasNext() ? s.next() : "";
                JSONObject jObj = new JSONObject(resultString);
                JSONArray jArr = jObj.getJSONArray("Search");

                for(int i=0; i < Math.min(jArr.length(), maxResults); i++) {
                    Log.i("NetworkRetriever", "MaxResults="+maxResults);
                    JSONObject item = ((JSONObject) jArr.get(i));
                    Log.i("NetworkRetriever", "2");
                    String name = item.getString("Title");
                    Log.i("NetworkRetriever", "3");
                    String imageLink = item.getString("Poster");
                    Log.i("NetworkRetriever", "" + name + " " + imageLink);
                    res.add(new Item(name, imageLink));
                }
                Log.i("NetworkRet", "Res: " + res.size());
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {

            Log.i("NetworkRetriever", (isOnline() ? "No result" : "No internet"));
            return res;
        }

        return res;
    }

    protected void onPostExecute(ArrayList<Item> result) {
        callback.updateResults(result);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) callback.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}