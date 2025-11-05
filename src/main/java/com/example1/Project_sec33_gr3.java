package com.example1;

import java.util.List;

//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231

public class Project_sec33_gr3 {
    private String projectId;
    private String projectName;
    private List<String> collaborators;

    public Project_sec33_gr3(String projectId, String projectName, List<String> collaborators) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.collaborators = collaborators;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public List<String> getCollaborators() {
        return collaborators;
    }
}

