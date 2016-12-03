package com.erikarumbold.finalproject;

/**
 * Created by erikarumbold on 12/3/16.
 */

public class College {
    private int id;
    private String name;
    private String address;
    private String region;
    private String rank;
    private String NCAA;
    private double tuition;

    public College(){}

    public College(int id, String name, String address, String region, String rank, String NCAA, double tuition){
        this.id = id;
        this.name = name;
        this.address = address;
        this.region = region;
        this.rank = rank;
        this.NCAA = NCAA;
        this.tuition = tuition;
    }

    public void setId(int id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setAddress(String address){this.address = address;}
    public void setRegion(String region){this.region = region;}
    public void setRank(String rank){this.rank = rank;}
    public void setNCAA(String NCAA){this.NCAA = NCAA;}
    public void setTuition(double tuition){this.tuition = tuition;}

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public String getAddress(){return this.address;}
    public String getRegion(){return this.region;}
    public String getRank(){return this.rank;}
    public String getNCAA(){return this.NCAA;}
    public double getTuition(){return this.tuition;}
}
