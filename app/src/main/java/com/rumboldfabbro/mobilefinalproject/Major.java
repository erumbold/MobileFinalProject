package com.rumboldfabbro.mobilefinalproject;

/**
 * Created by erikarumbold on 12/3/16.
 */

public class Major {
    private int id;
    private String name;

    public Major(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void setId(int id){this.id = id;}
    public void setName(String name){this.name = name;}

    public int getId(){return this.id;}
    public String getName(){return this.name;}
}
