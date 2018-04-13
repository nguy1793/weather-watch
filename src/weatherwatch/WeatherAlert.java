package com.weatherwatch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherAlert {

    public String getPayload(String weatherEndPoint) throws IOException {
        String content;
        URL endpoint = new URL(weatherEndPoint);

        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream stream = connection.getInputStream();
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder response = new StringBuilder();
        while((content = streamReader.readLine()) != null) {
            response.append(content);
        }

        content = response.toString();
        return content;
    }

    public void parseJSONForecast(String payload) throws JSONException {
        JSONObject jsonObject = new JSONObject(payload);
        JSONObject jsonResult = jsonObject.getJSONObject("query").getJSONObject("results");
        JSONObject jsonData = jsonResult.getJSONObject("channel").getJSONObject("item");
        JSONArray jsonForecast = jsonData.getJSONArray("forecast");

        for(int i = 0; i < jsonForecast.length(); i++) {
            JSONObject forecast = jsonForecast.getJSONObject(i);
            generateAlert(forecast);
        }

    }

    private void generateAlert(JSONObject forecast) {
        String conditionText = forecast.optString("text").toLowerCase();
        String date = formatDateString(forecast.optString("date"));
        String day = getFullDayText(forecast.optString("day"));
        int highTemp = forecast.optInt("high");
        int lowTemp = forecast.optInt("low");


        if(conditionText.contains("snow")) {
            new Alert(date, day, "Snow").printAlert();
        } else if (conditionText.contains("rain") || (conditionText.contains("showers") && !conditionText.contains("snow"))) {
            new Alert(date, day, "Rain").printAlert();
        } else if (conditionText.contains("ice")) {
            new Alert(date, day, "Ice").printAlert();
        } else if (conditionText.contains("thunderstorm")) {
            new Alert(date, day, "Thunderstorm").printAlert();
        }

        if(highTemp > 85) {
            new Alert(date, day, "High heat").printAlert();
        }
        if(lowTemp < 32) {
            new Alert(date, day, "Freezing temperature").printAlert();
        }
    }


    private String getFullDayText(String day) {
        String dayString;
        switch(day) {
            case "Sun":
                dayString = "Sunday";
                break;
            case "Mon":
                dayString =  "Monday";
                break;
            case "Tue":
                dayString =  "Tuesday";
                break;
            case "Wed":
                dayString =  "Wednesday";
                break;
            case "Thu":
                dayString =  "Thursday";
                break;
            case "Fri":
                dayString =  "Friday";
                break;
            case "Sat":
                dayString =  "Saturday";
                break;
            default:
                dayString = "Invalid day";
                break;
        }
        return dayString;
    }

    private String formatDateString(String date) {
        String[] tokens = date.split(" ");
        return tokens[1] + " " + tokens[0] + ", " + tokens[2];
    }
}
