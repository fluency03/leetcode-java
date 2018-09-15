/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have
 * to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return
 * the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0. So the correct course order is [0,1]
 *
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have
 * finished both courses 1 and 2. Both courses 1 and 2 should be taken after
 * you finished course 0. So one correct course order is [0,1,2,3]. Another
 * correct ordering is[0,2,1,3].
 *
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented.
 *
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 * Hints:
 * This problem is equivalent to finding the topological order in a directed
 * graph. If a cycle exists, no topological ordering exists and therefore it
 * will be impossible to take all courses.
 *
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera
 * explaining the basic concepts of Topological Sort.
 *
 * Topological sort could also be done via BFS.
 *
 */


public class CourseScheduleII210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Set<Integer>[] graph = new Set[numCourses];
        for (int[] link: prerequisites) {
            int src = link[1];
            int dst = link[0];
            if (graph[src] == null) graph[src] = new HashSet<>();
            graph[src].add(dst);
        }

        int[] order = new int[numCourses];
        boolean[] visited = new boolean[numCourses];
        int[] idx = new int[]{numCourses - 1};
        for (int i=0; i<numCourses; i++) {
            if (!visited[i]) {
                if (!helper(graph, i, numCourses, new boolean[numCourses], visited, idx, order)) {
                    return new int[0];
                }
            }
        }
        return order;
    }

    private boolean helper(Set<Integer>[] graph, int curr, int numCourses, boolean[] path, boolean[] visited, int[] idx, int[] order) {
        if (path[curr]) return false;
        if (visited[curr]) return true;
        visited[curr] = true;
        path[curr] = true;
        if (graph[curr] != null) {
            for (int next: graph[curr]) {
                if (!helper(graph, next, numCourses, path, visited, idx, order)) return false;
            }
        }
        path[curr] = false;
        order[idx[0]--] = curr;
        return true;
    }


    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        Set<Integer>[] graph = new Set[numCourses];
        int[] indegree = new int[numCourses];
        for (int[] link: prerequisites) {
            int src = link[1];
            int dst = link[0];
            indegree[dst]++;
            if (graph[src] == null) graph[src] = new HashSet<>();
            graph[src].add(dst);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i=0; i<numCourses; i++) {
            if (indegree[i] == 0) q.add(i);
        }
        if (q.isEmpty()) return new int[0];

        int[] order = new int[numCourses];
        int i = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            order[i++] = curr;
            if (graph[curr] == null) continue;
            for (int next: graph[curr]) {
                indegree[next]--;
                if (indegree[next] == 0) q.add(next);
            }
        }
        return i == numCourses ? order : new int[0];
    }

}
