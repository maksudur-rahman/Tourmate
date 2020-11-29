package com.ju.rotno.tourmate;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Mac on 17-09-17.
 */
public class ExpenseAdapter extends ArrayAdapter<Expense>{
    private Context context;
    private ArrayList<Expense> expenseArrayList;

    public ExpenseAdapter(Context context,ArrayList<Expense> expenseArrayList) {
        super(context, R.layout.view_expense_list, expenseArrayList);
        this.context = context;
        this.expenseArrayList = expenseArrayList;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.view_expense_list, parent, false);

        TextView dateTV = convertView.findViewById(R.id.dateTV);
        TextView timeTV = convertView.findViewById(R.id.timeTV);
        TextView expDeTV=convertView.findViewById(R.id.expdeTV);
        TextView expAmtTV = convertView.findViewById(R.id.expAmountTV);
      SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
       Calendar c = Calendar.getInstance();
      dateTV.setText(dateFormat.format(c.getTime()));
        timeTV.setText(timeFormat.format(c.getTime()));

        expDeTV.setText(expenseArrayList.get(position).getExpDetails());

        expAmtTV.setText(String.valueOf(expenseArrayList.get(position).getAmount())+" TK");
        return convertView;
    }
}
