/**
 * Equations are given in the format A / B = k, where A and B are variables
 * represented as strings, and k is a real number (floating point number).
 * Given some queries, return the answers. If the answer does not exist,
 * return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries , where equations.size() == values.size(),
 * and the values are positive. This represents the equations.
 * Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 * The input is always valid. You may assume that evaluating the queries will
 * result in no division by zero and there is no contradiction.
 *
 */


public class EvaluateDivision399 {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        double[] res = new double[queries.length];
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, Map<String, Double>> weights = new HashMap<>();
        initGraph(equations, values, graph, weights);

        for (int i=0; i<queries.length; i++) {
            String[] q = queries[i];
            if (!graph.containsKey(q[0]) || !graph.containsKey(q[1])){
                res[i] = -1.0;
            } else {
                Set<String> visited = new HashSet<>();
                res[i] = dfs(graph, weights, q[0], q[1], visited, 1.0);
            }
        }

        return res;
    }

    private double dfs(Map<String, Set<String>> graph, Map<String, Map<String, Double>> weights, String from, String to, Set<String> visited, double pre) {
        if (from.equals(to)) return 1.0;

        visited.add(from);
        Set<String> set = graph.get(from);

        if (set.contains(to)) {
            return (weights.get(from).get(to) == -1.0) ? -1.0 : weights.get(from).get(to) * pre;
        }

        for (String next: set) {
            if (visited.contains(next)) continue;
            Double newVal = dfs(graph, weights, next, to, visited, pre);
            if (newVal == -1.0) continue;
            return pre * weights.get(from).get(next) * newVal;
        }

        visited.remove(from);
        return -1.0;
    }


    private void initGraph(String[][] equations, double[] values, Map<String, Set<String>> graph, Map<String, Map<String, Double>> weights) {
        for (int i=0; i<values.length; i++) {
            String[] edge = equations[i];
            double weight = values[i];
            addToValueMap(weights, edge[0], edge[1], weight);
            addToValueMap(weights, edge[1], edge[0], (weight == 0.0) ? -1.0 : (1.0 / weight));
            addToValueSet(graph, edge[0], edge[1]);
            addToValueSet(graph, edge[1], edge[0]);
        }
    }

    private void addToValueMap(Map<String, Map<String, Double>> weights, String from, String to, Double weight) {
        Map<String, Double> map = weights.getOrDefault(from, new HashMap<String, Double>());
        map.put(to, weight);
        weights.put(from, map);
    }

    private void addToValueSet(Map<String, Set<String>> graph, String from, String to) {
        Set<String> set = graph.getOrDefault(from, new HashSet<String>());
        set.add(to);
        graph.put(from, set);
    }


    public double[] calcEquation2(String[][] equations, double[] values, String[][] queries) {
        UnionFind uf = new UnionFind();
        int N = equations.length;
        
        for (int i = 0; i<N; i++) {
            uf.union(equations[i][0], equations[i][1], values[i]);
        }
        
        int M = queries.length;
        double[] res = new double[M];
        for (int i=0; i<M; i++) {
            String[] q = queries[i];
            Parent PA = uf.find(q[0]);
            Parent PB = uf.find(q[1]);
            if (PA == null || PB == null || !PA.parent.equals(PB.parent)) {
                res[i] = -1.0;
            } else {
                res[i] = PB.times / PA.times;
            }
        }
        
        return res;
    }
    
    class UnionFind {
        Map<String, Parent> parents = new HashMap<>();

        private Parent find(String A) {
            if (!parents.containsKey(A)) return null;
            Parent pa = parents.get(A);
            if (!pa.parent.equals(A)) {
                Parent p = find(pa.parent);
                pa.parent = p.parent;
                pa.times *= p.times;
            }
            return pa;
        }

        private void union(String A, String B, double times) {
            boolean hasA = parents.containsKey(A);
            boolean hasB = parents.containsKey(B);
            if (!hasA && !hasB) {
                parents.put(A, new Parent(A, 1.0));
                parents.put(B, new Parent(A, times));
            } else if (!hasA) {
                parents.put(A, new Parent(B, 1.0 / times));
            } else if (!hasB) {
                parents.put(B, new Parent(A, times));
            } else {
                Parent pa = find(A);
                Parent pb = find(B);
                pb.parent = pa.parent;
                pb.times = (pa.times / pb.times) * times;
            }
        }
    }
    
    class Parent {
        String parent;
        Double times;
        Parent(String parent, Double times) {
            this.parent = parent;
            this.times = times;
        }
    }

}
