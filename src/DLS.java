import java.util.*;

public class DLS implements ISearchAlgorithm
{
    private final Map<Node, ArrayList<Node>> fAdjacentList;

    public DLS(Map<Node, ArrayList<Node>> aAdjacentList)
    {
        this.fAdjacentList = aAdjacentList;
    }

    @Override
    public LinkedList<Node> search(Node aRoot, List<Node> aDestinations)
    {
        Stack<Node> stack = new Stack<>();
        List<Node> visited = new ArrayList<>();
        LinkedList<Node> path = new LinkedList<>();
        int MAX_LEVEL = 4;
        int lLevel = 0;

        // Add source Node to the stack
        stack.push(aRoot);

        while(!stack.isEmpty())
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

            // Increase the level
            lLevel++;

            List<Node> neighbours = fAdjacentList.get(current);
            for (Node neighbour : neighbours)
            {
                if (!visited.contains(neighbour))
                {
                    if (lLevel <= MAX_LEVEL)
                    {
                        neighbour.setPrevious(current);
                        stack.push(neighbour);
                    }
                    else
                    {   // Reset the level
                        lLevel = 0;
                    }
                }
            }
        }

        return path;
    }
}
