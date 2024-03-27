import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProblemFileReader
{
    private final String fFilePath;

    public ProblemFileReader(String aFilePath)
    {
        fFilePath = aFilePath;
    }

    public Graph read() throws IOException
    {
        // Read file and store lines in a ArrayList
        List<String> lContents = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(fFilePath));
        String line;

        while ((line = in.readLine()) != null)
            lContents.add(line);

        in.close();

        // Graph data
        int lNumRows;
        int lNumCols;
        Node lSource;
        List<Node> lDestinations;
        List<Node> lWalls;


        // Sanitise all the raw data
        lContents.replaceAll(ProblemFileReader::sanitiseText);

        // Extract the number of rows & columns
        String lRowsAndColumns = lContents.get(0);
        lNumRows = parseRows(lRowsAndColumns);
        lNumCols = parseColumns(lRowsAndColumns);

        // Extract the source Node
        String lSourceLine = lContents.get(1);
        lSource = parseSourceNode(lSourceLine);

        // Extract the destination Node(s)
        String destContent = lContents.get(2);
        lDestinations = parseDestinationNodes(destContent);

        // Extract wall data and create wall Node(s)
        lWalls = parseWallNodes(lContents);

        return new Graph(lNumRows, lNumCols, lSource, lDestinations, lWalls);
    }

    private int parseRows(String aLine)
    {
        String[] lContent = aLine.split(",");
        return Integer.parseInt(lContent[0]);
    }

    private int parseColumns(String aLine)
    {
        String[] lContent = aLine.split(",");
        return Integer.parseInt(lContent[1]);
    }

    private Node parseSourceNode(String aLine)
    {
        String[] lSource = aLine.split(",");
        int x = Integer.parseInt(lSource[0]);
        int y = Integer.parseInt(lSource[1]);

        return new Node(x, y);
    }

    private List<Node> parseDestinationNodes(String aLine)
    {
        // Goal Nodes are separated by |
        String[] destContent = aLine.split("\\|");
        List<Node> lDestinations = new ArrayList<>();
        int x;
        int y;

        for (String dest : destContent)
        {
            String[] arr = dest.split(",");
            x = Integer.parseInt(arr[0]);
            y = Integer.parseInt(arr[1]);

            lDestinations.add(new Node(x, y));
        }

        return lDestinations;
    }

    private List<Node> parseWallNodes(List<String> aLines)
    {
        // Lines 3 onwards are wall data
        // Shifts wall data to zero-th element
        aLines.subList(0, 3).clear();

        ArrayList<String[]> lWallData = new ArrayList<>();
        List<Node> lWalls = new ArrayList<>();

        for (String line : aLines)
        {
            String[] arr = line.split(",");
            lWallData.add(arr);
        }

        for (String[] line : lWallData)
        {
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int w = Integer.parseInt(line[2]);
            int h = Integer.parseInt(line[3]);

            for (int i = 0; i < w; i++)
            {
                for (int j = 0; j < h; j++)
                {
                    lWalls.add(new Node(x + i, y + j));
                }
            }
        }

        return lWalls;
    }

    private static String sanitiseText(String aValue) {
        return aValue.trim()
                .replace("[", "")
                .replace("]", "")
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "");
    }
}
