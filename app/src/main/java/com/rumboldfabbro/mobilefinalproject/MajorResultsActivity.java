package com.rumboldfabbro.mobilefinalproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by erikarumbold on 12/10/16.
 */

public class MajorResultsActivity extends Activity{
    public Database db = new Database(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegelist);
        TextView results = (TextView) findViewById(R.id.results);

        Intent intent = getIntent();
        String major = intent.getStringExtra("major");

        SQLiteDatabase data = db.getReadableDatabase();
        String[] projection = {"MajorID"};
        String selection = "Name != ?";
        String[] selectionArgs = {major};
        String sortOrder = "ID";

        Cursor c = data.query("Majors", projection, selection, selectionArgs, null, null, sortOrder);
        c.moveToFirst();
        while (!c.getString(c.getColumnIndex("Name")).equals(major)){
            c.moveToNext();
        }
        int major_id = c.getInt(c.getColumnIndex("ID"));

        projection[0] = "CollegeID";
        selection = "MajorID != ?";
        selectionArgs[0] = major_id+"";
        c = data.query("cmlinks", projection, selection, selectionArgs, null, null, sortOrder);
        c.moveToFirst();

        ArrayList<Integer> x = new ArrayList<>();

        while (!c.isAfterLast()){
            x.add(c.getInt(c.getColumnIndex("ID")));
            c.moveToNext();
        }

        String[] args = new String[x.size()];
        for (int i = 0; i < x.size(); i++){
            args[i] = x.get(i)+"";
        }

        projection[0] = "Name";
        selection = "ID != ?";
        c = data.query("Colleges", projection, selection, args, null, null, sortOrder);

        String output = "";

        while (!c.isAfterLast()){
            output += c.getString(c.getColumnIndex("Name"));
        }

        if (!output.equals("")) {
            results.setText(output);
        }
    }

    public void goCreate(View v){
        finish();
    }
}
