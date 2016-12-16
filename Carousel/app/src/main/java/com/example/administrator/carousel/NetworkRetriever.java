package com.example.administrator.carousel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class NetworkRetriever extends AsyncTask<String, Void, ArrayList<Item>> {

    Context context;
    Carousel carousel;
    private String imageDBURL;
    private int maxResults;

    private String searchString;

    NetworkRetriever(Context context, Carousel carousel, String imageDBURL, int max) {
        this.context = context;
        this.carousel = carousel;
        this.imageDBURL = imageDBURL;
        maxResults = max;
    }

    protected ArrayList<Item> doInBackground(String... strings) {
        ArrayList<Item> res = new ArrayList<>();

        searchString = strings[0];

        if(strings[0].equals("")) return res; // We may get error when asking for an empty string

        int page = 0;
        while (true) {
            try {
                //The URL id is actually not used here, because of the way NetworkRetriever is implemented, it is enough to store the id variable locally.
                URL url = new URL(imageDBURL + searchString + "&page=" + ++page);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    String resultString = s.hasNext() ? s.next() : "";
                    JSONObject jObj = new JSONObject(resultString);
                    Log.i("NetworkRetriever", "bool? " + page + ": " + jObj.getString("Response") + (jObj.getString("Response").equals("True")));

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
                Log.i("NetworkRetriever", (isOnline() ? "No result" : "No internet"));
                return res;
            }
        }

        return res;
    }

    protected void onPostExecute(ArrayList<Item> result) {
        Log.i("NetworkRetriever", "size: " + result.size());
        carousel.updateResults(result, searchString, isOnline());
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}