package com.ju.rotno.tourmate;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    EditText fromDateTV,toDateTV;
    Button eventBtn;
    EditText destinationET,budgetET;
    private int mYear, mMonth, mDay;

    EventManager eventManager;
    Button dltBtn;
    int eventId = 0;
    private int id;
    private String eventName;
    private String fDate;
    private String tDate;
    private int estBudget;
    private long isSuccessfull;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        id=getIntent().getIntExtra("id",0);
        eventName=getIntent().getStringExtra("name");
        fDate=getIntent().getStringExtra("fDate");
        tDate=getIntent().getStringExtra("tDate");
        estBudget=getIntent().getIntExtra("estBudget",0);

        eventManager=new EventManager(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        mTitle.setText("Travel Event");

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fromDateTV= (EditText) findViewById(R.id.fromDateTV);
        toDateTV= (EditText) findViewById(R.id.toDateTV);
        eventBtn= (Button) findViewById(R.id.eventBtn);
        dltBtn= (Button) findViewById(R.id.deleteBtn);
        destinationET= (EditText) findViewById(R.id.desET);
        budgetET= (EditText) findViewById(R.id.budgetET);
        fromDateTV.setOnClickListener(this);
        toDateTV.setOnClickListener(this);

        destinationET.setText(eventName);
        fromDateTV.setText(fDate);
        toDateTV.setText(tDate);
        budgetET.setText(String.valueOf(estBudget));
        if(id>0){
            budgetET.setText(String.valueOf(estBudget));
            eventBtn.setText("Update");
            dltBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == fromDateTV) {
            DatePickerDialog datePickerDialog = null;
            final Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 1);
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            fromDateTV.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == toDateTV) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            toDateTV.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
    public void save(View view) {
        Event event=new Event(id,destinationET.getText().toString(),fromDateTV.getText().toString(),toDateTV.getText().toString(),Integer.valueOf(budgetET.getText().toString()));
        if(id>0){
            isSuccessfull=eventManager.updateEvent(event);
        }else{
            isSuccessfull=eventManager.addEvent(event);
        }

        if(isSuccessfull>0){
            Toast.makeText(this, ""+isSuccessfull, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,EventProfileActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "unable to save data", Toast.LENGTH_SHORT).show();
        }

    }

    public void Delete(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                long isDeleted=eventManager.deleteEvent(id);
                if(isDeleted>0){
                    Intent intent=new Intent(EventActivity.this,EventProfileActivity.class);
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.show();

    }
}

