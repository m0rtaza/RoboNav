import java.util.*;

public class AStar implements ISearchAlgorithm
{
    private final Map<Node, ArrayList<Node>> fAdjacentList;
    private final Cost fCost = new Cost();

    public AStar(Map<Node, ArrayList<Node>> aAdjacentList)
    {
        this.fAdjacentList = aAdjacentList;
    }

    @Override
    public LinkedList<Node> search(Node aRoot, List<Node> aDestinations)
    {
        // Store the g and f scores
        HashMap<Node, Integer> actualCost = new HashMap<>();
        HashMap<Node, Integer> totalCost = new HashMap<>();

        Queue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(totalCost::get));
        List<Node> visited = new ArrayList<>();
        LinkedList<Node> path = new LinkedList<>();

        // Add the source Node to the queue
        frontier.add(aRoot);
        actualCost.put(aRoot, 0);

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

            // Get all the current Nodes neighbours
            List<Node> neighbours = fAdjacentList.get(current);
            for (Node neighbour : neighbours)
            {
                int tentativeActualCost = actualCost.get(current) + 1;

                if (tentativeActualCost < actualCost.getOrDefault(neighbour, Integer.MAX_VALUE))
                {
                    neighbour.setPrevious(current);
                    actualCost.put(neighbour, tentativeActualCost);

                    // calculate the total cost and set the total cost
                    int heuristic = fCost.calculateCost(neighbour, aDestinations, tentativeActualCost);
                    totalCost.put(neighbour, tentativeActualCost + heuristic);

                    // Add for traversal if not already
                    if (!visited.contains(neighbour))
                    {
                        visited.add(neighbour);
                        frontier.add(neighbour);
                    }
                }
            }

        }

        return path;
    }
}
