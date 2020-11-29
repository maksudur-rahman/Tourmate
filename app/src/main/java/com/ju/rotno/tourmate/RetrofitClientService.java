package com.ju.rotno.tourmate;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Taseen Bappi on 22/09/2017.
 */

public class RetrofitClientService {

    public static Retrofit getRetrofitClientServic(){

        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory
                .create())
                .build();


    }

}
