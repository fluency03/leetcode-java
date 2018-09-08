/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have
 * to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0. So it is possible.
 *
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0, and to take course 0 you should also have finished
 * course 1. So it is impossible.
 *
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented.
 *
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 * Hints:
 * This problem is equivalent to finding if a cycle exists in a directed graph.
 * If a cycle exists, no topological ordering exists and therefore it will be
 * impossible to take all courses.
 *
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera
 * explaining the basic concepts of Topological Sort.
 *
 * Topological sort could also be done via BFS.
 *
 */


public class CourseSchedule207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = initGraph(prerequisites);
        Set<Integer> visited = new HashSet<>();

        for (int i=0; i<numCourses; i++) {
            if (graph.containsKey(i) && !visited.contains(i) && !dfs(graph, visited, new HashSet<>(), i)) return false;
        }

        return true;
    }

    private boolean dfs(Map<Integer, Set<Integer>> graph, Set<Integer> visited, Set<Integer> onStack, Integer pre) {
        if (onStack.contains(pre)) return false;
        visited.add(pre);
        onStack.add(pre);
        if (!graph.containsKey(pre)) return true;

        Set<Integer> adj = graph.get(pre);
        for (Integer curr: adj) {
            if (graph.containsKey(curr) && !dfs(graph, visited, onStack, curr)) return false;
        }
        onStack.remove(pre);
        return true;
    }

    private Map<Integer, Set<Integer>> initGraph(int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] p: prerequisites) {
            addEdge(graph, p);
        }
        return graph;
    }

    private void addEdge(Map<Integer, Set<Integer>> graph, int[] p) {
        Set<Integer> adj = graph.getOrDefault(p[1], new HashSet<>());
        adj.add(p[0]);
        graph.put(p[1], adj);
    }


    /**
     * https://discuss.leetcode.com/topic/15762/java-dfs-and-bfs-solution
     */
    public boolean canFinish2s(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count=0;

        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();

        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][1]]++;
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);
                count++;
            }
        }

        while(queue.size() != 0){
            int course = (int)queue.poll();
            for(int i=0; i<graph[course].size();i++){
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;
                if(degree[pointer] == 0){
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if(count == numCourses)
            return true;
        else
            return false;
    }


    /**
     * Kahn’s algorithm for Topological Sorting
     * https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
     */
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Set<Integer>[] graph = new Set[numCourses];
        for (int[] link: prerequisites) {
            if (graph[link[1]] == null) graph[link[1]] = new HashSet<Integer>();
            graph[link[1]].add(link[0]);
            indegree[link[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i=0; i<numCourses; i++) {
            if (indegree[i] == 0) q.add(i);
        }

        int count = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (graph[curr] == null) {
                count++;
                continue;
            }
            for (int next: graph[curr]) {
                indegree[next]--;
                if (indegree[next] == 0) q.add(next);
            }
            count++;
        }
        return count == numCourses;
    }


    public boolean canFinish4(int numCourses, int[][] prerequisites) {
        Set<Integer>[] graph = new Set[numCourses];
        for (int[] link: prerequisites) {
            if (graph[link[1]] == null) graph[link[1]] = new HashSet<Integer>();
            graph[link[1]].add(link[0]);
        }
        return !isCyclic(graph, numCourses);
    }

    private boolean isCyclic(Set<Integer>[] graph, int numCourses) {
        boolean[] visited = new boolean[numCourses];
        boolean[] trace = new boolean[numCourses];
        for (int i=0; i<numCourses; i++) {
            if (isCyclic(graph, i, visited, trace)) return true;
        }
        return false;
    }

    private boolean isCyclic(Set<Integer>[] graph, int start, boolean[] visited, boolean[] trace) {
        if (trace[start]) return true;
        if (visited[start]) return false;

        visited[start] = true;
        if (graph[start] == null) return false;

        trace[start] = true;
        for (int next: graph[start]) {
            if (isCyclic(graph, next, visited, trace)) return true;
        }
        trace[start] = false;
        return false;
    }

}
