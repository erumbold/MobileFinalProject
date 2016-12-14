package com.rumboldfabbro.mobilefinalproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by erikarumbold on 12/3/16.
 */

public class AttributeResultsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegelist);
        TextView results = (TextView) findViewById(R.id.results);

        Database db = new Database(getApplicationContext());

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        String attribute = intent.getStringExtra("attribute");

        SQLiteDatabase data = db.getReadableDatabase();
        String[] projection = {"Name"};
        String selection = attribute + " != ?";
        String[] selectionArgs = {"%"+search+"%"};
        String sortOrder = "ID";

        Cursor c = data.query("Colleges", projection, selection, selectionArgs, null, null, sortOrder);
        c.moveToFirst();

        String output = "";

        while (c.getString(c.getColumnIndex("Name")) != null){
            output += c.getString(c.getColumnIndex("Name"));
            c.moveToNext();
        }

        if (!output.equals("")) {
            results.setText(output);
        }
    }

    public void goCreate(View v){
        finish();
    }
}