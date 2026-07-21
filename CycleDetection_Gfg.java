import java.util.*;

class Solution {
    public boolean isCycle(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i] && bfs(i, adj, vis))
                return true;
        }

        return false;
    }

    boolean bfs(int src, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, -1});
        vis[src] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int parent = cur[1];

            for (int nei : adj.get(node)) {
                if (!vis[nei]) {
                    vis[nei] = true;
                    q.offer(new int[]{nei, node});
                } else if (nei != parent) {
                    return true;
                }
            }
        }

        return false;
    }
}
