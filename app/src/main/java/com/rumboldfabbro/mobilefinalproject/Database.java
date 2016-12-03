package com.erikarumbold.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by erikarumbold on 12/3/16.
 */

public class Database extends SQLiteOpenHelper {
    public static final String db_name = "myDB.db";

    public static final String college_table_name = "colleges";
    public static final String colleges_column_id = "id";
    public static final String colleges_column_name = "name";
    public static final String colleges_column_address = "address";
    public static final String colleges_column_region = "region";
    public static final String colleges_column_rank = "rank";
    public static final String colleges_column_NCAA = "NCAA";
    public static final String colleges_column_tuition = "tuition";

    public static final String cmlink_table_name = "cmlinks";
    public static final String cmlink_column_id = "id";
    public static final String cmlink_column_college = "college_id";
    public static final String cmlink_column_major = "major_id";

    public static final String majors_table_name = "majors";
    public static final String majors_column_id = "id";
    public static final String majors_column_name = "name";

    public static final String ulist_table_name = "user_list";
    public static final String ulist_column_id = "id";
    public static final String ulist_column_name = "college_id";

    public Database(Context context){
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table colleges "+"(id integer primary key, name text, address text, " +
                "region text, rank text, NCAA text, tuition real)");
        db.execSQL("create table majors "+"(id integer primary key, name text)");
        db.execSQL("create table cmlinks "+"(id integer primary key, college_id integer, major_id integer)");
        db.execSQL("create table ulist "+"(id integer primary key, college_id)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS colleges");
        db.execSQL("DROP TABLE IF EXISTS majors");
        db.execSQL("DROP TABLE IF EXISTS cmlinks");
        db.execSQL("DROP TABLE IF EXISTS ulist");
        onCreate(db);
    }

    public boolean insertCollege(String name, String address, String region, String rank,
                                 String NCAA, double tuition){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("address", address);
        contentValues.put("region", region);
        contentValues.put("rank", rank);
        contentValues.put("NCAA", NCAA);
        contentValues.put("tuition", tuition);

        db.insert("colleges", null, contentValues);
        return true;
    }

    public boolean insertMajor(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);

        db.insert("majors", null, contentValues);
        return true;
    }

    public boolean insertCmlink(int college_id, int major_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("college_id", college_id);
        contentValues.put("major_id", major_id);

        db.insert("cmlinks", null, contentValues);
        return true;
    }

    public boolean insertUlist(int college_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("college_id", college_id);

        db.insert("user_list", null, contentValues);
        return true;
    }

    public Cursor getData(String table, int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+table+" where id="+id+"", null);
        return res;
    }

    public int numberOfRows(String table){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = 0;
        if (table.equals("colleges")){
            numRows = (int) DatabaseUtils.queryNumEntries(db, college_table_name);
        } else if (table.equals("majors")){
            numRows = (int) DatabaseUtils.queryNumEntries(db, majors_table_name);
        } else if (table.equals("cmlinks")){
            numRows = (int) DatabaseUtils.queryNumEntries(db, cmlink_table_name);
        } else if (table.equals("user_list")){
            numRows = (int) DatabaseUtils.queryNumEntries(db, ulist_table_name);
        }
        return numRows;
    }

    public boolean updateCollege(Integer id, String name, String address, String region, String rank,
                                 String NCAA, double tuition){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("address", address);
        contentValues.put("region", region);
        contentValues.put("rank", rank);
        contentValues.put("NCAA", NCAA);
        contentValues.put("tuition", tuition);

        db.update("colleges", contentValues, "id= ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public boolean updateMajor(Integer id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);

        db.update("majors", contentValues, "id= ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public boolean updateCmlink(Integer id, Integer college_id, Integer major_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("college_id", college_id);
        contentValues.put("major_id", major_id);

        db.update("cmlinks", contentValues, "id= ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public boolean updateUlist(Integer id, Integer college_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("college_id", college_id);

        db.update("user_list", contentValues, "id= ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public Integer deleteCollege(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("colleges", "id = ? ", new String[] {Integer.toString(id)});
    }

    public Integer deleteMajor(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("majors", "id = ? ", new String[] {Integer.toString(id)});
    }

    public Integer deleteCmlink(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("cmlinks", "id = ? ", new String[] {Integer.toString(id)});
    }

    public Integer deleteUlist(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("user_list", "id = ? ", new String[] {Integer.toString(id)});
    }

    public ArrayList<String> getAll(String table){
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+table+"", null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            if (table.equals("colleges")){
                arrayList.add(res.getString(res.getColumnIndex(colleges_column_name)));
            } else if (table.equals("majors")){
                arrayList.add(res.getString(res.getColumnIndex(majors_column_name)));
            } else if (table.equals("cmlinks")){
                arrayList.add(res.getString(res.getColumnIndex(cmlink_column_college)));
            } else if (table.equals("user_list")){
                arrayList.add(res.getString(res.getColumnIndex(ulist_column_name)));
            }
            res.moveToNext();
        }
        return arrayList;
    }
}




