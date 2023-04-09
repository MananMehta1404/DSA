package Graph.Detect_Cycle;

import java.util.*;

public class Undirected_BFS {

    // Creating a Edge class to store the source, destination.
    static class Edge{
        int src;
        int des;

        Edge(int s, int d){
            this.src = s;
            this.des = d;
        }
    }

    public static List<List<Edge>> createGraph(int n, int[][] edges){

        List<List<Edge>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++){
            int src = edges[i][0];
            int des = edges[i][1];

            graph.get(src).add(new Edge(src, des));
            graph.get(des).add(new Edge(des, src));
        }

        return graph;
    }

    static class Pair{
        int node;
        int parent;

        Pair(int n, int p){
            this.node = n;
            this.parent = p;
        }
    }

    public static boolean detectCycle_BFS(List<List<Edge>> graph, int src, boolean[] visited){
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(src, -1));
        visited[src] = true;

        while(!queue.isEmpty()){
            int node = queue.peek().node;
            int parent = queue.peek().parent;
            queue.remove();

            for(int i = 0; i < graph.get(node).size(); i++){
                Edge e = graph.get(node).get(i);
                if(!visited[e.des]){
                    queue.add(new Pair(e.des, node));
                    visited[e.des] = true;
                }
                else{
                    if(e.des != parent)
                        return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 9;
        int[][] edges = {{0, 1}, {1, 2}, {0, 3}, {2, 3}, {4, 5}, {5, 6}, {6, 4}, {7, 8}};

        List<List<Edge>> graph = createGraph(n, edges);

        // Graph with Disconnected Components 
        /*
              1 -- 2     
             /    /        4 - 5     7 -- 8
            0    /          \ /
             \  /            6
              3
        */

        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                if(detectCycle_BFS(graph, i, visited)){
                    System.out.println("Cycle is present at node " + i + ".");
                }
            }
        }
    }
}
