package com.rumboldfabbro.mobilefinalproject;

import android.app.Activity;
import android.content.SharedPreferences;
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

public class ProfileActivity extends Activity {
    private EditText name, school;
    private Spinner major;
    private SharedPreferences.Editor myEd;

    /************************************************************************************************
     * This function is called when the Activity is launched. It implements the appropriate
     * listeners. Values entered in the EditText fields are stored in SharedPreferences (kinda).
     * @param savedInstanceState
     ***********************************************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (EditText) findViewById(R.id.profile_name);
        school = (EditText) findViewById(R.id.profile_school);
        major = (Spinner) findViewById(R.id.profile_major);
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);


        loadSpinnerData();

        myEd = userPref.edit();

        name.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                myEd.putString("name", name.getText().toString());
                myEd.commit();
            }
        });

        school.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                myEd.putString("school", school.getText().toString());
                myEd.commit();
            }
        });
    }

    /************************************************************************************************
     * This function is called in the onCreate(). It fills the Spinner with the elements in
     * the Majors table.
     ***********************************************************************************************/
    public void loadSpinnerData() {
        Database db = new Database(getApplicationContext());
        ArrayList<String> majors = db.listMajors();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, majors);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        major.setAdapter(adapter);
    }

    /************************************************************************************************
     * This is called when an item in the Spinner is selected.
     * It stores the value in SharedPreferences (kinda).
     * @param parent
     * @param view
     * @param position
     * @param id
     ***********************************************************************************************/
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String major = parent.getItemAtPosition(position).toString();
        myEd.putString("major", major);
        myEd.commit();

    }
}
