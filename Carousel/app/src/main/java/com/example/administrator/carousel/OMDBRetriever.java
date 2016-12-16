package com.example.administrator.carousel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Retrieves URL:s from the OMDB API. Created to demonstrate our custom Carousel view.
 */

class OMDBRetriever extends AsyncTask<String, Void, ArrayList<Item>> {

    Context context;
    Carousel carousel;
    private int maxResults;

    private String searchString;

    OMDBRetriever(Context context, Carousel carousel, int max) {
        this.context = context;
        this.carousel = carousel;
        maxResults = max;
    }

    protected ArrayList<Item> doInBackground(String... strings) {
        ArrayList<Item> res = new ArrayList<>();

        searchString = strings[0];

        if(strings[0].equals("")) return res; // We may get error when asking for an empty string

        int page = 0;
        while (true) {
            try {
                //The URL id is actually not used here, because of the way OMDBRetriever is implemented, it is enough to store the id variable locally.
                URL url = new URL("http://www.omdbapi.com/?s=" + searchString + "&page=" + ++page);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    String resultString = s.hasNext() ? s.next() : "";
                    JSONObject jObj = new JSONObject(resultString);

                    if(jObj.getString("Response").equals("False") || res.size() >= maxResults) break;

                    JSONArray jArr = jObj.getJSONArray("Search");

                    for (int i = 0; i < Math.min(jArr.length(), maxResults - res.size()); i++) {
                        JSONObject item = ((JSONObject) jArr.get(i));
                        String name = item.getString("Title");
                        String imageLink = item.getString("Poster");
                        res.add(new Item(name, imageLink));
                    }
                } catch (Exception e) {
                    urlConnection.disconnect();
                    break;
                } finally {
                    urlConnection.disconnect();
                }
            }catch(Exception e){
                return res;
            }
        }

        return res;
    }

    protected void onPostExecute(ArrayList<Item> result) {
        carousel.updateResults(result, searchString, isOnline());
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}