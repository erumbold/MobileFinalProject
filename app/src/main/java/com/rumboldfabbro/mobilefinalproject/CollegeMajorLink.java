package com.rumboldfabbro.mobilefinalproject;

/**
 * Created by erikarumbold on 12/3/16.
 */

public class CollegeMajorLink {
    private int id;
    private int college_id;
    private int major_id;

    public CollegeMajorLink(int id, int college_id, int major_id){
        this.id = id;
        this.college_id = college_id;
        this.major_id = major_id;
    }

    public void setId(int id){this.id = id;}
    public void setCollege_id(int id){this.college_id = id;}
    public void setMajor_id(int id){this.major_id = id;}

    public int getId(){return this.id;}
    public int getCollege_id(){return this.college_id;}
    public int getMajor_id(){return this.major_id;}
}
