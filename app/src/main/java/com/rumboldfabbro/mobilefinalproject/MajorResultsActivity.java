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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegelist);
        TextView results = (TextView) findViewById(R.id.results);

        Database db = new Database(getApplicationContext());
        Intent intent = getIntent();
        String major = intent.getStringExtra("major");

        SQLiteDatabase data = db.getReadableDatabase();
        String[] projection = {"ID"};
        String selection = "Name='"+major+"'";
        String sortOrder = "ID";

        Cursor c = data.query("Majors", projection, selection, null, null, null, sortOrder);
        c.moveToFirst();
        int major_id = c.getInt(c.getColumnIndex("ID"));

        projection[0] = "CollegeID";
        selection = "MajorID='"+major_id+"'";
        c = data.query("cmlinks", projection, selection, null, null, null, sortOrder);
        c.moveToFirst();

        String output = "";
        for (int i = 0; i < c.getCount(); i++) {
            int clg = c.getInt(c.getColumnIndex("CollegeID"));
            if (clg == 1 && !output.contains("State")) {
                output += "Delaware State University\n\n";
            } else if (clg == 2 && !output.contains("Beacom")) {
                output += "Goldey-Beacom College\n\n";
            } else if (clg == 3 && !output.contains("University of Delaware")) {
                output += "University of Delaware\n\n";
            } else if (clg == 4 && !output.contains("Wesley")) {
                output += "Wesley College\n\n";
            } else if (clg == 5 && !output.contains("Wilmington")) {
                output += "Wilmington University\n\n";
            }
            c.moveToNext();
        }
        /*
        ArrayList<Integer> x = new ArrayList<>();

        for (int i = 0; i < c.getCount(); i++){
            x.add(c.getInt(c.getColumnIndex("CollegeID")));
            c.moveToNext();
        }

        projection[0] = "Name";
        for (int i = 0; i < x.size(); i++){
            selection = "ID='"+x.get(i)+"'";
            c = data.query("Colleges", projection, selection, null, null, null, sortOrder);
        }

        c.moveToFirst();

        for (int i = 0; i < c.getCount(); i++){
            String o = c.getString(c.getColumnIndex("Name")) + "\n";
            c.moveToNext();
            output += o;
        }
        */
        if (!output.equals("")) {
            results.setText(output);
        }

        c.close();
    }

    public void goCreate(View v){
        finish();
    }
}
