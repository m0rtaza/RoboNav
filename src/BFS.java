import java.util.*;

public class BFS implements ISearchAlgorithm
{
    private final Map<Node, ArrayList<Node>> fAdjacentList;

    public BFS(Map<Node, ArrayList<Node>> aAdjacentList)
    {
        this.fAdjacentList = aAdjacentList;
    }

    public LinkedList<Node> search(Node aRoot, List<Node> aDestinations)
    {
        Queue<Node> queue = new LinkedList<>();
        List<Node> visited = new ArrayList<>();
        LinkedList<Node> path = new LinkedList<>();

        queue.add(aRoot);

        while(!queue.isEmpty())
        {
            Node current = queue.poll();

            if (!visited.contains(current))
                visited.add(current);

            // Check if the current Node is the destination(s)
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
            List<Node> neighbours = fAdjacentList.get(current);
            for (Node neighbour : neighbours)
            {
                if (!visited.contains(neighbour))
                {
                    neighbour.setPrevious(current);
                    queue.add(neighbour);
                }
            }
        }

        return path;
    }
}
