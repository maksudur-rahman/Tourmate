package com.example.nirzhar.tourmate.weatherApi;

import com.example.nirzhar.tourmate.weatherJsonData.WeatherForecast;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Taseen Bappi on 22/09/2017.
 */

public interface WeatherInterfaceApi {

    @GET("forecast/daily?APPID=37df53a877aaf9e887db5018b90ed335&q=Dhaka&mode=json&units=metric&cnt=7")
    Call<WeatherForecast>getWeatherInfo();

}
