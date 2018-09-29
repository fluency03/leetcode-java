/**
 * Given a char array representing tasks CPU need to do. It contains capital
 * letters A to Z where different letters represent different tasks.Tasks could
 * be done without original order. Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two
 * same tasks, there must be at least n intervals that CPU are doing different
 * tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish
 * all the given tasks.
 *
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 *
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * Note:
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 *
 */

/**
 *  Note that the names of the tasks are irrelevant for obtaining the solution
 *  of the given problem. The time taken for the tasks to be finished is only
 *  dependent on the number of instances of each task and not on the names of
 *  tasks.
 */


public class TaskScheduler621 {
    /**
     * https://leetcode.com/problems/task-scheduler/solution/
     */
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);

        int maxVal = map[25] - 1;
        int idles = maxVal * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idles -= Math.min(map[i], maxVal);
        }
        return idles > 0 ? idles + tasks.length : tasks.length;
    }

    /**
     * https://discuss.leetcode.com/topic/92852/concise-java-solution-o-n-time-o-26-space
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }


    /**
     * https://leetcode.com/problems/task-scheduler/solution/
     */
    public int leastInterval3(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }


    public int leastInterval4(char[] tasks, int n) {
        int N = tasks.length;
        int[] count = new int[26];
        int max = -1;
        int maxCount = -1;
        for (char ch: tasks) {
            count[ch-'A']++;
            if (count[ch-'A'] == max) {
                maxCount++;
            } else if (count[ch-'A'] > max) {
                max = count[ch-'A'];
                maxCount = 1;
            }
        }
        return Math.max((n+1) * (max-1) + maxCount, N);
    }


    /**
     * https://leetcode.com/problems/task-scheduler/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation
     */
    public int leastInterval5(char[] tasks, int n) {
        int[] counter = new int[26];
        int max = 0;
        int maxCount = 0;
        for(char task : tasks) {
            counter[task - 'A']++;
            if(max == counter[task - 'A']) {
                maxCount++;
            }
            else if(max < counter[task - 'A']) {
                max = counter[task - 'A'];
                maxCount = 1;
            }
        }
        int partCount = max - 1;
        int partLength = n - (maxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - max * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);
        
        return tasks.length + idles;
    }

}
