package com.example1;

//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231

/*4

*/


public class UAEConnectApp_sec33_gr3 {

    private static GraphManager_sec33_gr3 graphManager = new JGraphTGraphManager_sec33_gr3();

    private static CommunityNetwork_sec33_gr3 network = new CommunityNetwork_sec33_gr3(graphManager);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  UAE COMMUNITY ENGAGEMENT NETWORK");
        System.out.println("         (UAEConnect System)");
        System.out.println("======================================\n");


        // Load data from JSON files
        System.out.println("Loading data from JSON files...");
        network.loadContributorsFromFile("contributors.json");
        network.loadProjectsFromFile("projects.json");
        System.out.println("✓ Data loaded successfully!\n");

       network.startDynamicMonitoring();

    }
}


/*
# Library Usage  — UAEConnect Project -

This summarizes the external libraries used in the project and explains the purpose of key classes and methods.

---------------------------------------------------------------------

## 1. JGraphT (`org.jgrapht`)
Used as the main graph-handling library.

| Class / Method | Location in Project | Description |
|---|---|---|
| `Graph` | Core graph classes and all strategies | Represents the network of contributors and collaborations. |
| `new Pseudograph(...)` | `JGraphTGraphManager_sec33_gr3` | Creates a graph that allows multiple edges between the same two nodes (used to represent different projects) and self-loops when needed. |
| `graph.addVertex(...)` | `JGraphTGraphManager_sec33_gr3` | Adds a contributor to the network. |
| `graph.addEdge(...)` | `JGraphTGraphManager_sec33_gr3` | Creates a collaboration link between two contributors. |
| `graph.removeVertex(...)` | `JGraphTGraphManager_sec33_gr3` | Removes a contributor and all related collaborations. |
| `graph.removeEdge(...)` | `JGraphTGraphManager_sec33_gr3` | Removes a specific collaboration link. |
| `graph.containsVertex(...)` | `JGraphTGraphManager_sec33_gr3` | Used to avoid adding duplicate contributor entries. |
| `graph.degreeOf(...)` | Centrality strategies & visualizer | Returns how many collaborations a contributor has. Used to determine engagement level. |
| `graph.vertexSet()` / `graph.edgeSet()` | Throughout the project | Used to iterate over contributors and collaborations. |
| `graph.getAllEdges(...)` | `JGraphTGraphManager_sec33_gr3` | Retrieves all collaboration records between a pair of contributors. |
| `BetweennessCentrality` | `InfluenceAnalysisStrategy_sec33_gr3` | Identifies contributors who act as bridges between groups. |
| `EigenvectorCentrality` | `InfluenceAnalysisStrategy_sec33_gr3` | Identifies high-level influencers based on network connections. |
| `ConnectivityInspector` | `ClusterDetectionStrategy_sec33_gr3`, `ConnectivityAnalysisStrategy_sec33_gr3` | Detects separate collaboration clusters and isolated groups. |
| `DijkstraShortestPath` | `PathfindingStrategy_sec33_gr3` | Finds the shortest collaboration path between two contributors. |

---------------------------------------------------------------------

## 2. Google Gson (`com.google.code.gson`)
Used for loading and saving project and contributor data.

| Class / Method | Location | Description |
|---|---|---|
| `new Gson()` | `CommunityNetwork_sec33_gr3` | Main Gson instance used for JSON serialization/deserialization. |
| `gson.fromJson(...)` | `CommunityNetwork_sec33_gr3` | Converts JSON data into Java objects. |
| `new TypeToken<...>(){}` | `CommunityNetwork_sec33_gr3` | Handles conversion of JSON arrays into `List<...>` objects. |

---------------------------------------------------------------------

## 3. JUnit 5 (`org.junit.jupiter`)
Used for automated testing.

| Class / Method | Location | Description |
|---|---|---|
| `@Test` | `CommunityNetworkTest_sec33_gr3` | Marks a test method. |
| `@BeforeEach` | `CommunityNetworkTest_sec33_gr3` | Runs setup code before each test. |
| `Assertions.assertTrue(...)` / `assertEquals(...)` | `CommunityNetworkTest_sec33_gr3` | Used to verify expected outcomes during testing. |

---------------------------------------------------------------------

## 4. GraphStream (`org.graphstream`)
Used to build the interactive visual graph.

| Class / Method | Location | Description |
|---|---|---|
| `Graph` | `NetworkVisualizer_sec33_gr3` | Visual graph representation. |
| `new SingleGraph(...)` | `NetworkVisualizer_sec33_gr3` | Creates the visual graph instance. |
| `graph.setStrict(false)` | `NetworkVisualizer_sec33_gr3` | Allows multiple edges between the same nodes (matches Pseudograph behavior). |
| `graph.addNode(...)` | `NetworkVisualizer_sec33_gr3` | Adds a contributor to the visual graph. |
| `graph.addEdge(...)` | `NetworkVisualizer_sec33_gr3` | Draws collaboration lines between contributors. |
| `node.setAttribute(...)` | `NetworkVisualizer_sec33_gr3` | Used to control node labels, size, and visual class styling. |
| `graph.setAttribute(...)` | `NetworkVisualizer_sec33_gr3` | Applies stylesheet for colors, layout, and visual rules. |
| `graph.display()` | `NetworkVisualizer_sec33_gr3` | Opens the interactive network visualization window. |
| `System.setProperty(...)` | `NetworkVisualizer_sec33_gr3` | Configures GraphStream to use the Swing viewer. |

---------------------------------------------------------------------

 */
