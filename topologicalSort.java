class Solution {
  public:
    vector<int> topoSort(int V, vector<vector<int>>& edges)
    {
        vector <vector <int>> graph(V); // adjacency list

        vector <int> indegree(V, 0);

        for (auto &edge : edges)
        {
            indegree[edge[1]] ++;
            graph[edge[0]].push_back(edge[1]);
        }

        queue <int> q;

        for (int node = 0; node < V; node ++)
        {
            if (indegree[node] == 0) q.push(node);
        }
    

        vector <int> res;

        while (not q.empty())
        {
            int src = q.front();
            q.pop();

            res.push_back(src);

            for (int &neighbourNode : graph[src])
            {
                if (-- indegree[neighbourNode] == 0)
                {
                    q.push(neighbourNode);
                }
            }
        }

        return res;
    }
};
