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
        Map<Integer, Set<Integer>> graph = getGraph(numCourses, prerequisites);

        Stack<Integer> st = new Stack<>();
        boolean[] marked = new boolean[numCourses];
        boolean[] onTrace = new boolean[numCourses];
        for (int i=0; i<numCourses; i++) {
            if (!marked[i] && !dfs(graph, st, marked, onTrace, i)) return new int[0];
        }

        int[] res = new int[numCourses];
        int j = 0;
        while (!st.isEmpty()) {
            res[j] = st.pop();
            j++;
        }

        return res;
    }

    private boolean dfs(Map<Integer, Set<Integer>> graph, Stack<Integer> st, boolean[] marked, boolean[] onTrace, Integer n) {
        marked[n] = true;
        onTrace[n] = true;
        for (Integer a: graph.get(n)) {
            if (!marked[a]) {
                if(!dfs(graph, st, marked, onTrace, a)) return false;
            } else if (onTrace[a]) {
                return false;
            }
        }
        onTrace[n] = false;
        st.push(n);
        return true;
    }

    private Map<Integer, Set<Integer>> getGraph(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i=0; i<numCourses; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        for (int[] edge: prerequisites) {
            Set<Integer> adj = graph.get(edge[1]);
            adj.add(edge[0]);
            graph.put(edge[1], adj);
        }
        return graph;
    }







}
