import java.util.LinkedList;
import java.util.List;

/**
 * ISearchAlgorithm
 */
public interface ISearchAlgorithm 
{
    LinkedList<Node> search(Node aRoot, List<Node> aDestinations);
}