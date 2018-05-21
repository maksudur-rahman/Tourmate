package com.example.nirzhar.tourmate;

/**
 * Created by Mac on 24-10-17.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mac on 14-09-17.
 */
public class ExpenseDataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="expensedb";
    private static final int DATABASE_VERSION=1;
    private ExpenseActivity expenseActivity=new ExpenseActivity();
    private String name=expenseActivity.eventName;

    public static final String TABLE_EXPENSE="Expense";
    public static final String COLUMN_EXPENSE_ID="expenseId";
    public static final String COLUMN_EXPENSE_NAME="expenseName";
    public static final String COLUMN_EXPENSE_AMOUNT="expenseAmount";
    public static final String COLUMN_EXPENSE_Event="expenseEvent";

    private String createEVENT="create table "+TABLE_EXPENSE+"("+COLUMN_EXPENSE_ID+" integer primary key autoincrement,"+COLUMN_EXPENSE_NAME+" text," +COLUMN_EXPENSE_Event+" text," +COLUMN_EXPENSE_AMOUNT+" integer);";

    public ExpenseDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createEVENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop "+TABLE_EXPENSE+" if exists");
        onCreate(sqLiteDatabase);
    }
}