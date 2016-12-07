package com.rumboldfabbro.mobilefinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button profile, search, savedlist, about;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);

        db.insertCollege("Delaware College of Art and Design", "DE", "600 N. Market Street", 39.742097, -75.549831, "North", "N/A", 29167, "");
        db.insertCollege("Delaware State University", "DE", "1200 N. DuPont Highway", 39.187834, -75.541644, "North", "Division I: Mid-Eastern", 7532, "");
        db.insertCollege("Goldey-Beacom College", "DE", "4701 Limestone Road", 39.741787, -75.689544, "North", "N/A", 11700, "");
        db.insertCollege("University of Delaware", "DE", "175 The Green", 39.678776, -75.750611, "North", "Division I: Colonial Athletics Association", 11540, "");
        db.insertCollege("Wesley College", "DE", "120 N. State Street", 39.163501, -75.527760, "North", "Division III: Capital Athletics Conference", 25646, "");
        db.insertCollege("Wilmington University", "DE", "320 DuPont Highway", 39.682997, -75.586300, "North", "Division II: Central Athletics Collegiate Conference", 5310, "");

        profile = (Button) findViewById(R.id.menu_profile);
        search = (Button) findViewById(R.id.menu_search);
        savedlist = (Button) findViewById(R.id.menu_savedlist);
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

        savedlist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                activateUserList(v);
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

    public void activateUserList(View v){
        Intent launchUserList = new Intent(this, UserListActivity.class);
        startActivity(launchUserList);
    }

    public void activateAbout(View v){
        Intent launchAbout = new Intent(this, AboutActivity.class);
        startActivity(launchAbout);
    }
}
