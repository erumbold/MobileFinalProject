package com.rumboldfabbro.mobilefinalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by erikarumbold on 12/3/16.
 */

public class SearchActivity extends Activity{
    private EditText searchbar;
    private Spinner attribute, major;
    private Button attribute_submit, major_submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegesearch);

        searchbar = (EditText) findViewById(R.id.search_bar);
        attribute = (Spinner) findViewById(R.id.search_attribute);
        major = (Spinner) findViewById(R.id.search_major);
        attribute_submit = (Button) findViewById(R.id.search_submit1);
        major_submit = (Button) findViewById(R.id.search_submit2);

        loadSpinnerData();

        searchbar.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                //TODO
            }
        });

        attribute_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO
            }
        });

        major_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO
            }
        });
    }

    public void loadSpinnerData() {
        Database db = new Database(getApplicationContext());

        ArrayList<String> attributes = db.listAttributes();
        ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, attributes);

        ArrayList<String> majors = db.listMajors();
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, majors);

        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        major.setAdapter(mAdapter);
        attribute.setAdapter(aAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        switch (parent.getId()){
            case R.id.search_attribute:
                //TODO
            case R.id.search_major:
                //TODO
        }
    }
}
