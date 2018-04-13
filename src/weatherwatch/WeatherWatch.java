package com.weatherwatch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherWatch {
    public static void main(String[] args) throws IOException {
        WeatherAlert weatherAlert = new WeatherAlert();
        String payload = weatherAlert.getPayload(args[0].toString());
//        String payload = weatherAlert.getPayload("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20%28select%20woeid%20from%20geo.places%281%29%20where%20text%3D%22Chicago%22%29&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        if(payload != null) {
            try{
                weatherAlert.parseJSONForecast(payload);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
