import enums.Direction;

public class Node
{
    private final int fX;
    private final int fY;
    private Node fPrevious;
    private Direction fDirectionTaken;

    public Node(int aX, int aY)
    {
        this.fX = aX;
        this.fY = aY;

        fPrevious = null;
    }

    public Node (int aX, int aY, Direction aDirection)
    {
        this.fX = aX;
        this.fY = aY;
        this.fDirectionTaken = aDirection;

        fPrevious = null;
    }

    public String directionTaken()
    {
        return fDirectionTaken != null ? fDirectionTaken.toString() : null;
    }

    public int getX()
    {
        return this.fX;
    }

    public int getY()
    {
        return this.fY;
    }

    public Node getPrevious()
    {
        return fPrevious;
    }

    public void setPrevious(Node aNode)
    {
        this.fPrevious = aNode;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (!(obj instanceof Node node))
            return false;

        return this.fX == node.fX && this.fY == node.fY;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 31 * hash + fX;
        hash = 31 * hash + fY;

        return hash;
    }

    // Mostly for debugging purposes
    public String label()
    {
        return String.format("(%d, %s)", fX, fY);
    }
}