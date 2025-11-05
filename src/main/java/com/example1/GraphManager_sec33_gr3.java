package com.example1;

import org.jgrapht.Graph;

//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231

 // Bridge interface for graph management operations.

public interface GraphManager_sec33_gr3 {
    void addContributor(Contributor_sec33_gr3 contributor);
    void createCollaboration(Contributor_sec33_gr3 c1, Contributor_sec33_gr3 c2, String projectId);
    void removeContributor(String contributorId);
    void removeCollaboration(String contributorId1, String contributorId2, String projectId);
    Contributor_sec33_gr3 getContributorById(String id);
    Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> getGraph();
}