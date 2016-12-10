package com.rumboldfabbro.mobilefinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by erikarumbold on 12/10/16.
 */

public class MajorResultsActivity extends Activity{
    public Database db = new Database(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegelist);

        Intent intent = getIntent();
        String major = intent.getStringExtra("major");
        String output;

        // iterate through cmlinks
        // if major == major:
        // add college to output string

        output = db.getCollegeByMajor(db.getMajorID(major));
    }

    //TODO populate view with search results

    public void goCreate(View v){
        finish();
    }
}
