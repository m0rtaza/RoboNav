import java.util.List;

public class Cost
{
    public Cost() { }

    public int calculateCost(Node aNode, List<Node> aTargets, int aActualCost)
    {
        int lHeuristic = Integer.MAX_VALUE;

        for (Node n : aTargets)
        {
            int smallestCost = manhattanDistance(aNode.getX(), aNode.getY(), n.getX(), n.getY());
            if (smallestCost < lHeuristic)
                lHeuristic = smallestCost;
        }

        return aActualCost + lHeuristic;
    }

    public int calculateCost(Node aNode, List<Node> aTargets)
    {
        int lHeuristic = Integer.MAX_VALUE;

        for (Node n : aTargets)
        {
            int smallestCost = manhattanDistance(aNode.getX(), aNode.getY(), n.getX(), n.getY());
            if (smallestCost < lHeuristic)
                lHeuristic = smallestCost;
        }

        return lHeuristic;
    }

    private int manhattanDistance(int aCurrentX, int aCurrentY, int aTargetX, int aTargetY)
    {
        return (Math.abs(aCurrentX - aTargetX)) + (Math.abs(aCurrentY - aTargetY));
    }

    private int euclideanDistance(int aCurrentX, int aCurrentY, int aTargetX, int aTargetY)
    {
        return (int)Math.sqrt((aTargetY - aCurrentY) * (aTargetY - aCurrentY) + (aTargetX - aCurrentX) * (aTargetX - aCurrentX));
    }
}
