package com.ju.rotno.tourmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class EventDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toast.makeText(this, "eventId"+getIntent().getIntExtra("eventId",0), Toast.LENGTH_SHORT).show();
    }
}
