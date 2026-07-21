class Solution {
private:
    void getTopoSort(int src, vector <bool> &vis, stack <int> &s, vector <vector <int>> &graph)
    {
        vis[src] = 1;

        for (int &neighbourNode : graph[src])
        {
            if (not vis[neighbourNode])
            {
                getTopoSort(neighbourNode, vis, s, graph);
            }
        }

        s.push(src);
    }

  public:
    vector<int> topoSort(int V, vector<vector<int>>& edges)
    {
        vector <vector <int>> graph(V);

        for (auto &edge : edges) graph[edge[0]].push_back(edge[1]);

        vector <bool> vis(V, 0);

        stack <int> s;

        for (int node = 0; node < V; node ++)
        {
            if (not vis[node])
            {
                getTopoSort(node, vis, s, graph);
            }
        }
    }
};
