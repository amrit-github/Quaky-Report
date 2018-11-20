package com.example.android.quakereport;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FetchData extends AsyncTask<Void, Void, String> {

    private RecyclerView mRecyclerView;
    private ArrayList<EarthquakeModel> mEarthquakeModels = new ArrayList<>();
    private SimpleDateFormat dateFormatter;

    FetchData(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        dateFormatter = new SimpleDateFormat("MMM dd, yyyy,  hh:mm aaa");
    }

    @Override
    protected String doInBackground(Void... voids) {
        return NetworkUtils.getEarthQuakeInfo();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject root = new JSONObject(s);
            JSONArray featuresArray = root.getJSONArray("features");

            for (int i = 0; i < featuresArray.length(); i++) {
                JSONObject earthQuakeObject = featuresArray.getJSONObject(i);

                double magnitude;
                String place;
                Long timeInmilliSeconds;
                String dateToDisplay;

                JSONObject properties = earthQuakeObject.getJSONObject("properties");
                magnitude = properties.getDouble("mag");
                place = properties.getString("place");
                timeInmilliSeconds = properties.getLong("time");
                Date dateObject = new Date(timeInmilliSeconds);
                dateToDisplay = dateFormatter.format(dateObject);

                try{
                    mEarthquakeModels.add(new EarthquakeModel(place, magnitude,dateToDisplay));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyAdapter myAdapter = new MyAdapter(mEarthquakeModels);
        mRecyclerView.setAdapter(myAdapter);
    }
}
