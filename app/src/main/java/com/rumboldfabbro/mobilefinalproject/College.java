package com.rumboldfabbro.mobilefinalproject;

/**
 * Created by erikarumbold on 12/3/16.
 */

public class College {
    private int id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String region;
    private String NCAA;
    private double tuition;

    public College(){}

    public College(int id, String name, String address, double latitude, double longitude, String region, String NCAA, double tuition){
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.NCAA = NCAA;
        this.tuition = tuition;
    }

    public void setId(int id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setAddress(String address){this.address = address;}
    public void setLatitude(int latitude){this.latitude = latitude;}
    public void setLongitude(int longitude){this.longitude = longitude;}
    public void setRegion(String region){this.region = region;}
    public void setNCAA(String NCAA){this.NCAA = NCAA;}
    public void setTuition(double tuition){this.tuition = tuition;}

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public String getAddress(){return this.address;}
    public double getLatitude(){return this.latitude;}
    public double getLongitude(){return this.longitude;}
    public String getRegion(){return this.region;}
    public String getNCAA(){return this.NCAA;}
    public double getTuition(){return this.tuition;}
}
