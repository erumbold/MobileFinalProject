package com.rumboldfabbro.mobilefinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by erikarumbold on 12/3/16.
 */

public class Database extends SQLiteOpenHelper {

    public static final String db_name = "myDB.db";

    public static final String college_table_name = "Colleges";
    public static final String colleges_column_id = "ID";
    public static final String colleges_column_name = "Name";
    public static final String colleges_column_state = "State";
    public static final String colleges_column_address = "Address";
    public static final String colleges_column_latitude = "Latitude";
    public static final String colleges_column_longitude = "Longitude";
    public static final String colleges_column_region = "Region";
    public static final String colleges_column_rank = "Rank";
    public static final String colleges_column_NCAA = "NCAA";
    public static final String colleges_column_tuition = "Tuition";
    public static final String colleges_column_description = "Description";

    public static final String cmlink_table_name = "cmlinks";
    public static final String cmlink_column_id = "ID";
    public static final String cmlink_column_college = "CollegeID";
    public static final String cmlink_column_major = "MajorID";

    public static final String majors_table_name = "Majors";
    public static final String majors_column_id = "ID";
    public static final String majors_column_name = "Name";

    public Database(Context context){
        super(context, db_name, null, 1);
    }

    /************************************************************************************************
     * This is called when an instance of Database is created. It creates the tables in the database.
     * @param db
     ***********************************************************************************************/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Colleges "+"(ID integer primary key, Name text, State text, Latitude integer, Longitude integer, Address text, " +
                "Region text, NCAA text, Tuition real, Description text)");
        db.execSQL("create table Majors "+"(ID integer primary key, Name text)");
        db.execSQL("create table cmlinks "+"(ID integer primary key, CollegeID integer, MajorID integer)");
    }

    /************************************************************************************************
     * This function wipes the database of its tables.
     * @param db
     * @param oldV
     * @param newV
     ***********************************************************************************************/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS Colleges");
        db.execSQL("DROP TABLE IF EXISTS Majors");
        db.execSQL("DROP TABLE IF EXISTS cmlinks");
        onCreate(db);
    }

    /************************************************************************************************
     * This function adds a college to the Colleges table using the parameters to populate
     * the attributes for the college.
     * @param name
     * @param state
     * @param address
     * @param latitude
     * @param longitude
     * @param region
     * @param NCAA
     * @param tuition
     * @param description
     * @return
     ***********************************************************************************************/
    public boolean insertCollege(String name, String state, String address,
                                 double latitude, double longitude, String region,
                                 String NCAA, double tuition, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", name);
        contentValues.put("State", state);
        contentValues.put("Address", address);
        contentValues.put("Latitude", latitude);
        contentValues.put("Longitude", longitude);
        contentValues.put("Region", region);
        contentValues.put("NCAA", NCAA);
        contentValues.put("Tuition", tuition);
        contentValues.put("Description", description);

        db.insert("Colleges", null, contentValues);
        return true;
    }

    /************************************************************************************************
     * This function adds a major to the Majors table.
     * @param name
     * @return
     ***********************************************************************************************/
    public boolean insertMajor(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", name);

        db.insert("Majors", null, contentValues);
        return true;
    }

    /************************************************************************************************
     * This function creates a link between a college and major using their respective IDs.
     * @param college_id
     * @param major_id
     * @return
     ***********************************************************************************************/
    public boolean insertCmlink(int college_id, int major_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("CollegeID", college_id);
        contentValues.put("MajorID", major_id);

        db.insert("cmlinks", null, contentValues);
        return true;
    }

    /************************************************************************************************
     * This function searches through the Colleges table given a certain attribute and search term.
     * @param attribute
     * @param search
     * @return an ArrayList consisting of the college names that satisfies the search query.
     ***********************************************************************************************/
    public ArrayList<String> getData(String attribute, String search){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Name from Colleges where "+attribute+" like '%"+search+"%'", null);
        res.moveToFirst();
        ArrayList<String> x = new ArrayList<>();
        if (!res.getString(res.getColumnIndex("Name")).isEmpty()) {
            while (!res.isLast()) {
                String c = res.getString(res.getColumnIndex("Name"));
                if (c.contains(search)) {
                    x.add(c);
                }
                res.moveToNext();
            }
            x.add(res.getString(res.getColumnIndex("Name")));
        }
        return x;
    }

    /************************************************************************************************
     * This creates an ArrayList consisting every major name in the Majors table
     * @return ArrayList arrayList
     ***********************************************************************************************/
    public ArrayList<String> listMajors(){
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Majors", null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            arrayList.add(res.getString(res.getColumnIndex(majors_column_name)));
            res.moveToNext();
        }
        return arrayList;
    }

    /************************************************************************************************
     * This creates an ArrayList consisting of every attribute in the Colleges table
     * @return ArrayList arrayList
     ***********************************************************************************************/
    public ArrayList<String> listAttributes(){
        ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.add(colleges_column_name);
        arrayList.add(colleges_column_address);
        arrayList.add(colleges_column_state);
        arrayList.add(colleges_column_region);
        arrayList.add(colleges_column_rank);
        arrayList.add(colleges_column_NCAA);
        arrayList.add(colleges_column_tuition);

        return arrayList;
    }
}




