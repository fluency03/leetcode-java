/**
 * Given an undirected graph, return true if and only if it is bipartite.
 * 
 * Recall that a graph is bipartite if we can split it's set of nodes into two
 * independent subsets A and B such that every edge in the graph has one node
 * in A and another node in B.
 * 
 * The graph is given in the following form: graph[i] is a list of indexes j
 * for which the edge between nodes i and j exists.  Each node is an integer
 * between 0 and graph.length - 1.  There are no self edges or parallel
 * edges: graph[i] does not contain i, and it doesn't contain any element twice.
 * 
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation: 
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * 
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation: 
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 * 
 * Note:
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 * The graph is undirected: if any element j is in graph[i], then i will be
 * in graph[j].
 */

public class IsGraphBipartite785 {
    public boolean isBipartite(int[][] graph) {
        if (graph.length == 0) return true;
        int N = graph.length;
        int[] visited = new int[N];
        for (int i=0; i<N; i++) {
            if (visited[i] == 0 && !helper(graph, i, visited, 1)) return false;
        }
        return true;
    }

    private boolean helper(int[][] graph, int curr, int[] visited, int pre) {
        if (visited[curr] != 0) return visited[curr] == pre;
        visited[curr] = pre;
        for (int next: graph[curr]) {
            if (visited[next] == pre) return false;
            if (visited[next] == 0) {
                if (!helper(graph, next, visited, -pre)) return false;
            }
        }
        return true;
    }


    public boolean isBipartite2(int[][] graph) {
        if (graph.length == 0) return true;
        int N = graph.length;
        int[] visited = new int[N];
        for (int i=0; i<N; i++) {
            if (visited[i] == 0) {
                Queue<Integer> q = new LinkedList<>();
                visited[i] = 1;
                q.add(i);
                while (!q.isEmpty()) {
                    int curr = q.poll();
                    
                    for (int next: graph[curr]) {
                        if (visited[next] == 0) {
                            visited[next] = - visited[curr];
                            q.add(next);
                        } else {
                            if (visited[next] == visited[curr]) return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/is-graph-bipartite/discuss/118959/JAVA-union-find-easy-solution
     */
    public boolean isBipartite3(int[][] graph) {
        int n= graph.length;
        int[] parent= new int[n];
        for(int i=0; i<n; i++){
            parent[i]=i;
        }
        for(int i=0; i<n; i++){
            for(int j=1; j<graph[i].length; j++){
                parent[findParent(parent,graph[i][j])]=findParent(parent,graph[i][0]);
            }
        }
        for(int i=0; i<n; i++){
            if(graph[i].length>0){
                if(findParent(parent,i)==findParent(parent,graph[i][0])) return false;
            }
        }
        
        return true;
        
    }

    private int findParent(int[]parent, int p){
        if(parent[p]==p) return p;
        parent[p]=findParent(parent, parent[p]);
        return parent[p];
    }

}
