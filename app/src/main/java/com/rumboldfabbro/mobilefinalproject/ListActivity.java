package com.rumboldfabbro.mobilefinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by erikarumbold on 12/3/16.
 */

public class ListActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegelist);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        String attribute = intent.getStringExtra("attribute");
    }

    //TODO populate view with search results

    public void goCreate(View v){
        finish();
    }
}
