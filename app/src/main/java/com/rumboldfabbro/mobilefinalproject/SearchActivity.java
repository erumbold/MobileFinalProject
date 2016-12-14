package com.rumboldfabbro.mobilefinalproject;

import android.app.Activity;
import android.content.Intent;
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
    private String searchbar_entry, attribute_entry, major_entry;


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
/*
        searchbar.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){

            }
        });
        */

        attribute_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                launchList1(v);
            }
        });

        major_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                launchList2(v);
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
                attribute_entry = attribute.toString();
                break;
            case R.id.search_major:
                major_entry = major.toString();
                break;
        }
    }

    public void launchList1(View v){
        searchbar_entry = searchbar.getText().toString();
        attribute_entry = attribute.getSelectedItem().toString();
        Intent search = new Intent(this, AttributeResultsActivity.class);
        search.putExtra("search", searchbar_entry);
        search.putExtra("attribute", attribute_entry);
        startActivity(search);
    }

    public void launchList2(View v){
        major_entry = major.getSelectedItem().toString();
        Intent search = new Intent(this, MajorResultsActivity.class);
        search.putExtra("major", major_entry);
        startActivity(search);
    }
}
