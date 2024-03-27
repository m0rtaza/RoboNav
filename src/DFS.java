import java.util.*;

public class DFS implements ISearchAlgorithm
{
    private final Map<Node, ArrayList<Node>> fAdjacentList;

    public DFS(Map<Node, ArrayList<Node>> aAdjacentList)
    {
        this.fAdjacentList = aAdjacentList;
    }

    public LinkedList<Node> search(Node aRoot, List<Node> aDestinations)
    {
        Stack<Node> stack = new Stack<>();
        List<Node> visited = new ArrayList<>();
        LinkedList<Node> path = new LinkedList<>();

        // Add 'source' Node to the Stack
        stack.push(aRoot);

        while (!stack.isEmpty())
        {
            Node current = stack.pop();

            if (!visited.contains(current))
                visited.add(current);

            // Check if the current Node is the destination Node(s)
            // If it is, stop the search
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
                    stack.push(neighbour);
                }
            }
        }

        return path;
    }

}