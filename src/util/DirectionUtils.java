package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DirectionUtils
{
    public static List<String> compressDirections(List<String> aDirections)
    {
        List<String> compressedDirections = new ArrayList<>();
        Iterator<String> iterator = aDirections.listIterator();
        String previous = null;
        int count = 1;

        while (iterator.hasNext())
        {
            String current = iterator.next();

            if (previous != null)
            {
                if (current.equals(previous))
                {
                    count++;
                }
                else if (count > 1)
                {
                    compressedDirections.add(previous + "(x" + count + "); ");
                    count = 1;
                }
                else
                {
                    compressedDirections.add(previous + "; ");
                }
            }

            previous = current;
        }

        // Don't add the last direction into the list
        if (count > 1)
            compressedDirections.add(previous + "(x" + count + "); ");
        else
            compressedDirections.add(previous + "; ");

        return compressedDirections;
    }
}
