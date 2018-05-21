package com.example.nirzhar.tourmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class EventProfileActivity extends AppCompatActivity {
    ListView listView;
    EventManager eventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);;
        mTitle.setText("Event Profile");

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView= (ListView) findViewById(R.id.eventLV);
        eventManager=new EventManager(this);
        final ArrayList<Event> eventArrayList=eventManager.getAllEvent();
        EventAdapter adapter=new EventAdapter(this,eventArrayList) {
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(EventProfileActivity.this,TravelHome.class);
                intent.putExtra("id",eventArrayList.get(i).getEventID());
                intent.putExtra("name",eventArrayList.get(i).getDesName());
                intent.putExtra("fDate",eventArrayList.get(i).getFromaDate());
                intent.putExtra("tDate",eventArrayList.get(i).getToDate());
                intent.putExtra("estBudget",eventArrayList.get(i).getBudget());
                startActivity(intent);
            }
        });
    }
    public void moveToAdd(View view) {
        Intent intent=new Intent(this,EventActivity.class);
        startActivity(intent);
    }
}