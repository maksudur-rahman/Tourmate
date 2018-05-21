package com.example.nirzhar.tourmate;

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
import android.widget.Toast;

public class ExpenseActivity extends AppCompatActivity {

    EditText expDetailsET,expAmountET;
    Button expBtn,deltBtn;

    ExpenseManager expenseManager;
    int expenseId = 0;
    public String eventName;
    private int id;
    private String expenseName;
    private int expAmount;
    private long isSuccessfull;
     String dst="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        dst=getIntent().getStringExtra("namedst");
        id=getIntent().getIntExtra("id",0);
        expenseName=getIntent().getStringExtra("name");
        expAmount=getIntent().getIntExtra("expAmount",0);
        expenseManager=new ExpenseManager(this);
        id=getIntent().getIntExtra("id",0);
        eventName=getIntent().getStringExtra("name");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        mTitle.setText("Travel Expense");

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        expDetailsET= (EditText) findViewById(R.id.expenseDetailsET);
        expAmountET= (EditText) findViewById(R.id.expenseAmountET);
        expBtn= (Button) findViewById(R.id.expenseEntryBtn);
        deltBtn= (Button) findViewById(R.id.expDltBtn);

        expDetailsET.setText(expenseName);
        if(id>0){
            expAmountET.setText(String.valueOf(expAmount));
            expBtn.setText("Update");
            deltBtn.setVisibility(View.VISIBLE);
        }
    }
    public void saveExpense(View view) {
        Expense expense=new Expense(id,expDetailsET.getText().toString(),Integer.valueOf(expAmountET.getText().toString()),dst);
        if(id>0){
            isSuccessfull=expenseManager.updateExpense(expense);
        }else{
            isSuccessfull=expenseManager.addExpense(expense);
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

    public void DeleteExpense(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                long isDeleted=expenseManager.deleteExpense(id);
                if(isDeleted>0){
                    Intent intent=new Intent(ExpenseActivity.this,ExpenseProfileActivity.class);
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.show();

    }
}