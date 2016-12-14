package com.rumboldfabbro.mobilefinalproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.*;

public class MainActivity extends AppCompatActivity {
    private Button profile, search, about;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);

        db.insertCollege("Delaware State University", "DE", "1200 N. DuPont Highway", 39.187834, -75.541644, "North", "Division I: Mid-Eastern", 7532, "");
        db.insertCollege("Goldey-Beacom College", "DE", "4701 Limestone Road", 39.741787, -75.689544, "North", "N/A", 11700, "");
        db.insertCollege("University of Delaware", "DE", "175 The Green", 39.678776, -75.750611, "North", "Division I: Colonial Athletics Association", 11540, "");
        db.insertCollege("Wesley College", "DE", "120 N. State Street", 39.163501, -75.527760, "North", "Division III: Capital Athletics Conference", 25646, "");
        db.insertCollege("Wilmington University", "DE", "320 DuPont Highway", 39.682997, -75.586300, "North", "Division II: Central Athletics Collegiate Conference", 5310, "");

        BufferedReader r = null, ud, wes, dsu, wilm, gb;
        try {
            r = new BufferedReader(new InputStreamReader(getAssets().open("majors.txt")));
            ud = new BufferedReader(new InputStreamReader(getAssets().open("UDMajors.txt")));
            wes = new BufferedReader(new InputStreamReader(getAssets().open("WesleyMajors.txt")));
            dsu = new BufferedReader(new InputStreamReader(getAssets().open("DSUMajors.txt")));
            wilm = new BufferedReader(new InputStreamReader(getAssets().open("WilmUMajors.txt")));
            gb = new BufferedReader(new InputStreamReader(getAssets().open("GoldeyBeacomMajors.txt")));


            String mLine, nLine;
            while ((mLine = r.readLine()) != null){
                db.insertMajor(mLine);
            }

            SQLiteDatabase data = db.getReadableDatabase();
            String[] projection = {"Name"};
            String selection = "ID != ?";
            String[] selectionArgs = {""};
            String sortOrder = "ID";

            Cursor c = data.query("Majors", projection, selection, selectionArgs, null, null, sortOrder);

            c.moveToFirst();

            for (int i = 1; i <= c.getCount(); i++){
                while ((nLine = dsu.readLine()) != null){
                    if (nLine.equals(c.getString(c.getColumnIndex("Name")))){
                        db.insertCmlink(1, i);
                    }
                }
                while((nLine = gb.readLine()) != null){
                    if (nLine.equals(c.getString(c.getColumnIndex("Name")))){
                        db.insertCmlink(2, i);
                    }
                }
                while ((nLine = ud.readLine()) != null){
                    if (nLine.equals(c.getString(c.getColumnIndex("Name")))){
                        db.insertCmlink(3, i);
                    }
                }
                while ((nLine = wes.readLine()) != null){
                    if (nLine.equals(c.getString(c.getColumnIndex("Name")))){
                        db.insertCmlink(4, i);
                    }
                }
                while ((nLine = wilm.readLine()) != null){
                    if (nLine.equals(c.getString(c.getColumnIndex("Name")))){
                        db.insertCmlink(5, i);
                    }
                }
                c.moveToNext();
            }
        } catch (IOException e){
        } finally {
            if (r != null){
                try {
                    r.close();
                } catch (IOException e){}
            }
        }


        profile = (Button) findViewById(R.id.menu_profile);
        search = (Button) findViewById(R.id.menu_search);
        about = (Button) findViewById(R.id.menu_about);

        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                activateProfile(v);
            }
        });

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                activateSearch(v);
            }
        });

        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                activateAbout(v);
            }
        });
    }

    public void activateProfile(View v){
        Intent launchProfile = new Intent(this, ProfileActivity.class);
        startActivity(launchProfile);
    }

    public void activateSearch(View v){
        Intent launchSearch = new Intent(this, SearchActivity.class);
        startActivity(launchSearch);
    }

    public void activateAbout(View v){
        Intent launchAbout = new Intent(this, AboutActivity.class);
        startActivity(launchAbout);
    }
}
