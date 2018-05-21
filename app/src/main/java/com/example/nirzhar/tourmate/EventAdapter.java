package com.example.nirzhar.tourmate;

import android.widget.Adapter;

/**
 * Created by Mac on 23-10-17.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mac on 17-09-17.
 */
public class EventAdapter extends ArrayAdapter<Event>{
    private Context context;
    private ArrayList<Event> eventArrayList;

    public EventAdapter(Context context,ArrayList<Event> eventArrayList) {
        super(context, R.layout.view_event_list, eventArrayList);
        this.context = context;
        this.eventArrayList = eventArrayList;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.view_event_list, parent, false);

        TextView destinationTV = convertView.findViewById(R.id.destinationTV);
        TextView fromDateTV = convertView.findViewById(R.id.fDateTV);
        TextView toDateTV=convertView.findViewById(R.id.tDateTV);
        TextView estTV = convertView.findViewById(R.id.estBudgetTV);
        ImageView takaIV=convertView.findViewById(R.id.moneyBtn);

        destinationTV.setText(eventArrayList.get(position).getDesName());
        fromDateTV.setText(eventArrayList.get(position).getFromaDate());
        toDateTV.setText(eventArrayList.get(position).getToDate());
        estTV.setText(String.valueOf(eventArrayList.get(position).getBudget())+" TK");
        takaIV.setImageResource(R.mipmap.icontaka);
        takaIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(),ExpenseProfileActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}