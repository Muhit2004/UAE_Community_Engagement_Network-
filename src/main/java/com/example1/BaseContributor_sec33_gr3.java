package com.example1;

//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231

 // Abstract base class for all contributors in the network

public abstract class BaseContributor_sec33_gr3 {
    protected final String id;
    protected String name;
    protected String region;

    public BaseContributor_sec33_gr3(String id, String name, String region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public abstract String getType();

    public abstract String getDescription();
}

