/**
 * Median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Your job is to output the median array for each window in the original array.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 *
 *  Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 *
 *  Note:
 *  You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 */


import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class SlidingWindowMedian480 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int mid = k / 2;
        List<Integer> window = new ArrayList<>();
        for (int j = 0; j < k; j++) {
            window.add(nums[j]);
        }
        Collections.sort(window);

        if ((k & 1) == 0) {
            int lenNew = len - 2 * mid + 1;
            double[] returned = new double[lenNew];
            for (int i = 0; i < lenNew - 1; i++) {
                returned[i] = findMedianFromEven(window, mid);
                int idx = window.indexOf(nums[i]);
                int newValue = nums[i + k];
                updateWindow(window, newValue, idx, k);
            }
            returned[lenNew - 1] = findMedianFromEven(window, mid);
            return returned;
        } else {
            int lenNew = len - 2 * mid;
            double[] returned = new double[lenNew];
            for (int i = 0; i < lenNew - 1; i++) {
                returned[i] = findMedianFromOdd(window, mid);
                int idx = window.indexOf(nums[i]);
                int newValue = nums[i + k];
                updateWindow(window, newValue, idx, k);
            }
            returned[lenNew - 1] = findMedianFromOdd(window, mid);
            return returned;
        }
    }

    private double findMedianFromEven(List<Integer> window, int mid) {
        return ((long)window.get(mid - 1) + (long)window.get(mid)) / 2.0;
    }

    private double findMedianFromOdd(List<Integer> window, int mid) {
        return window.get(mid);
    }

    private void updateWindow(List<Integer> window, int newValue, int idx, int k) {
        while(0 <= idx && idx < k) {
            if (idx > 0 && window.get(idx - 1) > newValue) {
                window.set(idx, window.get(idx - 1));
                idx -= 1;
            } else if (idx < k - 1 && window.get(idx + 1) < newValue) {
                window.set(idx, window.get(idx + 1));
                idx += 1;
            } else {
                window.set(idx, newValue);
                return;
            }
        }
        window.set(idx, newValue);
    }



    public double[] medianSlidingWindow2(int[] nums, int k) {
        Comparator<Pair> comp = new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2) {
                int diff = Integer.compare(p1.value, p2.value);
                if (diff != 0) return diff;
                return Integer.compare(p1.index, p2.index);  
            }
        };
        TreeSet<Pair> left = new TreeSet<>(comp);
        TreeSet<Pair> right = new TreeSet<>(comp);
        boolean isOdd = k % 2 == 1;
        for (int i=0; i<k; i++) {
            addNew(i, nums[i], left, right);
        }

        int len = nums.length;
        double[] res = new double[len - k + 1];
        for (int i=0; i<=len-k; i++) {
            res[i] = left.size() == right.size() ? ((double) left.last().value + (double) right.first().value) / 2.0 : (double) left.last().value;
            if (i == len-k) break;
            Pair oldP = new Pair(i, nums[i]);
            boolean inLeft = left.remove(oldP);
            if (!inLeft) right.remove(oldP);
            addNew(i+k, nums[i+k], left, right);
        }
        return res;
    }
    
    private void addNew(int index, int value, TreeSet<Pair> left, TreeSet<Pair> right) {
        Pair p = new Pair(index, value);
        if (right.size() != 0 && value > right.first().value) {
            right.add(p);
        } else {
            left.add(p);
        }
        balance(left, right);
    }
    
    private void balance(TreeSet<Pair> left, TreeSet<Pair> right) {
        while (left.size() > right.size() + 1) {
            Pair topLeft = left.pollLast();
            right.add(topLeft);
        }
        while (left.size() < right.size()) {
            Pair bottomRight = right.pollFirst();
            left.add(bottomRight);
        }
    }

    class Pair {
        int index;
        int value;
        Pair(int i, int v) {
            index = i;
            value = v;
        }
    }


    public double[] medianSlidingWindow3(int[] nums, int k) {
        Comparator<Integer> comp = (i1, i2) -> Integer.compare(i2, i1);
        PriorityQueue<Integer> left = new PriorityQueue<>(comp);
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for (int i=0; i<k; i++) {
            if (left.isEmpty() || nums[i] <= left.peek()) {
                left.add(nums[i]);
            } else {
                right.add(nums[i]);
            }
            if (left.size() < right.size()) {
                left.add(right.poll());
            } else if (left.size() > right.size() + 1) {
                right.add(left.poll());
            }
        }

        int N = nums.length;
        double[] res = new double[N-k+1];
        for (int i=k; i<=N; i++) {
            if (left.size() == right.size() + 1) {
                res[i-k] = left.peek() * 1.0;
            } else {
                res[i-k] = ((long)left.peek() + (long)right.peek()) / 2.0;
            }
            if (i == N) break; 
            int toPop = nums[i-k];
            int toAdd = nums[i];
            
            if (toAdd <= left.peek()) {
                left.add(toAdd);
            } else {
                right.add(toAdd);
            }
            
            if (left.peek() >= toPop) {
                left.remove(toPop);
            } else {
                right.remove(toPop);
            }

            if (left.size() < right.size()) {
                left.add(right.poll());
            } else if (left.size() > right.size() + 1) {
                right.add(left.poll());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SlidingWindowMedian480 swm = new SlidingWindowMedian480();

        int [] nums = {1, 3, -1, -3, 5,  3,  6,  7};
        int [] twos = {2147483647, 2147483647};

        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums, 3)));
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums, 4)));
        System.out.println(Arrays.toString(swm.medianSlidingWindow(twos, 2)));

    }
}
