package com.example1;

import java.util.Objects;

//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231

public class Contributor_sec33_gr3 extends BaseContributor_sec33_gr3 {
    private String type;

    public Contributor_sec33_gr3(String id, String name, String type, String region) {
        super(id, name, region);
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return "Contributor of type " + type + " from " + region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contributor_sec33_gr3 that = (Contributor_sec33_gr3) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
