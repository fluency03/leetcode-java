/**
 * Given a list accounts, each element accounts[i] is a list of strings, where
 * the first element accounts[i][0] is a name, and the rest of the elements are
 * emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong
 * to the same person if there is some email that is common to both accounts.
 * Note that even if two accounts have the same name, they may belong to
 * different people as people could have the same name. A person can have any
 * number of accounts initially, but all of their accounts definitely have the
 * same name.
 *
 * After merging the accounts, return the accounts in the following format:
 * the first element of each account is the name, and the rest of the elements
 * are emails in sorted order. The accounts themselves can be returned in any
 * order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 *
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Note:
 *
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 *
 */


public class AccountsMerge721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();

        Map<String, Set<Set<String>>> map = new HashMap<>();

        for (List<String> acc: accounts) {
            String name = acc.get(0);
            if (!map.containsKey(name)) {
                Set<String> set = new HashSet<>(acc);
                set.remove(name);
                Set<Set<String>> setOfSet = new HashSet<>();
                setOfSet.add(set);
                map.put(name, setOfSet);
            } else {
                Set<Set<String>> toBeMerged = new HashSet<>();
                Set<Set<String>> setOfSet = map.get(name);
                Set<String> curr = new HashSet<>(acc);
                curr.remove(name);
                for (String email: curr) {
                    Set<String> found = null;
                    for (Set<String> e: setOfSet) {
                        if (e.contains(email)) {
                            found = e;
                            break;
                        }
                    }
                    if (found != null) {
                        setOfSet.remove(found);
                        toBeMerged.add(found);
                    }
                }
                for (Set<String> s: toBeMerged) {
                    curr.addAll(s);
                }
                setOfSet.add(curr);
            }
        }

        for (Map.Entry<String, Set<Set<String>>> en: map.entrySet()) {
            for (Set<String> es: en.getValue()) {
                List<String> list = new ArrayList<>(es);
                java.util.Collections.sort(list);
                list.add(0, en.getKey());
                res.add(list);
            }
        }

        return res;
    }


    /**
     * https://leetcode.com/problems/accounts-merge/solution/
     */
    int MAX_VAL = 10000;
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToId = new HashMap<>();

        DSU dsu = initDSU(accounts, emailToName, emailToId);

        Map<Integer, List<String>> res = new HashMap();
        for (String email: emailToName.keySet()) {
            int id = dsu.find(emailToId.get(email));
            res.computeIfAbsent(id, x -> new ArrayList()).add(email);
        }

        for (List<String> emails: res.values()) {
            Collections.sort(emails);
            emails.add(0, emailToName.get(emails.get(0)));
        }

        return new ArrayList(res.values());
    }


    private DSU initDSU(List<List<String>> accounts, Map<String, String> emailToName, Map<String, Integer> emailToId) {
        DSU dsu = new DSU(MAX_VAL+1);

        int id = 0;
        for (List<String> acc: accounts) {
            String name = acc.get(0);

            for (int i=1; i<acc.size(); i++) {
                String email = acc.get(i);
                emailToName.putIfAbsent(email, name);
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email, id);
                    id++;
                }

                dsu.union(emailToId.get(acc.get(1)), emailToId.get(email));
            }
        }

        return dsu;
    }


    class DSU {
        int[] parent;

        public DSU(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }


    /**
     * https://leetcode.com/problems/accounts-merge/discuss/109158/Java-Solution-(Build-graph-+-DFS-search)
     */
    public List<List<String>> accountsMerge3(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Set<String>> graph = initGraph(accounts, emailToName);

        Set<String> visited = new HashSet<>();
        List<List<String>> res = new LinkedList<>();

        for (String email: emailToName.keySet()) {
            List<String> list = new LinkedList<>();
            if (visited.add(email)) {
                dfs(graph, email, visited, list);
                Collections.sort(list);
                list.add(0, emailToName.get(email));
                res.add(list);
            }
        }

        return res;
    }

    private Map<String, Set<String>> initGraph(List<List<String>> accounts, Map<String, String> emailToName) {
        Map<String, Set<String>> graph = new HashMap<>();

        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                if (!graph.containsKey(acc.get(i))) {
                    graph.put(email, new HashSet<>());
                }

                emailToName.put(email, name);

                if (i == 1) continue;
                graph.get(email).add(acc.get(i-1));
                graph.get(acc.get(i-1)).add(email);
            }
        }
        return graph;
    }

    private void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
        list.add(email);
        for (String l: graph.get(email)) {
            if (visited.add(l)) dfs(graph, l, visited, list);
        }
    }


}
