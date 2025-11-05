package com.example1;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;


//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231

public class NetworkVisualizer_sec33_gr3 {

    private final org.jgrapht.Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> jGraphTGraph;
    private final Graph displayGraph;

    public NetworkVisualizer_sec33_gr3(org.jgrapht.Graph<Contributor_sec33_gr3, Collaboration_sec33_gr3> jGraphTGraph) {
        this.jGraphTGraph = jGraphTGraph;
        System.setProperty("org.graphstream.ui", "swing");
        this.displayGraph = new SingleGraph("UAEConnect");
        displayGraph.setStrict(false); // Allow multiple edges
    }

    public void display() {
        buildDisplayGraph();
        applyStyling();
        displayGraph.display();
    }

    private void buildDisplayGraph() {
        // Add nodes
        for (Contributor_sec33_gr3 contributor : jGraphTGraph.vertexSet()) {
            Node node = displayGraph.addNode(contributor.getId());
            node.setAttribute("ui.label", contributor.getName());
            node.setAttribute("contributor", contributor); // Store original object
        }

        // Add edges
        int edgeCount = 0;
        for (Collaboration_sec33_gr3 collaboration : jGraphTGraph.edgeSet()) {
            Contributor_sec33_gr3 source = jGraphTGraph.getEdgeSource(collaboration);
            Contributor_sec33_gr3 target = jGraphTGraph.getEdgeTarget(collaboration);
            displayGraph.addEdge("e" + (edgeCount++), source.getId(), target.getId());
        }
    }

    private void applyStyling() {
        String stylesheet =
            "node {" +
            "   size: 10px, 10px;" +
            "   fill-mode: plain;" +
            "   text-size: 12;" +
            "   text-alignment: under;" +
            "}" +
            "node.individual {" +
            "   fill-color: blue;" +
            "}" +
            "node.ngo {" +
            "   fill-color: green;" +
            "}" +
            "node.school {" +
            "   fill-color: orange;" +
            "}" +
            "node.institution {" +
            "   fill-color: red;" +
            "}";

        displayGraph.setAttribute("ui.stylesheet", stylesheet);

        for (Node node : displayGraph) {
            Contributor_sec33_gr3 contributor = node.getAttribute("contributor", Contributor_sec33_gr3.class);
            int degree = jGraphTGraph.degreeOf(contributor);
            node.setAttribute("ui.size", 10 + (degree * 2)); // Size nodes by degree

            switch (contributor.getType().toLowerCase()) {
                case "individual":
                    node.setAttribute("ui.class", "individual");
                    break;
                case "ngo":
                    node.setAttribute("ui.class", "ngo");
                    break;
                case "school":
                    node.setAttribute("ui.class", "school");
                    break;
                case "institution":
                    node.setAttribute("ui.class", "institution");
                    break;
            }
        }
    }
}
