package com.example1;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;

//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231


  // implementation of the GraphManager bridge using JGraphT.

public class JGraphTGraphManager_sec33_gr3 implements GraphManager_sec33_gr3 {

    private final Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> network;

    public JGraphTGraphManager_sec33_gr3() {
        this.network = new Pseudograph<>(Collaboration_sec33_gr3.class);
    }

    @Override
    public void addContributor(Contributor_sec33_gr3 contributor) {
        if (contributor != null && !network.containsVertex(contributor)) {
            network.addVertex(contributor);
        }
    }

    @Override
    public void createCollaboration(Contributor_sec33_gr3 c1, Contributor_sec33_gr3 c2, String projectId) {
        if (c1 != null && c2 != null && network.containsVertex(c1) && network.containsVertex(c2)) {
            network.addEdge(c1, c2, new Collaboration_sec33_gr3(projectId));
        }
    }

    @Override
    public void removeContributor(String contributorId) {
        Contributor_sec33_gr3 contributor = getContributorById(contributorId);
        if (contributor != null) {
            network.removeVertex(contributor);
        }
    }

    @Override
    public void removeCollaboration(String contributorId1, String contributorId2, String projectId) {
        Contributor_sec33_gr3 c1 = getContributorById(contributorId1);
        Contributor_sec33_gr3 c2 = getContributorById(contributorId2);
        if (c1 != null && c2 != null) {
            Collaboration_sec33_gr3 toRemove = null;
            for (Collaboration_sec33_gr3 edge : network.getAllEdges(c1, c2)) {
                if (edge.getProjectId().equals(projectId)) {
                    toRemove = edge;
                    break;
                }
            }
            if (toRemove != null) {
                network.removeEdge(toRemove);
            }
        }
    }



//uses the id to find the contributor
    //uses hash and equals to compare the id , check contributor class
    @Override
    public Contributor_sec33_gr3 getContributorById(String id) {
        for (Contributor_sec33_gr3 c : network.vertexSet()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> getGraph() {
        return network;
    }
}