/**
 * For Directed Graph. (It is easy for Undirected graph, we can just do a BFS
 * and DFS starting from any vertex. If BFS or DFS visits all vertices, then
 * the given undirected graph is connected.)
 * 
 * References:
 * https://www.geeksforgeeks.org/strongly-connected-components/
 * https://www.geeksforgeeks.org/connectivity-in-a-directed-graph/
 * https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/
 */

public class StrongConnectivity {
    // Kosaraju's algorithm
    public boolean isStronglyConnected(Set<Integer>[] graph) {
        if (graph == null) return false;
        if (graph.length == 0) return true;
        int len = graph.length;
        boolean[] visited = new boolean[len];

        dfs(graph, 0, visited);
        for (boolean vz: visited) {
            if (!vz) return false;
        }

        Set<Integer>[] graphT = transpose(graph);
        for (int i=0; i<len; i++) visited[i] = false;
        dfs(graphT, 0, visited);
        for (boolean vz: visited) {
            if (!vz) return false;
        }

        return true;
    }

    private void dfs(Set<Integer>[] graph, int i, boolean[] visited) {
        if (visited[i]) return;
        visited[i] = true;
        for (int next: graph[i]) {
            if (!visited[next]) {
                dfs(graph, next, visited);
            }
        }
    }

    private Set<Integer>[] transpose(Set<Integer>[] graph) {
        if (graph == null) return null;
        if (graph.length == 0) return new Set[0];

        int len = graph.length;
        Set<Integer>[] newGraph = new Set[len];
        for (int i=0; i<len; i++) newGraph[i] = new HashSet<>();
        for (int i=0; i<len; i++) {
            for (int next: graph[i]) {
                newGraph[next].add(i);
            }
        }
        return newGraph;
    }


    // Kosaraju's algorithm
    public List<Set<Integer>> stronglyConnectedComponents(Set<Integer>[] graph) {
        if (graph == null) return null;
        List<Set<Integer>> res = new ArrayList<>();
        if (graph.length == 0) return res;

        int len = graph.length;
        boolean[] visited = new boolean[len];
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<len; i++) {
            if (!visited[i]) {
                traverseComponent(graph, i, visited, stack);
            }
        }

        Set<Integer>[] graphT = transpose(graph);
        for (int i=0; i<len; i++) visited[i] = false;

        while (!stack.isEmpty()) {
            int i = stack.pop();
            if (!visited[i]) {
                Set<Integer> newC = new HashSet<>();
                getComponent(graphT, i, visited, newC);
                res.add(newC);
            }
        }

        return res;
    }

    private void traverseComponent(Set<Integer>[] graph, int i, boolean[] visited, Stack<Integer> stack) {
        if (visited[i]) return;
        visited[i] = true;
        for (int next: graph[i]) {
            if (!visited[next]) {
                traverseComponent(graph, next, visited, stack);
            }
        }
        stack.add(i);
    }

    private void getComponent(Set<Integer>[] graph, int i, boolean[] visited, Set<Integer> res) {
        if (visited[i]) return;
        visited[i] = true;
        res.add(i);
        for (int next: graph[i]) {
            if (!visited[next]) {
                getComponent(graph, next, visited, res);
            }
        }
    }

}
