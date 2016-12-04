package com.rumboldfabbro.mobilefinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button profile, search, savedlist, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = (Button) findViewById(R.id.menu_profile);
        search = (Button) findViewById(R.id.menu_search);
        savedlist = (Button) findViewById(R.id.menu_savedlist);
        about = (Button) findViewById(R.id.menu_about);

        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO activate ProfileActivity
            }
        });

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO activate SearchActivity
            }
        });

        savedlist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO activate SavedListActivity
            }
        });

        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO activate AboutActivity
            }
        });
    }
}
