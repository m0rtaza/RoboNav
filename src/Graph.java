import util.GraphUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import enums.Direction;

public class Graph 
{
    private final int fRows;
    private final int fColumns;
    private final Node fSource;
    private final List<Node> fDestinations;
    private final List<Node> fWalls;

    private HashMap<Node, ArrayList<Node>> fAdjacencyList;
    private final HashMap<Direction, int[]> fDirection;

    public Graph(int aRows, int aColumns, Node aSource, List<Node> aDestinations, List<Node> aWalls)
    {
        this.fRows = aRows;
        this.fColumns = aColumns;
        this.fSource = aSource;
        this.fDestinations = aDestinations;
        this.fWalls = aWalls;

        fDirection = GraphUtils.getDirections();
    }

    public HashMap<Node, ArrayList<Node>> buildAdjacencyList()
    {
        fAdjacencyList = new HashMap<>();

        for (int x = 0; x < fColumns; x++)
        {
            for (int y = 0; y < fRows; y++)
            {
                Node node = new Node(x, y);

                // If the node is NOT a wall or a destination Node
                // add it to the adjacency list
                if (!fWalls.contains(node) && !fDestinations.contains(node))
                {
                    addNode(node);
                }
            }
        }

        return fAdjacencyList;
    }

    private void addNode (Node aNode)
    {
        ArrayList<Node> neighbours = getNeighbours(aNode);
        fAdjacencyList.putIfAbsent(aNode, neighbours);
    }

    private ArrayList<Node> getNeighbours(Node aNode)
    {
        ArrayList<Node> lNeighbours = new ArrayList<>();

        // Check all the Nodes directions
        for (Direction direction : Direction.values())
        {
            int[] dir = fDirection.get(direction);
            Node neighbour = new Node(aNode.getX() + dir[0], aNode.getY() + dir[1], direction);

            if (isPossibleMove(neighbour, direction))
            {
                lNeighbours.add(neighbour);
            }
        }

        return lNeighbours;
    }

    private boolean isPossibleMove(Node aNode, Direction aDirection)
    {
        // First, check if the neighbour node is a wall
        // before checking the graph bounds
        if (fWalls.contains(aNode))
            return false;

        int x = aNode.getX();
        int y = aNode.getY();

        switch (aDirection) {
            case UP -> { return y >= 0; }
            case DOWN -> { return y <= fRows - 1; }
            case LEFT -> { return x >= 0; }
            case RIGHT -> { return x <= fColumns - 1; }
        }

        // This return statement should never be reachable
        // It's just to satisfy IDE
        return false;
    }

    public LinkedList<Node> trace(Node current)
    {
        LinkedList<Node> pathTaken = new LinkedList<>();

        Node n = current;   // The current Node is the dest. Node
        pathTaken.push(n);   // Add the dest. Node to the path
        while (n.getPrevious() != null)
        {
            pathTaken.push(n.getPrevious()); //Add previous Node to the path
            n = n.getPrevious();    // Set the previous Node as the current
        }

        return pathTaken;
    }

    public int getNumNodes()
    {
        return fAdjacencyList.size();
    }

    public Node getSource()
    {
        return this.fSource;
    }

    public int getRows()
    {
        return this.fRows;
    }

    public int getColumns()
    {
        return this.fColumns;
    }

    public List<Node> getDestinations()
    {
        return this.fDestinations;
    }

}
