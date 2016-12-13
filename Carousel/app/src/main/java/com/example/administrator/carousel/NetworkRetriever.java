package com.example.administrator.carousel;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private int maxResults;
    private int id;

    NetworkRetriever(Carousel callback, int max, int id) {
        this.callback = callback;
        maxResults = max;
        this.id = id;
    }

    protected ArrayList<Item> doInBackground(String... strings) {
        ArrayList<Item> res = new ArrayList<>();
        if(strings[0].equals("")) return res; // We may get error when asking for an empty string
        try {
            //The URL id is actually not used here, because of the way NetworkRetriever is implemented, it is enough to store the id variable locally.
            URL url = new URL("http://www.omdbapi.com/?s=" + strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                String resultString = s.hasNext() ? s.next() : "";
                JSONObject jObj = new JSONObject(resultString);
                JSONArray jArr = jObj.getJSONArray("Search");

                for(int i=0; i < Math.min(jArr.length(), maxResults); i++) {
                    Log.i("NetworkRetriever", "1");
                    JSONObject item = ((JSONObject) jArr.get(i));
                    Log.i("NetworkRetriever", "2");
                    String name = item.getString("Title");
                    Log.i("NetworkRetriever", "3");
                    String imageLink = item.getString("Poster");
                    Log.i("NetworkRetriever", "" + name + " " + imageLink);
                    res.add(new Item(name));
                }
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    protected void onPostExecute(ArrayList<Item> result) {
        callback.updateResults(result, id);
    }
}