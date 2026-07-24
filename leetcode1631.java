import java.util.*;

class Solution {
    public int minimumEffortPath(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        int[][] effort = new int[m][n];

        for (int[] row : effort) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        effort[0][0] = 0;

        // {effort, row, col}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]);

        pq.offer(new int[]{0, 0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int currEffort = curr[0];
            int row = curr[1];
            int col = curr[2];

            // Destination reached
            if (row == m - 1 && col == n - 1) {
                return currEffort;
            }

            // Skip outdated entry
            if (currEffort > effort[row][col]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {

                int newRow = row + dr[i];
                int newCol = col + dc[i];

                if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n) {

                    int edgeCost = Math.abs(
                            heights[row][col] - heights[newRow][newCol]);

                    int newEffort = Math.max(currEffort, edgeCost);

                    if (newEffort < effort[newRow][newCol]) {

                        effort[newRow][newCol] = newEffort;

                        pq.offer(new int[]{
                                newEffort,
                                newRow,
                                newCol
                        });
                    }
                }
            }
        }

        return 0;
    }
}
