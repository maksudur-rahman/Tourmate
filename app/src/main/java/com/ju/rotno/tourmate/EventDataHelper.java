package com.ju.rotno.tourmate;

/**
 * Created by Mac on 23-10-17.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mac on 14-09-17.
 */
public class EventDataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="eventdb";

    private static final int DATABASE_VERSION=1;

    public static final String TABLE_EVENT="event";
    public static final String COLUMN_EVENT_ID="eventId";
    public static final String COLUMN_EVENT_NAME="eventName";
    public static final String COLUMN_EVENT_FDATE="eventFDate";
    public static final String COLUMN_EVENT_TDATE="eventTDate";
    public static final String COLUMN_EST_BUDGET="estBudget";

    private String createEVENT="create table "+TABLE_EVENT+"("+COLUMN_EVENT_ID+" integer primary key autoincrement,"+COLUMN_EVENT_NAME+" text,"
            +COLUMN_EVENT_FDATE+" text,"+COLUMN_EVENT_TDATE+" text,"+COLUMN_EST_BUDGET+" integer);";

    public EventDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createEVENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop "+TABLE_EVENT+" if exists");
        onCreate(sqLiteDatabase);
    }
}
