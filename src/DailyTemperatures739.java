/**
 * Given a list of daily temperatures, produce a list that, for each day in the
 * input, tells you how many days you would have to wait until a warmer
 * temperature. If there is no future day for which this is possible,
 * put 0 instead.
 * 
 * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73],
 * your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 * 
 * Note: The length of temperatures will be in the range [1, 30000]. Each
 * temperature will be an integer in the range [30, 100].
 */

public class DailyTemperatures739 {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return new int[0];
        int N = temperatures.length;
        int[] res  = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=N-1; i>=0; i--) {
            int curr = temperatures[i];
            while (!list.isEmpty() && list.getFirst() <= curr) {
                list.removeFirst();
            }
            if (!list.isEmpty()) {
                res[i] = map.get(list.getFirst()) - i;
            }
            list.addFirst(curr);
            map.put(curr, i);
        }
        return res;
    }


    public int[] dailyTemperatures2(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return new int[0];
        Stack<Integer> stack = new Stack<>();
        
        int N = temperatures.length;
        int[] res = new int[N];
        for (int i=N-1; i>=0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : (stack.peek() - i);
            stack.push(i);
        }
        
        return res;
    }


    public int[] dailyTemperatures3(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        int top = -1;
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int idx = stack[top--];
                ret[idx] = i - idx;
            }
            stack[++top] = i;
        }
        return ret;
    }

}
