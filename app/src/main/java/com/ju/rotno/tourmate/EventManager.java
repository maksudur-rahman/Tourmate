package com.ju.rotno.tourmate;

/**
 * Created by Mac on 23-10-17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class EventManager {
    private Context context;
    private EventDataHelper eventDataHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EventManager(Context context) {
        this.context = context;
        eventDataHelper=new EventDataHelper(context);
    }
    public long addEvent(Event event){
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(EventDataHelper.COLUMN_EVENT_NAME,event.getDesName());
        contentValues.put(EventDataHelper.COLUMN_EVENT_FDATE,event.getFromaDate());
        contentValues.put(EventDataHelper.COLUMN_EVENT_TDATE,event.getToDate());
        contentValues.put(EventDataHelper.COLUMN_EST_BUDGET,event.getBudget());
        long insertedRow=sqLiteDatabase.insert(EventDataHelper.TABLE_EVENT,null,contentValues);
        sqLiteDatabase.close();
        return insertedRow;
    }

    public long updateEvent(Event event){
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(EventDataHelper.COLUMN_EVENT_NAME,event.getDesName());
        contentValues.put(EventDataHelper.COLUMN_EVENT_FDATE,event.getFromaDate());
        contentValues.put(EventDataHelper.COLUMN_EVENT_TDATE,event.getToDate());
        contentValues.put(EventDataHelper.COLUMN_EST_BUDGET,event.getBudget());
        long insertedRow=sqLiteDatabase.update(EventDataHelper.TABLE_EVENT,contentValues,EventDataHelper.COLUMN_EVENT_ID+" = "+event.getEventID(),null);
        sqLiteDatabase.close();
        return insertedRow;

    }
    public long deleteEvent(int id){
        open();
        long deleted=sqLiteDatabase.delete(EventDataHelper.TABLE_EVENT,EventDataHelper.COLUMN_EVENT_ID+" = "+id,null);
        sqLiteDatabase.close();
        return deleted;
    }


    public ArrayList<Event> getAllEvent(){
        ArrayList<Event>eventArrayList=new ArrayList<>();
        open();
        String selectQuery="select * from "+EventDataHelper.TABLE_EVENT;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Event event=new Event();
                event.setEventID(cursor.getInt(cursor.getColumnIndex(EventDataHelper.COLUMN_EVENT_ID)));
                event.setDesName(cursor.getString(cursor.getColumnIndex(EventDataHelper.COLUMN_EVENT_NAME)));
                event.setFromaDate(cursor.getString(cursor.getColumnIndex(EventDataHelper.COLUMN_EVENT_FDATE)));
                event.setToDate(cursor.getString(cursor.getColumnIndex(EventDataHelper.COLUMN_EVENT_TDATE)));
                event.setBudget(cursor.getInt(cursor.getColumnIndex(EventDataHelper.COLUMN_EST_BUDGET)));
                eventArrayList.add(event);
            }while(cursor.moveToNext());
        }
        return eventArrayList;
    }

    public Event getEventById(int id){
        open();
        String selectQuery="select * from "+EventDataHelper.TABLE_EVENT+" where "+EventDataHelper.COLUMN_EVENT_ID+" = "+id;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        Event event=new Event();
        if(cursor.moveToFirst()){

            event.setDesName(cursor.getString(cursor.getColumnIndex(EventDataHelper.COLUMN_EVENT_NAME)));
            event.setFromaDate(cursor.getString(cursor.getColumnIndex(EventDataHelper.COLUMN_EVENT_FDATE)));
            event.setToDate(cursor.getString(cursor.getColumnIndex(EventDataHelper.COLUMN_EVENT_TDATE)));
            event.setBudget(cursor.getInt(cursor.getColumnIndex(EventDataHelper.COLUMN_EST_BUDGET)));
            event.setEventID(id);
        }
        return event;
    }

    private void open() {
        sqLiteDatabase=eventDataHelper.getWritableDatabase();
    }
    private void close(){
        sqLiteDatabase.close();
    }
}

