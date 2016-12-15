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

    /************************************************************************************************
     * This is called when the Activity is launched. The appropriate listeners are implemented.
     * @param savedInstanceState
     ***********************************************************************************************/
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

    /************************************************************************************************
     * This is called within onCreate(). It populates the Spinners with the attributes of the
     * Colleges table and the elements of the Majors table, respectively.
     ***********************************************************************************************/
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

    /************************************************************************************************
     * This is called when a value is selected in a Spinner.
     * @param parent
     * @param view
     * @param position
     * @param id
     ***********************************************************************************************/
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

    /************************************************************************************************
     * This is called by the corresponding Button listener. The values in the EditText and
     * the Attribute Spinner is stored in an Intent, which is passed to AttributeResultsActivity.java
     * @param v
     ***********************************************************************************************/
    public void launchList1(View v){
        searchbar_entry = searchbar.getText().toString();
        attribute_entry = attribute.getSelectedItem().toString();
        Intent search = new Intent(this, AttributeResultsActivity.class);
        search.putExtra("search", searchbar_entry);
        search.putExtra("attribute", attribute_entry);
        startActivity(search);
    }

    /************************************************************************************************
     * This is called by the corresponding Button listener. The value in the Major Spinner is stored
     * in an Intent, which is passed to MajorResultsActivity.java
     * @param v
     ***********************************************************************************************/
    public void launchList2(View v){
        major_entry = major.getSelectedItem().toString();
        Intent search = new Intent(this, MajorResultsActivity.class);
        search.putExtra("major", major_entry);
        startActivity(search);
    }
}
