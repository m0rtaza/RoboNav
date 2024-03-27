import util.DirectionUtils;

import java.io.IOException;
import java.util.*;

public class Main {
    private ProblemFileReader fReader;
    private Graph fGraph;

    public Main(String aFilePath, String aSearchAlgorithm)
    {
        fReader = new ProblemFileReader(aFilePath);

        try
        {
            fGraph = fReader.read();
        }
        catch (IOException e)
        {
            System.out.println("Error: File not found \"" + aFilePath + "\"");
            System.out.println("Please check the file path");
            System.exit(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        var lPath = performSearch(fGraph, aSearchAlgorithm, aFilePath);

        if (lPath != null)
            printResult(lPath, aSearchAlgorithm, aFilePath, fGraph.getNumNodes());
    }

    private LinkedList<Node> performSearch(Graph aGraph, String aSearchAlgorithm, String aFilePath)
    {
        ISearchAlgorithm lSearch;
        Map<Node, ArrayList<Node>> lAdjacentList = aGraph.buildAdjacencyList();
        Node lSource = aGraph.getSource();
        List<Node> lDestinations = aGraph.getDestinations();

        switch (aSearchAlgorithm.toLowerCase()) {
            case "dfs" -> {
                lSearch = new DFS(lAdjacentList);
                return lSearch.search(lSource, lDestinations);
            }
            case "bfs" -> {
                lSearch = new BFS(lAdjacentList);
                return lSearch.search(lSource, lDestinations);
            }
            case "gbfs" -> {
                lSearch = new GBFS(lAdjacentList);
                return lSearch.search(lSource, lDestinations);
            }
            case "astar" -> {
                lSearch = new AStar(lAdjacentList);
                return lSearch.search(lSource, lDestinations);
            }
            case "dls" -> {
                lSearch = new DLS(lAdjacentList);
                return lSearch.search(lSource, lDestinations);
            }
            case "cus2" -> {
                return null;
            }
            default -> {
                System.out.println("The search algorithm provided is incorrect or not implemented.");
                return null;
            }
        }
    }

    private void printResult(LinkedList<Node> aPath, String aAlgorithm, String aFileName, int aNumNodes)
    {
        List<String> compressed = new ArrayList<>();

        System.out.printf("%s %s %s%n", aAlgorithm, aFileName, aNumNodes);
        if (aPath.isEmpty())
            System.out.println("No solution found.");
        else
        {
            for (Node n : aPath)
            {
                // Exclude the first Node as it doesn't have a previous Node
                if (n.directionTaken() != null)
                {
                    compressed.add(n.directionTaken());
                }
            }

            compressed = DirectionUtils.compressDirections(compressed);
            compressed.forEach(System.out::print);
        }
    }

    public static void main(String[] args) {
        new Main(args[0], args[1]);
    }
}
