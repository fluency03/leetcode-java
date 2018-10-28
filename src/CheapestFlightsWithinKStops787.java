/**
 * There are n cities connected by m flights. Each fight starts from city u and
 * arrives at v with a price w.
 * 
 * Now given all the cities and flights, together with starting city src and
 * the destination dst, your task is to find the cheapest price from src to dst
 * with up to k stops. If there is no such route, output -1.
 * 
 * Example 1:
 * Input: 
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 *
 * Explanation: 
 * The graph looks like this:
 * https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png
 * 
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as
 * marked red in the picture.
 * 
 * Example 2:
 * Input: 
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * 
 * Explanation: 
 * The graph looks like this:
 * https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png
 * 
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as
 * marked blue in the picture.
 * 
 * Note:
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0
 * to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 */

public class CheapestFlightsWithinKStops787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = constructGraph(n, flights);
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{src, 0});
        int[][] dist = new int[n][K+2];
        for (int[] row: dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[src][0] = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int[] nei = graph[cur[0]];
            for (int next=0; next<n; next++) {
                int weight = nei[next];
                if (weight == 0) continue;
                int newDis = dist[cur[0]][cur[1]] + weight;
                if (cur[1] + 1 <= K+1 && newDis < dist[next][cur[1]+1]) {
                    dist[next][cur[1]+1] = newDis;
                    q.add(new int[]{next, cur[1]+1});
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int r: dist[dst]) {
            res = Math.min(res, r);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int[][] constructGraph(int n, int[][] flights) {
        int[][] res = new int[n][n];
        for (int[] f: flights) {
            res[f[0]][f[1]] = f[2];
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/cheapest-flights-within-k-stops/solution/
     */
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        int[][] dist = new int[2][n];
        int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dist[0], INF);
        Arrays.fill(dist[1], INF);
        dist[0][src] = dist[1][src] = 0;

        for (int i = 0; i <= K; ++i)
            for (int[] edge: flights)
                dist[i&1][edge[1]] = Math.min(dist[i&1][edge[1]], dist[~i&1][edge[0]] + edge[2]);

        return dist[K&1][dst] < INF ? dist[K&1][dst] : -1;
    }


    /**
     * https://leetcode.com/problems/cheapest-flights-within-k-stops/solution/
     */
    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] flight: flights)
            graph[flight[0]][flight[1]] = flight[2];

        Map<Integer, Integer> best = new HashMap();

        Comparator<int[]> comp = (a, b) -> a[0] - b[0];
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(comp);
        pq.offer(new int[]{0, 0, src});

        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int cost = info[0], k = info[1], place = info[2];
            if (k > K+1 || cost > best.getOrDefault(k * 1000 + place, Integer.MAX_VALUE))
                continue;
            if (place == dst)
                return cost;

            for (int nei = 0; nei < n; ++nei) if (graph[place][nei] > 0) {
                int newcost = cost + graph[place][nei];
                if (newcost < best.getOrDefault((k+1) * 1000 + nei, Integer.MAX_VALUE)) {
                    pq.offer(new int[]{newcost, k+1, nei});
                    best.put((k+1) * 1000 + nei, newcost);
                }
            }
        }

        return -1;
    }


    /**
     * https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/128776/5-ms-AC-Java-Solution-based-on-Dijkstra's-Algorithm
     */
    private class City implements Comparable<City>{
        int id;
        int costFromSrc;
        int stopFromSrc;
        public City(int id, int costFromSrc, int stopFromSrc){
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.stopFromSrc = stopFromSrc;
        }
        public boolean equals(City c){
            if(c instanceof City)
                return this.id == c.id;
            return false;
        }
        public int compareTo(City c){
            return this.costFromSrc - c.costFromSrc;
        }
    }

    public int findCheapestPrice4(int n, int[][] flights, int src, int dst, int K) {
        int[][] srcToDst = new int[n][n];
        for(int i = 0; i < flights.length; i++)
            srcToDst[flights[i][0]][flights[i][1]] = flights[i][2]; 

        PriorityQueue<City> minHeap = new PriorityQueue();
        minHeap.offer(new City(src,0,0));

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);
        stop[src] = 0;

        while(!minHeap.isEmpty()){
            City curCity = minHeap.poll();
            if(curCity.id == dst) return curCity.costFromSrc;
            if(curCity.stopFromSrc == K + 1) continue;
            int[] nexts = srcToDst[curCity.id];
            for(int i = 0; i < n; i++){
                if(nexts[i] != 0){
                    int newCost = curCity.costFromSrc + nexts[i];
                    int newStop = curCity.stopFromSrc + 1;
                    if(newCost < cost[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        cost[i] = newCost;
                    }
                    else if(newStop < stop[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        stop[i] = newStop;
                    }
                }
            }
        }

        return cost[dst] == Integer.MAX_VALUE? -1:cost[dst];
    }


    /**
     * https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/163698/easy-java-Bellman-Ford
     */
    public int findCheapestPrice5(int n, int[][] flights, int src, int dst, int k) {
        int INF = 0x3F3F3F3F;
        int[] cost = new int[n];
        Arrays.fill(cost, INF);
        cost[src] = 0;
        int ans = cost[dst];
        for(int i = k; i >= 0; i--){
            int[] cur = new int[n];
            Arrays.fill(cur, INF);
            for(int[] flight : flights){
                cur[flight[1]] = Math.min(cur[flight[1]], cost[flight[0]] + flight[2]);
            }
            cost = cur;
            ans = Math.min(ans, cost[dst]);
        }
        return ans == INF ? -1 : ans;
    }


    private static int MAX = Integer.MAX_VALUE / 2;
    public int findCheapestPrice6(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[K+2][n];
        for (int[] row: dp) Arrays.fill(row, MAX);
        dp[0][src] = 0;
        for (int i=1; i<=K+1; i++) {
            for (int[] f: flights) {
                dp[i][f[1]] = Math.min(dp[i][f[1]], Math.min(dp[i-1][f[1]],
                                       dp[i-1][f[0]] + f[2]));
            }
        }
        return dp[K+1][dst] >= MAX ? -1 : dp[K+1][dst];
    }


    public int findCheapestPrice7(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[2][n];
        Arrays.fill(dp[0], MAX);
        Arrays.fill(dp[1], MAX);
        dp[0][src] = 0;
        int k = 1;
        while (k <= K+1) {
            for (int[] f: flights) {
                dp[k%2][f[1]] = Math.min(dp[k%2][f[1]], Math.min(dp[(k-1)%2][f[1]],
                                       dp[(k-1)%2][f[0]] + f[2]));
            }
            k++;
        }
        k--;
        return dp[k%2][dst] >= MAX ? -1 : dp[k%2][dst];
    }

}

