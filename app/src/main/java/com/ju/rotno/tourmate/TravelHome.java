package com.ju.rotno.tourmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TravelHome extends AppCompatActivity {
    private String namedst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_home);
        namedst=getIntent().getStringExtra("name");
    }

    public void expense(View view) {
        Intent intent=new Intent(TravelHome.this,ExpenseProfileActivity.class);
        intent.putExtra("namedst",namedst);
        startActivity(intent);
    }


    public void event(View view) {
        Intent intent=new Intent(TravelHome.this,EventProfileActivity.class);
        startActivity(intent);
    }

    public void nearby(View view) {
        Intent intent=new Intent(TravelHome.this,MapsActivity.class);
        intent.putExtra("namedst",namedst);
        startActivity(intent);
    }

    public void weather(View view) {
        Intent intent=new Intent(TravelHome.this,WeatherActivity.class);
        intent.putExtra("namedst",namedst);
        startActivity(intent);
    }
    public void moment(View view) {
        Intent intent=new Intent(TravelHome.this,ImageActivity.class);
        intent.putExtra("namedst",namedst);
        startActivity(intent);
    }
}
