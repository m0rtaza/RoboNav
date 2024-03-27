package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import enums.Direction;

public abstract class GraphUtils
{
    public static HashMap<Direction, int[]> getDirections()
    {
        HashMap<Direction, int[]> lDirection = new HashMap<>();

        lDirection.put(Direction.UP, new int[] { 0, -1 });
        lDirection.put(Direction.DOWN, new int[] { 0, 1 });
        lDirection.put(Direction.LEFT, new int[] { -1, 0 });
        lDirection.put(Direction.RIGHT, new int[] { 1, 0 });

        return lDirection;
    }

    public static <T, E> Set<T> getDirectionByValue(Map<T, E> map, E value)
    {
        return map.entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
