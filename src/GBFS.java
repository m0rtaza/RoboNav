import java.util.*;

public class GBFS implements ISearchAlgorithm
{
    private final Map<Node, ArrayList<Node>> fAdjacentList;
    private final Cost fCost = new Cost();

    public GBFS(Map<Node, ArrayList<Node>> aAdjacentList)
    {
        this.fAdjacentList = aAdjacentList;
    }

    @Override
    public LinkedList<Node> search(Node aRoot, List<Node> aDestinations)
    {
        HashMap<Node, Integer> heuristic = new HashMap<>();
        Queue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(heuristic::get));
        List<Node> visited = new ArrayList<>();
        LinkedList<Node> path = new LinkedList<>();

        // Add source Node to the frontier
        frontier.add(aRoot);
        heuristic.put(aRoot, fCost.calculateCost(aRoot, aDestinations));

        while (!frontier.isEmpty())
        {
            Node current = frontier.poll();

            if (!visited.contains(current))
                visited.add(current);

            // Is current Node the destination Node(s)
            if (aDestinations.contains(current))
            {
                Node n = current;   // The current Node is the dest. Node
                path.push(n);   // Add the dest. Node to the path
                while (n.getPrevious() != null)
                {
                    path.push(n.getPrevious()); //Add previous Node to the path
                    n = n.getPrevious();    // Set the previous Node as the current
                }

                break;
            }

            // Retrieve each neighbour of the current Node
            List<Node> neighbours = fAdjacentList.get(current);
            for (Node neighbour : neighbours)
            {
                neighbour.setPrevious(current);

                int cost = fCost.calculateCost(neighbour, aDestinations);
                heuristic.put(neighbour, cost);

                // If the neighbour hasn't been visited
                // Add to visited list & add for traversal
                if (!visited.contains(neighbour))
                {
                    visited.add(neighbour);
                    frontier.add(neighbour);
                }
            }
        }

        return path;
    }
}
