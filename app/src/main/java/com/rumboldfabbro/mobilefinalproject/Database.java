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

    //TODO populate cmlinks table
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

    public static final String ulist_table_name = "UserList";
    public static final String ulist_column_id = "ID";
    public static final String ulist_column_name = "CollegeID";

    public Database(Context context){
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Colleges "+"(ID integer primary key, Name text, State text, Latitude integer, Longitude integer, Address text, " +
                "Region text, NCAA text, Tuition real, Description text)");
        db.execSQL("create table Majors "+"(ID integer primary key, Name text)");
        db.execSQL("create table cmlinks "+"(ID integer primary key, CollegeID integer, MajorID integer)");
        db.execSQL("create table UserList "+"(ID integer primary key, CollegeID)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS Colleges");
        db.execSQL("DROP TABLE IF EXISTS Majors");
        db.execSQL("DROP TABLE IF EXISTS cmlinks");
        db.execSQL("DROP TABLE IF EXISTS UserList");
        onCreate(db);
    }

    public boolean insertCollege(String name, String state, String address, double latitude, double longitude, String region,
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

    public boolean insertMajor(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", name);

        db.insert("Majors", null, contentValues);
        return true;
    }

    public boolean insertCmlink(int college_id, int major_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("CollegeID", college_id);
        contentValues.put("MajorID", major_id);

        db.insert("cmlinks", null, contentValues);
        return true;
    }

    public boolean insertUlist(int college_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("CollegeID", college_id);

        db.insert("UserList", null, contentValues);
        return true;
    }


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
    /*
    public String getNameByCollege(int college){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Name from Colleges where ID="+college+"", null);
        return res.getString(0);
    }

    public String getAddressByCollege(int college){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Address from Colleges where ID="+college+"", null);
        return res.getString(0);
    }

    public double getLatByCollege(int college){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Latitude from Colleges where ID="+college+"", null);
        return res.getDouble(0);
    }

    public double getLongByCollege(int college){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Longitude from Colleges where ID="+college+"", null);
        return res.getDouble(0);
    }

    public int getCollegeID(String college){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Colleges where Name="+college+"", null);
        return res.getInt(0);
    }

    public String getCollegeByMajor(int major){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = numberOfRows("Colleges");
        String output = "";

        for (int i = 0; i < numRows; i++){
            Cursor res = db.rawQuery("select collegeID from cmlinks where MajorID="+major+" and ID="+i+"", null);
            output += res.getString(0);
        }
        return output;
    }

    public int getMajorID(String major){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Majors where Name="+major+"", null);
        return res.getInt(0);
    }

    public String getMajorName(int major){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Name from Majors where ID="+major+"", null);
        return res.getString(0);
    }

    public String getCollegeBySearch(String search, String attribute){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = numberOfRows("Colleges");
        String output = "";

        for (int i = 0; i < numRows; i++){
            Cursor res = db.rawQuery("select Name from Colleges where "+attribute+"like '%"+search+"%' and ID="+i+"", null);
            output += res.getString(0);
        }
        return output;
    }
    */
    public int numberOfRows(String table){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = 0;
        if (table.equals("Colleges")){
            numRows = (int) DatabaseUtils.queryNumEntries(db, college_table_name);
        } else if (table.equals("Majors")){
            numRows = (int) DatabaseUtils.queryNumEntries(db, majors_table_name);
        } else if (table.equals("cmlinks")){
            numRows = (int) DatabaseUtils.queryNumEntries(db, cmlink_table_name);
        } else if (table.equals("UserList")){
            numRows = (int) DatabaseUtils.queryNumEntries(db, ulist_table_name);
        }
        return numRows;
    }

    public boolean updateCollege(Integer id, String name, String state, String address, int latitude, int longitude,
                                 String region, String NCAA, double tuition, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", name);
        contentValues.put("Address", address);
        contentValues.put("Latitude", latitude);
        contentValues.put("Longitude", longitude);
        contentValues.put("State", state);
        contentValues.put("Region", region);
        contentValues.put("NCAA", NCAA);
        contentValues.put("Tuition", tuition);
        contentValues.put("Description", description);

        db.update("Colleges", contentValues, "id= ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public boolean updateMajor(Integer id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", name);

        db.update("Majors", contentValues, "id= ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public boolean updateCmlink(Integer id, Integer college_id, Integer major_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("CollegeID", college_id);
        contentValues.put("MajorID", major_id);

        db.update("cmlinks", contentValues, "id= ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public boolean updateUlist(Integer id, Integer college_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("CollegeID", college_id);

        db.update("UserList", contentValues, "id= ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public Integer deleteCollege(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Colleges", "id = ? ", new String[] {Integer.toString(id)});
    }

    public Integer deleteMajor(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Majors", "id = ? ", new String[] {Integer.toString(id)});
    }

    public Integer deleteCmlink(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("cmlinks", "id = ? ", new String[] {Integer.toString(id)});
    }

    public Integer deleteUlist(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("UserList", "id = ? ", new String[] {Integer.toString(id)});
    }

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




