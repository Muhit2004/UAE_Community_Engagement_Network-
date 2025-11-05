package com.example1;

import org.jgrapht.graph.DefaultEdge;

//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231
public class Collaboration_sec33_gr3 extends DefaultEdge {
    private String projectId;

    public Collaboration_sec33_gr3(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    @Override
    public String toString() {
        return "(" + getSource() + " : " + getTarget() + " : " + projectId + ")";
    }
}

