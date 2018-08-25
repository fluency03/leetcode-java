/**
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from
 * node 0 to node N-1, and return them in any order.
 * 
 * The graph is given as follows:  the nodes are0, 1, ..., graph.length - 1.
 * graph[i] is a list of all nodes j for which the edge (i, j) exists.
 * 
 * Example:
 * Input: [[1,2], [3], [3], []] 
 * Output: [[0,1,3],[0,2,3]] 
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * 
 * Note:
 * The number of nodes in the graph will be in the range [2, 15].
 * You can print different paths in any order, but you should keep the order
 * of nodes inside one path.
 */

public class AllPathsFromSourceToTarget797 {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph == null || graph.length == 0) return res;
        int N = graph.length;
        backtrace(graph, 0, N-1, new ArrayList<>(), res, N);
        return res;
    }

    private void backtrace(int[][] graph, int current, int dest, List<Integer> path, List<List<Integer>> res, int N) {
        if (current == dest) {
            path.add(current);
            res.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        
        path.add(current);
        for (int child: graph[current]) {
            backtrace(graph, child, N-1, path, res, N);
        }
        path.remove(path.size() - 1);
    }


    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph == null || graph.length == 0) return res;
        int N = graph.length;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        backtrace2(graph, 0, N-1, path, res, N);
        return res;
    }

    private void backtrace2(int[][] graph, int current, int dest, List<Integer> path, List<List<Integer>> res, int N) {
        if (current == dest) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int child: graph[current]) {
            path.add(child);
            backtrace2(graph, child, N-1, path, res, N);
            path.remove(path.size() - 1);
        }
    }

}
