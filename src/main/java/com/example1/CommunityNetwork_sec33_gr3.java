package com.example1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Mustabir islam 1096561
//Tarek firas 1090479
//Fakhri Mohammed 1093231

 // Main class for managing the UAE Community Engagement Network using Bridge and Strategy patterns.


//i used the graphStream library to visualize the network
//i used bridge stratagy design pattarn to hide the implementation
//all of them are available at jGrapht class which implements from GraphManager interface
public class CommunityNetwork_sec33_gr3 {

    private final GraphManager_sec33_gr3 graphManager;
    private final List<Project_sec33_gr3> projects;
    private AnalysisStrategy_sec33_gr3 analysisStrategy;
    private  InfluenceAnalysisStrategy_sec33_gr3 influenceAnalysisStrategy ;
    private ClusterDetectionStrategy_sec33_gr3 communityAnalysisStrategy;
    private PathfindingStrategy_sec33_gr3 pathAnalysisStrategy;
    private ConnectivityAnalysisStrategy_sec33_gr3 connectivityAnalysisStrategy;
    private EngagementImprovementStrategy_sec33_gr3 engagementImprovementStrategy;
    public CommunityNetwork_sec33_gr3(GraphManager_sec33_gr3 graphManager) {
        this.graphManager = graphManager;
        this.projects = new ArrayList<>();

        this.analysisStrategy = new DegreeCentralityStrategy_sec33_gr3();
        this.influenceAnalysisStrategy=  new InfluenceAnalysisStrategy_sec33_gr3();
        this.communityAnalysisStrategy = new ClusterDetectionStrategy_sec33_gr3();
        this.pathAnalysisStrategy = new PathfindingStrategy_sec33_gr3();
        this.connectivityAnalysisStrategy = new ConnectivityAnalysisStrategy_sec33_gr3();
         this.engagementImprovementStrategy = new EngagementImprovementStrategy_sec33_gr3();
    }


    // * Load contributors from JSON file

    public void loadContributorsFromFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type contributorListType = new TypeToken<ArrayList<Contributor_sec33_gr3>>(){}.getType();
            List<Contributor_sec33_gr3> contributors = new Gson().fromJson(reader, contributorListType);
            for (Contributor_sec33_gr3 contributor : contributors) {
                addContributor(contributor);
            }
        } catch (Exception e) {
            System.err.println("Error loading contributors: " + e.getMessage());
        }
    }


     // Load projects from JSON file and create collaborations

    public void loadProjectsFromFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type projectListType = new TypeToken<ArrayList<Project_sec33_gr3>>(){}.getType();
            this.projects.addAll(new Gson().fromJson(reader, projectListType));
            for (Project_sec33_gr3 project : this.projects) {
                for (int i = 0; i < project.getCollaborators().size() - 1; i++) {
                    for (int j = i + 1; j < project.getCollaborators().size(); j++) {
                        Contributor_sec33_gr3 c1 = getContributorById(project.getCollaborators().get(i));
                        Contributor_sec33_gr3 c2 = getContributorById(project.getCollaborators().get(j));
                        if (c1 != null && c2 != null) {
                            createCollaboration(c1, c2, project.getProjectId());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading projects: " + e.getMessage());
        }
    }

    public void addContributor(Contributor_sec33_gr3 contributor) {
        graphManager.addContributor(contributor);
    }

    public void createCollaboration(Contributor_sec33_gr3 c1, Contributor_sec33_gr3 c2, String projectId) {
        graphManager.createCollaboration(c1, c2, projectId);
    }

    public void updateContributor(String contributorId, String newName, String newType, String newRegion) {
        Contributor_sec33_gr3 contributor = getContributorById(contributorId);
        if (contributor != null) {
            contributor.setName(newName);
            contributor.setType(newType);
            contributor.setRegion(newRegion);
        }
    }

    public void removeContributor(String contributorId) {
        graphManager.removeContributor(contributorId);
    }

    public void removeCollaboration(String contributorId1, String contributorId2, String projectId) {
        graphManager.removeCollaboration(contributorId1, contributorId2, projectId);
    }

    public void performRankingAnalysis() {
        if (analysisStrategy != null) {
            analysisStrategy.analyze(graphManager.getGraph());
        } else {
            System.out.println("No analysis strategy set.");
        }
    }

    public void performInfluenceAnalysis() {
        if (influenceAnalysisStrategy != null) {
            influenceAnalysisStrategy.analyze(graphManager.getGraph());
        } else {
            System.out.println("No influence analysis strategy set.");
        }
    }

    public void performCommunityAnalysis() {
        if (communityAnalysisStrategy != null) {
            communityAnalysisStrategy.analyze(graphManager.getGraph());
        } else {
            System.out.println("No community analysis strategy set.");
        }
    }
    public void performPathAnalysis() {
        if (pathAnalysisStrategy != null) {
            pathAnalysisStrategy.analyze(graphManager.getGraph());
        } else {
            System.out.println("No path analysis strategy set.");
        }
    }
    public void performConnectivityAnalysis() {
        if (connectivityAnalysisStrategy != null) {
            connectivityAnalysisStrategy.analyze(graphManager.getGraph());
        } else {
            System.out.println("No connectivity analysis strategy set.");
        }
    }
    public void performEngagementImprovementAnalysis() {
        if (engagementImprovementStrategy != null) {
            engagementImprovementStrategy.analyze(graphManager.getGraph());
        } else {
            System.out.println("No engagement improvement analysis strategy set.");
        }
    }

    public void performAllAnalysis() {


        performRankingAnalysis();
        performInfluenceAnalysis();
        performCommunityAnalysis();
        performPathAnalysis();
        performConnectivityAnalysis();
        performEngagementImprovementAnalysis();

    }



     // Dynamic monitoring with console input

    public void startDynamicMonitoring() {
        Scanner scanner = new Scanner(System.in);
        String command;


        while (true) {

            System.out.println("\n=== DYNAMIC NETWORK MONITORING ===");

            System.out.println("Available commands:");
            System.out.println("add - Add a new contributor");
            System.out.println("collab - Add a new collaboration");
            System.out.println("update - Update a contributor");
            System.out.println("remove - Remove a contributor");
            System.out.println("delcollab - Remove a collaboration");
            System.out.println("rank - Perform ranking analysis");
            System.out.println("influence - Perform influence analysis");
            System.out.println("community - Perform community analysis");
            System.out.println("path - Perform path analysis");
            System.out.println("connectivity - Perform connectivity analysis");
            System.out.println("improve - Perform engagement improvement analysis");
            System.out.println("all - Perform all analysis");
            System.out.println("display - Display network");
            System.out.println("visualize - Visualize network graphically");
            System.out.println("exit - Exit monitoring");
            System.out.print("\nEnter command: ");
            command = scanner.nextLine().trim().toLowerCase();

            if ("exit".equals(command)) {
                System.out.println("Exiting monitoring...");
                break;
            }

            try {
                switch (command) {
                    case "add":
                        handleAddContributor(scanner);
                        break;
                    case "collab":
                        handleAddCollaboration(scanner);
                        break;
                    case "update":
                        handleUpdateContributor(scanner);
                        break;
                    case "remove":
                        handleRemoveContributor(scanner);
                        break;
                    case "delcollab":
                        handleRemoveCollaboration(scanner);
                        break;
                    case "rank":
                        performRankingAnalysis();
                        break;
                    case "community":
                        performCommunityAnalysis();
                        break;
                    case "influence":
                        performInfluenceAnalysis();
                        break;
                    case "path":
                        performPathAnalysis();
                        break;
                    case "connectivity":
                        performConnectivityAnalysis();
                        break;
                    case "improve":
                        performEngagementImprovementAnalysis();
                        break;
                     case "all":
                         performAllAnalysis();
                        break;
                    case "display":
                        displayNetwork();
                        break;
                    case "visualize":
                        visualizeNetwork();
                        break;
                    default:
                        System.out.println("Unknown command. Try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void handleAddContributor(Scanner scanner) {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter type (Individual/NGO/School/Institution): ");
        String type = scanner.nextLine();
        System.out.print("Enter region: ");
        String region = scanner.nextLine();
        addContributor(new Contributor_sec33_gr3(id, name, type, region));
        System.out.println("Contributor added successfully!");
    }

    private void handleAddCollaboration(Scanner scanner) {
        System.out.print("Enter first contributor ID: ");
        String id1 = scanner.nextLine();
        System.out.print("Enter second contributor ID: ");
        String id2 = scanner.nextLine();
        System.out.print("Enter project ID: ");
        String projectId = scanner.nextLine();
        Contributor_sec33_gr3 c1 = getContributorById(id1);
        Contributor_sec33_gr3 c2 = getContributorById(id2);
        createCollaboration(c1, c2, projectId);
        System.out.println("Collaboration created successfully!");
    }

    private void handleUpdateContributor(Scanner scanner) {
        System.out.print("Enter contributor ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new type: ");
        String type = scanner.nextLine();
        System.out.print("Enter new region: ");
        String region = scanner.nextLine();
        updateContributor(id, name, type, region);
        System.out.println("Contributor updated successfully!");
    }

    private void handleRemoveContributor(Scanner scanner) {
        System.out.print("Enter contributor ID to remove: ");
        String id = scanner.nextLine();
        removeContributor(id);
        System.out.println("Contributor removed successfully!");
    }

    private void handleRemoveCollaboration(Scanner scanner) {
        System.out.print("Enter first contributor ID: ");
        String id1 = scanner.nextLine();
        System.out.print("Enter second contributor ID: ");
        String id2 = scanner.nextLine();
        System.out.print("Enter project ID: ");
        String projectId = scanner.nextLine();
        removeCollaboration(id1, id2, projectId);
        System.out.println("Collaboration removed successfully!");
    }


    public Contributor_sec33_gr3 getContributorById(String id) {
        return graphManager.getContributorById(id);
    }

    //graphStream magicc
    public void visualizeNetwork() {
        System.out.println("Launching graphical network visualization...");
        NetworkVisualizer_sec33_gr3 visualizer = new NetworkVisualizer_sec33_gr3(graphManager.getGraph());
        visualizer.display();
    }

    public void displayNetwork() {
        System.out.println("\n=== NETWORK OVERVIEW ===");
        System.out.println("Contributors (" + graphManager.getGraph().vertexSet().size() + "):");
        for (Contributor_sec33_gr3 c : graphManager.getGraph().vertexSet()) {
            System.out.println("  - " + c);
        }
        System.out.println("\nCollaborations (" + graphManager.getGraph().edgeSet().size() + "):");
        for (Collaboration_sec33_gr3 e : graphManager.getGraph().edgeSet()) {
            System.out.println("  - " + e);
        }
    }
}
