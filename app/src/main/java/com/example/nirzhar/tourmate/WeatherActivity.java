package com.example.nirzhar.tourmate;
import android.content.Intent;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nirzhar.tourmate.weatherApi.WeatherInterfaceApi;
import com.example.nirzhar.tourmate.weatherApi.WeatherInterfaceApi;
import com.example.nirzhar.tourmate.weatherJsonData.City;
import com.example.nirzhar.tourmate.weatherJsonData.List;
import com.example.nirzhar.tourmate.weatherJsonData.Temp;
import com.example.nirzhar.tourmate.weatherJsonData.Weather;
import com.example.nirzhar.tourmate.weatherJsonData.WeatherForecast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    WeatherInterfaceApi weatherInterfaceApi;
    private TextView summaryTV, maxtempTV, minTempTV, currentTempTV , date;
    private ImageView icon;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        name=getIntent().getStringExtra("namedst");
        setTitle(name);

        summaryTV = (TextView) findViewById(R.id.summury);
        maxtempTV = (TextView) findViewById(R.id.hightemp);
        minTempTV = (TextView) findViewById(R.id.lowTemp);
        date = (TextView) findViewById(R.id.date);
        currentTempTV = (TextView) findViewById(R.id.curentTemp);




        getWeatherData();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                WeatherActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.addCity) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.cityList) {

            Intent i = new Intent(WeatherActivity.this, DetailsData.class);

            startActivity(i);
            // Handle the camera action
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void getWeatherData() {
        weatherInterfaceApi = RetrofitClientService.getRetrofitClientServic()
                .create(WeatherInterfaceApi.class);
        Call<WeatherForecast>weatherForecastCall = weatherInterfaceApi.getWeatherInfo();
        weatherForecastCall.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                Log.d("Check", "onResponse: Success");
                WeatherForecast forecast = response.body();

                java.util.List<List> tempList = forecast.getList();

                java.util.List<Weather> weListList = tempList.get(0).getWeather();
                Temp tempClass = tempList.get(0).getTemp();
                String temp = Double.toString(tempClass.getDay());
                String description = weListList.get(0).getDescription();


                long ms = Long.parseLong(String.valueOf(tempList.get(0).getDt()));
                Date d = new Date(ms*1000);
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                String newDate= dateformat.format(d);
                date.setText(newDate);



                String maxtemp = Double.toString(tempClass.getMax());
                String mintemp = Double.toString(tempClass.getMin());


                //City mCity = forecast.getCity();
                //String city = mCity.getName().toString();

                currentTempTV.setText(temp+"\u2103");
                maxtempTV.setText("Max: "+maxtemp+"\u2103");
                minTempTV.setText("Min: "+mintemp+"\u2103");
                summaryTV.setText(description);

                //Toast.makeText(MainActivity.this,"Temp:"+temp+" City:"+city,Toast.LENGTH_LONG).show();


                //Log.d("CityName", "onResponse: "+response.body());

            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {

                Toast.makeText(WeatherActivity.this,"Please Check Your Internet Connection",Toast.LENGTH_LONG).show();

                Log.d("Error", "onFailure: "+t.getMessage());
            }
        });

    }

}
