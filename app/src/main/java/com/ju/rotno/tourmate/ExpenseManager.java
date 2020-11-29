package com.ju.rotno.tourmate;

/**
 * Created by Mac on 24-10-17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpenseManager {
    private Context context;
    private ExpenseDataHelper expenseDataHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String eventname;

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }




    public ExpenseManager(Context context) {
        this.context = context;
        expenseDataHelper=new ExpenseDataHelper(context);
    }
    public long addExpense(Expense expense){
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ExpenseDataHelper.COLUMN_EXPENSE_NAME,expense.getExpDetails());
        contentValues.put(ExpenseDataHelper.COLUMN_EXPENSE_AMOUNT,expense.getAmount());
        contentValues.put(ExpenseDataHelper.COLUMN_EXPENSE_Event,expense.getName());
        long insertedRow=sqLiteDatabase.insert(ExpenseDataHelper.TABLE_EXPENSE,null,contentValues);
        sqLiteDatabase.close();
        return insertedRow;
    }

    public long updateExpense(Expense expense){
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ExpenseDataHelper.COLUMN_EXPENSE_NAME,expense.getExpDetails());
        contentValues.put(ExpenseDataHelper.COLUMN_EXPENSE_AMOUNT,expense.getAmount());
        contentValues.put(ExpenseDataHelper.COLUMN_EXPENSE_Event,expense.getName());
        long insertedRow=sqLiteDatabase.update(ExpenseDataHelper.TABLE_EXPENSE,contentValues,ExpenseDataHelper.COLUMN_EXPENSE_ID+" = "+expense.getId(),null);
        sqLiteDatabase.close();
        return insertedRow;

    }
    public long deleteExpense(int id){
        open();
        long deleted=sqLiteDatabase.delete(ExpenseDataHelper.TABLE_EXPENSE,ExpenseDataHelper.COLUMN_EXPENSE_ID+" = "+id,null);
        sqLiteDatabase.close();
        return deleted;
    }


    public ArrayList<Expense> getAllExpense(){
        ArrayList<Expense>expenseArrayList=new ArrayList<>();
        open();
        String selectQuery="select * from "+ExpenseDataHelper.TABLE_EXPENSE;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Expense expense=new Expense();
                expense.setId(cursor.getInt(cursor.getColumnIndex(ExpenseDataHelper.COLUMN_EXPENSE_ID)));
                expense.setExpDetails(cursor.getString(cursor.getColumnIndex(ExpenseDataHelper.COLUMN_EXPENSE_NAME)));
                expense.setAmount(cursor.getInt(cursor.getColumnIndex(ExpenseDataHelper.COLUMN_EXPENSE_AMOUNT)));
                expense.setName(cursor.getString(cursor.getColumnIndex(ExpenseDataHelper.COLUMN_EXPENSE_Event)));
                if(expense.getName()!=null && expense.getName().equals(eventname)){
                expenseArrayList.add(expense);}
            }while(cursor.moveToNext());
        }
        return expenseArrayList;
    }

    public Expense getExpenseById(int id){
        open();
        String selectQuery="select * from "+ExpenseDataHelper.TABLE_EXPENSE+" where "+ExpenseDataHelper.COLUMN_EXPENSE_ID+" = "+id;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        Expense expense=new Expense();
        if(cursor.moveToFirst()){

            expense.setExpDetails(cursor.getString(cursor.getColumnIndex(ExpenseDataHelper.COLUMN_EXPENSE_NAME)));
            expense.setAmount(cursor.getInt(cursor.getColumnIndex(ExpenseDataHelper.COLUMN_EXPENSE_AMOUNT)));
            expense.setId(id);
            expense.setName(cursor.getString(cursor.getColumnIndex(ExpenseDataHelper.COLUMN_EXPENSE_Event)));
        }
        return expense;
    }

    private void open() {
        sqLiteDatabase=expenseDataHelper.getWritableDatabase();
    }
    private void close(){
        sqLiteDatabase.close();
    }
}

