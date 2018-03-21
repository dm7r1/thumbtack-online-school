package net.thumbtack.school.matrix;

import java.util.*;
import java.util.stream.Collectors;

public class MatrixNonSimilarRows {
    Set<int[]> nonSimilarRows;

    public MatrixNonSimilarRows(int[][] matrix) {
        Map<Set<Integer>, List<List<Integer>>> rows = new HashMap<>();
        for(int[] row: matrix) {
            List<Integer> list = Arrays.stream(row).boxed().collect(Collectors.toList());
            Set<Integer> set = new HashSet<>(list);
            if(!rows.containsKey(set))
                rows.put(set, new LinkedList<>());
            rows.get(set).add(list);
        }

        nonSimilarRows = new TreeSet<>(
            (int[] o1, int[] o2) -> {
                if(o1.length != o2.length)
                    return Integer.compare(o1.length, o2.length);
                for(int i = 0; i < o1.length; ++i)
                    if(o1[i] > o2[i])
                        return 1;
                    else if (o2[i] > o1[i])
                        return -1;
                return 0;
            });
        for(List<List<Integer>> lists: rows.values()) {
            nonSimilarRows.add(lists.get(0).stream().mapToInt(a->a).toArray());
        }
    }

    public Set<int[]> getNonSimilarRows() {
        return nonSimilarRows;
    }
}
