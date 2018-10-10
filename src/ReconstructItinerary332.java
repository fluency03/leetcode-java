/**
 * Given a list of airline tickets represented by pairs of departure and
 * arrival airports [from, to], reconstruct the itinerary in order. All of the
 * tickets belong to a man who departs from JFK. Thus, the itinerary must
 * begin with JFK.
 * 
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary
 * that has the smallest lexical order when read as a single string. For
 * example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
 * ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * 
 * Example 1:
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * 
 * Example 2:
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 
 * Explanation: Another possible reconstruction is
 * ["JFK","SFO","ATL","JFK","ATL","SFO"].
 * But it is larger in lexical order.
 */

public class ReconstructItinerary332 {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> graph = new HashMap<>();
        for (String[] ticket: tickets) {
            if (!graph.containsKey(ticket[0])) {
                graph.put(ticket[0], new ArrayList<>());
            }
            graph.get(ticket[0]).add(ticket[1]);
        }
        for (List<String> nexts: graph.values()) {
            Collections.sort(nexts);
        }
        
        List<String> res = new ArrayList<>();
        res.add("JFK");
        dfs(graph, tickets.length, "JFK", res, 0);
        return res;
    }

    private boolean dfs(Map<String, List<String>> graph, int N, String start, List<String> res, int level) {
        if (level == N) return true;
        List<String> nexts = graph.get(start);
        if (nexts == null) return false;
        int size = nexts.size();
        for (int i=0; i<size; i++) {
            String next = nexts.remove(i);
            res.add(next);
            boolean canWork = dfs(graph, N, next, res, level+1);
            if (canWork) return true;
            res.remove(res.size()-1);
            nexts.add(i, next);
        }
        return false;
    }

}
