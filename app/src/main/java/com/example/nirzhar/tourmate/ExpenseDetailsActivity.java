package com.example.nirzhar.tourmate;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ExpenseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);
        Toast.makeText(this, "expenseId"+getIntent().getIntExtra("expenseId",0), Toast.LENGTH_SHORT).show();
    }
}
