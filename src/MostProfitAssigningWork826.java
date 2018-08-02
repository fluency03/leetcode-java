/**
 * We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i]
 * is the profit of the ith job. 
 * 
 * Now we have some workers. worker[i] is the ability of the ith worker, which
 * means that this worker can only complete a job with difficulty at most
 * worker[i]. 
 * 
 * Every worker can be assigned at most one job, but one job can be completed
 * multiple times.
 * 
 * For example, if 3 people attempt the same job that pays $1, then the total
 * profit will be $3.  If a worker cannot complete any job, his profit is $0.
 * 
 * What is the most profit we can make?
 * 
 * Example 1:
 * 
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100 
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get
 * profit of [20,20,30,30] seperately.
 * 
 * Notes:
 * 1 <= difficulty.length = profit.length <= 10000
 * 1 <= worker.length <= 10000
 * difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
 */

public class MostProfitAssigningWork826 {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int N = difficulty.length;
        Job[] jobs = new Job[N];
        for (int i=0; i<N; i++) jobs[i] = new Job(difficulty[i], profit[i]);
        Arrays.sort(jobs, (j1, j2) -> Integer.compare(j1.difficulty, j2.difficulty));
        Arrays.sort(worker);
        int res = 0;
        int max = 0;
        int i = 0;
        for (int cap: worker) {
            while (i < N && jobs[i].difficulty <= cap) {
                max = Math.max(max, jobs[i].profit);
                i++;
            }
            res += max;
        }
        return res;
    }
    
    class Job {
        int difficulty;
        int profit;
        Job (int d, int p) {
            difficulty = d;
            profit = p;
        }
    }

}
