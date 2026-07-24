import java.util.*;

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {

        // If starting cell is blocked
        if (grid[0][0] == 1) {
            return -1;
        }

        int n = grid.length;

        // If destination is blocked
        if (grid[n - 1][n - 1] != 0) {
            return -1;
        }

        // Mark starting cell with distance = 1
        grid[0][0] = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {

            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];

            int distance = grid[x][y];

            // Visit all 8 directions
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {

                    // Skip current cell
                    if (dx == 0 && dy == 0) {
                        continue;
                    }

                    int newX = x + dx;
                    int newY = y + dy;

                    // Check boundaries and unvisited cell
                    if (newX >= 0 && newX < n &&
                        newY >= 0 && newY < n &&
                        grid[newX][newY] == 0) {

                        // Store distance
                        grid[newX][newY] = distance + 1;

                        // Add to queue
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }

        // Return answer
        return grid[n - 1][n - 1] == 0 ? -1 : grid[n - 1][n - 1];
    }
}
