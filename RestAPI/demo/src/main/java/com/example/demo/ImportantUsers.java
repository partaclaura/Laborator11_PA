package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class ImportantUsers {
    List<Friendship> networkFriendships;
    static List<Integer> importantUsers;
    static int time;
    int nUsers;
    public ImportantUsers(List<Friendship> friendships, int nUsers)
    {
        this.networkFriendships = friendships;
        this.nUsers = nUsers;
        this.importantUsers = new ArrayList<>();
    }

    static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v)
    {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    static void Tarjan(ArrayList<ArrayList<Integer>> graph, int u, boolean visited[],
                       int[] disc, int[] low, int parent, boolean[] isCut)
    {
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;
        for(Integer v : graph.get(u))
        {
            if(!visited[v])
            {
                children++;
                Tarjan(graph, v, visited, disc, low, u, isCut);
                low[u] = Math.min(low[u], low[v]);
                if(parent != -1 && low[v] >= disc[u])
                    isCut[u] = true;
            }
            else if(v != parent)
                low[u] = Math.min(low[u], disc[v]);
        }
        if(parent == -1 && children > 1)
            isCut[u] = true;
    }

    static void findCutVertices(ArrayList<ArrayList<Integer>> graph, int vertices)
    {
        boolean[] visited = new boolean[vertices];
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] isCut = new boolean[vertices];
        int parent = -1;
        time = 0;

        for(int i = 0; i < vertices; i++)
            if(!visited[i])
                Tarjan(graph, i, visited, disc, low, parent, isCut);
        for(int i = 0; i < vertices; i++)
            if(isCut[i])
                importantUsers.add(i);
    }

    public List<Integer> getImportantUsers()
    {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(nUsers);
        for(int i = 0; i < nUsers; i++)
            graph.add(new ArrayList<>());
        for(Friendship friendship : networkFriendships)
        {
            int u = friendship.getIdFriend1();
            int v = friendship.getIdFriend2();
            addEdge(graph, u - 1, v - 1);
        }
        findCutVertices(graph, nUsers);
        return importantUsers;
    }
}
