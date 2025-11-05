package com.example1;

import org.jgrapht.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.jgrapht.alg.scoring.EigenvectorCentrality;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import java.util.Set;


//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231

//stratagy implementation with 5 extra features

//performs ranking
public class DegreeCentralityStrategy_sec33_gr3 implements AnalysisStrategy_sec33_gr3 {

    @Override
    public void analyze(Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> graph) {
        List<Contributor_sec33_gr3> contributors = new ArrayList<>(graph.vertexSet());
        contributors.sort(Comparator.comparingInt(graph::degreeOf).reversed());

        System.out.println("\n=== ENGAGEMENT RANKINGS (Degree Centrality) ===");
        for (int i = 0; i < contributors.size(); i++) {
            Contributor_sec33_gr3 c = contributors.get(i);
            System.out.printf("%d. %s (%s) - %d collaborations%n",
                    i + 1, c.getName(), c.getType(), graph.degreeOf(c));
        }
    }
}

//performs analysis on betweenness and eigenvector centrality
//How often someone sits on the shortest path between others
//if you remove the highest betweenness, the graph will be disconnected
//How well-connected your connections are with high value
class InfluenceAnalysisStrategy_sec33_gr3 implements AnalysisStrategy_sec33_gr3 {



    @Override
    public void analyze(Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> graph) {
        BetweennessCentrality<Contributor_sec33_gr3, Collaboration_sec33_gr3> bc =
                new BetweennessCentrality<>(graph);
        EigenvectorCentrality<Contributor_sec33_gr3, Collaboration_sec33_gr3> ec =
                new EigenvectorCentrality<>(graph);

        List<Contributor_sec33_gr3> contributors = new ArrayList<>(graph.vertexSet());
        contributors.sort((a, b) -> Double.compare(bc.getVertexScore(b), bc.getVertexScore(a)));

        System.out.println("\n--- Top Bridge Contributors (High Betweenness) ---");
        for (int i = 0; i < contributors.size(); i++) {
            Contributor_sec33_gr3 c = contributors.get(i);
            System.out.printf("%d. %s (%s) - Score: %.3f%n",
                    i + 1, c.getName(), c.getType(), bc.getVertexScore(c));
        }

        contributors.sort((a, b) -> Double.compare(ec.getVertexScore(b), ec.getVertexScore(a)));
        System.out.println("\n--- Top Influencers (Eigenvector Centrality) ---");
        for (int i = 0; i < contributors.size(); i++) {
            Contributor_sec33_gr3 c = contributors.get(i);
            System.out.printf("%d. %s (%s) - Influence: %.3f%n",
                    i + 1, c.getName(), c.getType(), ec.getVertexScore(c));
        }    }
}

//performs analysis on connectedness
class ClusterDetectionStrategy_sec33_gr3 implements AnalysisStrategy_sec33_gr3 {

    @Override
    public void analyze(Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> graph){
        ConnectivityInspector<Contributor_sec33_gr3, Collaboration_sec33_gr3> inspector =
                new ConnectivityInspector<>(graph);

        List<Set<Contributor_sec33_gr3>> clusters = inspector.connectedSets();

        System.out.println("\n=== COMMUNITY CLUSTER DETECTION ===");
        int clusterNumber = 1;
        for (Set<Contributor_sec33_gr3> cluster : clusters) {
            System.out.printf("Cluster %d (%d members):%n", clusterNumber++, cluster.size());
            for (Contributor_sec33_gr3 c : cluster) {
                System.out.println(" - " + c.getName() + " (" + c.getType() + ")");
            }
            System.out.println();
        }
    }
}

//performs analysis on shortest path it takes from 1st to last contributor
class PathfindingStrategy_sec33_gr3 implements AnalysisStrategy_sec33_gr3 {

    @Override
    public void analyze(Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> graph){

            System.out.println("\n=== PATHFINDING ANALYSIS (Shortest Collaboration Route) === ");

            List<Contributor_sec33_gr3> nodes = new ArrayList<>(graph.vertexSet());
            if (nodes.size() < 2) {
                System.out.println("Not enough contributors to analyze paths.");
                return;
            }

            Contributor_sec33_gr3 source = nodes.get(0);
            Contributor_sec33_gr3 target = nodes.get(nodes.size() - 1);

            DijkstraShortestPath<Contributor_sec33_gr3, Collaboration_sec33_gr3> dijkstra =
                    new DijkstraShortestPath<>(graph);

            var path = dijkstra.getPath(source, target);

            if (path != null) {
                System.out.printf("Shortest path between %s and %s:%n", source.getName(), target.getName());
                System.out.println(path.getVertexList());
            } else {
                System.out.printf("No path found between %s and %s.%n", source.getName(), target.getName());
            }
    }
}

//performs analysis on connectivity
//find flaws and how many flaws there are
class ConnectivityAnalysisStrategy_sec33_gr3 implements AnalysisStrategy_sec33_gr3 {

    @Override
    public void analyze(Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> graph){
        System.out.println("\n=== CONNECTIVITY ANALYSIS ===");

        ConnectivityInspector<Contributor_sec33_gr3, Collaboration_sec33_gr3> inspector =
                new ConnectivityInspector<>(graph);

        if (inspector.isConnected()) {
            System.out.println("✅ The network is fully connected.");
        } else {
            System.out.println("⚠️ The network has separated groups / isolated contributors.");

            List<Set<Contributor_sec33_gr3>> groups = inspector.connectedSets();
            System.out.println("Number of separate collaboration groups: " + groups.size());
        }

        System.out.println("\n--- Isolated Contributors (No Collaborations) ---");
        for (Contributor_sec33_gr3 c : graph.vertexSet()) {
            if (graph.degreeOf(c) == 0) {
                System.out.println(" - " + c.getName() + " (" + c.getType() + ")");
            }
        }
}}

//performs analysis on collaboration opportunities
//finds suggestions for collaboration
class EngagementImprovementStrategy_sec33_gr3 implements AnalysisStrategy_sec33_gr3 {

    @Override
    public void analyze(Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> graph) {
        System.out.println("\n=== ENGAGEMENT IMPROVEMENT SUGGESTIONS ===");

        // Identify potential connectors
        List<Contributor_sec33_gr3> nodes = new ArrayList<>(graph.vertexSet());
        nodes.sort(Comparator.comparingInt(graph::degreeOf).reversed());

        System.out.println("\n--- Suggested Collaboration Opportunities ---");
        for (int i = 0; i < nodes.size(); i++) {
            Contributor_sec33_gr3 contributor = nodes.get(i);




            // Find someone they are NOT connected to yet
            for (Contributor_sec33_gr3 target : nodes) {
                if (contributor != target && graph.getEdge(contributor, target) == null) {
                    System.out.printf("%s (%s) could collaborate with %s (%s)%n",
                            contributor.getName(), contributor.getType(),
                            target.getName(), target.getType());
                    break;
                }
            }
        }
    }
}



