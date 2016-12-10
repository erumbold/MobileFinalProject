package com.rumboldfabbro.mobilefinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by erikarumbold on 12/3/16.
 */

public class AttributeResultsActivity extends Activity{
    public Database db = new Database(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegelist);
        TextView results = (TextView) findViewById(R.id.results);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        String attribute = intent.getStringExtra("attribute");
        String output = db.getCollegeBySearch(search, attribute);

        if (!output.equals("")) {
            results.setText(output);
        }
    }

    public void goCreate(View v){
        finish();
    }
}