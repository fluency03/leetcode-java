/**
 * For Directed Acyclic Graph (DAG)
 * 
 * References:
 * https://www.geeksforgeeks.org/topological-sorting/
 * https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
 */

public class TopologicalSort {
    public List<Integer> sortByStack(Set<Integer>[] graph) {
        if (graph == null || graph.length == 0) return new ArrayList<>();
        int len = graph.length;
        boolean[] visited = new boolean[len];
        LinkedList<Integer> ll = new LinkedList<>();

        for (int i=0; i<len; i++) {
            if (!visited[i]) {
                sortByStack(graph, i, visited, stack);
            }
        }
        return ll;
    }

    public void sortByStack(Set<Integer>[] graph, int i, int[] visited, LinkedList<Integer> ll) {
        if (visited[i]) return;
        visited[i] = true;

        for (int j: graph[i]) {
            if (!visited[j]) {
                sortByStack(graph, j, visited, stack);
            }
        }

        ll.addFirst(i);
    }


    public List<Integer> sortByIndegree(Set<Integer>[] graph) {
        if (graph == null || graph.length == 0) return new ArrayList<>();
        int len = graph.length;
        int[] indegree = new int[len];
        for (int i=0; i<len; i++) {
            for (int d: graph[i]) {
                indegree[d]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i=0; i<len; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        List<Integer> sorted = new ArrayList<>();
        int count = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int d: graph[curr]) {
                indegree[d]--;
                if (indegree[d] == 0) {
                    q.add(d);
                }
            }
            count++;
            sorted.add(curr);
        }
        if (count != len) return new ArrayList<>();
        return sorted;
    }


}
