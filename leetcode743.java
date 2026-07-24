import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        // Adjacency List
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : times) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph.get(u).add(new int[]{v, w});
        }

        // Distance array
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Min Heap -> {distance, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]);

        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int currDist = curr[0];
            int node = curr[1];

            if (currDist > dist[node]) {
                continue;
            }

            for (int[] neighbor : graph.get(node)) {

                int next = neighbor[0];
                int weight = neighbor[1];

                if (currDist + weight < dist[next]) {

                    dist[next] = currDist + weight;

                    pq.offer(new int[]{dist[next], next});
                }
            }
        }

        int maxTime = 0;

        for (int i = 1; i <= n; i++) {

            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }
}
