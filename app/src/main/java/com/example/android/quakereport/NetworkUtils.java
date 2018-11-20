package com.example.android.quakereport;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

 class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String QUERY_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?";//format=geojson&minmagnitude=4.5&limit=10
    private static final String FORMAT = "format";
    private static final String MIN_MAG = "minmagnitude";
    private static final String LIMIT = "limit";

    private static final String FORMAT_TYPE = "geojson";
    private static final String MIN_MAG_VAL = "4.5";
    private static final String LIMIT_VAL = "50";

    static String getEarthQuakeInfo(){

        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String outputString;

        try {
            Uri builtURI = Uri.parse(QUERY_URL).buildUpon()
                    .appendQueryParameter(FORMAT,FORMAT_TYPE)
                    .appendQueryParameter(MIN_MAG,MIN_MAG_VAL)
                    .appendQueryParameter(LIMIT,LIMIT_VAL)
                    .build();

            URL requestURL = new URL(builtURI.toString());
            httpURLConnection = (HttpURLConnection)requestURL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuilder stringBuffer = new StringBuilder();

            if(inputStream == null){
                return null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line).append("\n");
            }

            if(stringBuffer.length() == 0){
                return null;
            }

            outputString = stringBuffer.toString();

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }finally {
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG,outputString);
        return outputString;
    }
}
