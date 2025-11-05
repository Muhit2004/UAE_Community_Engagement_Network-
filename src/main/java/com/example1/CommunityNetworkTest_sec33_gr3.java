package com.example1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231

//automated testing using JUnit library
public class CommunityNetworkTest_sec33_gr3 {

    private GraphManager_sec33_gr3 graphManager;
    private CommunityNetwork_sec33_gr3 network;

    @BeforeEach
    void setUp() {
        graphManager = new JGraphTGraphManager_sec33_gr3();
        network = new CommunityNetwork_sec33_gr3(graphManager);
    }

    @Test
    void testAddContributor() {
        Contributor_sec33_gr3 contributor = new Contributor_sec33_gr3("C101", "Test User", "Individual", "Test Region");
        network.addContributor(contributor);
        assertTrue(graphManager.getGraph().containsVertex(contributor));
    }

    @Test
    void testDuplicateContributor() {
        Contributor_sec33_gr3 contributor = new Contributor_sec33_gr3("C101", "Test User", "Individual", "Test Region");
        network.addContributor(contributor);
        network.addContributor(contributor); // Add the same contributor again
        assertEquals(1, graphManager.getGraph().vertexSet().size());
    }

    @Test
    void testIsolatedContributor() {
        Contributor_sec33_gr3 contributor = new Contributor_sec33_gr3("C101", "Isolated User", "Individual", "Test Region");
        network.addContributor(contributor);
        assertEquals(0, graphManager.getGraph().degreeOf(contributor));
    }

    @Test
    void testInvalidCollaboration() {
        Contributor_sec33_gr3 contributor1 = new Contributor_sec33_gr3("C101", "Test User 1", "Individual", "Test Region");
        Contributor_sec33_gr3 contributor2 = new Contributor_sec33_gr3("C102", "Test User 2", "Individual", "Test Region");
        network.addContributor(contributor1);

        network.createCollaboration(contributor1, contributor2, "P-Invalid");
        assertEquals(0, graphManager.getGraph().edgeSet().size());
    }

    @Test
    void testLargeDatasetLoading() {

        network.loadContributorsFromFile("contributors.json");
        network.loadProjectsFromFile("projects.json");

        assertEquals(100, graphManager.getGraph().vertexSet().size());
        assertTrue(graphManager.getGraph().edgeSet().size() > 0);
    }
}

