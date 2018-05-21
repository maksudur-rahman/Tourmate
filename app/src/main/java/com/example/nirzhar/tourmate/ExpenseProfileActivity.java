package com.example.nirzhar.tourmate;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpenseProfileActivity extends AppCompatActivity {
    ListView listView;
    ExpenseManager expenseManager;
    String dst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        dst=getIntent().getStringExtra("namedst");
        setSupportActionBar(toolbar);
        mTitle.setText("Expense Profile");

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView= (ListView) findViewById(R.id.expenseLV);
        expenseManager=new ExpenseManager(this);
        expenseManager.setEventname(dst);
        final ArrayList<Expense> expenseArrayList=expenseManager.getAllExpense();
        ExpenseAdapter adapter=new ExpenseAdapter(this,expenseArrayList) {
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ExpenseProfileActivity.this,ExpenseActivity.class);
                intent.putExtra("id",expenseArrayList.get(i).getId());
                intent.putExtra("name",expenseArrayList.get(i).getExpDetails());
                intent.putExtra("expAmount",expenseArrayList.get(i).getAmount());
                intent.putExtra("namedst",dst);
                startActivity(intent);
            }
        });
    }
    public void moveToAddExpense(View view) {
        Intent intent=new Intent(this,ExpenseActivity.class);
        intent.putExtra("namedst",dst);
        startActivity(intent);
    }
}

