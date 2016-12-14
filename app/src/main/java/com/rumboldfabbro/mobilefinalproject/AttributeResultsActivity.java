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

        String output = "";
        ArrayList<String> x = db.getData(attribute, search);

        for (int i = 0; i < x.size(); i++){
            if (!output.contains(x.get(i))) {
                output += (x.get(i) + "\n\n");

            }
        }

        if (!output.equals("")) {
            results.setText(output);
        }
    }

    public void goCreate(View v){
        finish();
    }
}