package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {
    private int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {
        HashMap<Set<Integer>, int[]> nonSimilarRows = new HashMap<>();

        for(int[] row: matrix) {
            Set<Integer> rowSet = new HashSet<>();
            for(int e: row) {
                rowSet.add(e);
            }
            if(!nonSimilarRows.containsKey(rowSet)) {
                nonSimilarRows.put(rowSet, row);
            }
        }

        return new HashSet<>(nonSimilarRows.values());
    }
}
