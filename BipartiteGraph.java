class Solution {
private:
    bool isBipartiteComponent(int src, int color, vector <int> &vis, vector <vector <int>> &graph)
    {
        vis[src] = color;

        for (int &neighbourNode : graph[src])
        {
            if (not vis[neighbourNode])
            {
                int nextColor = (color == 1 ? 2 : 1);

                if (not isBipartiteComponent(neighbourNode, nextColor, vis, graph))
                {
                    return 0;
                }
            }
            else if (vis[neighbourNode] == vis[src]) return 0;
        }

        return 1;
    }

  public:
    bool isBipartite(int V, vector<vector<int>> &edges)
    {
        vector <vector <int>> graph(V);

        for (auto &edge : edges)
        {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }

        vector <int> vis(V, 0);

        for (int node = 0; node < V; node ++)
        {
            if (not vis[node])
            {
                if (not isBipartiteComponent(node, 1, vis, graph))
                {
                    return 0;
                }
            }
        }

        return 1;
    }
};
