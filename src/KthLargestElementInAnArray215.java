/**
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 *
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */


public class KthLargestElementInAnArray215 {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * https://discuss.leetcode.com/topic/14597/solution-explained
     */
    public int findKthLargest2(int[] nums, int k) {

        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * https://discuss.leetcode.com/topic/14597/solution-explained
     */
    public int findKthLargest3(int[] nums, int k) {

        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }

    /**
     * https://discuss.leetcode.com/topic/14597/solution-explained
     */
    public int findKthLargest4(int[] nums, int k) {

        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private void shuffle(int a[]) {
        final Random random = new Random();
        for(int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            exch(a, ind, r);
        }
    }
    

    public int findKthLargest5(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n: nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        if (min == max) return min;

        while (min < max) {
            int mid = min + (max - min) / 2;
            int c = count(nums, mid);
            if (c >= k) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }

    private int count(int[] nums, int mid) {
        int res = 0;
        for (int n: nums) {
            if (n > mid) res++;
        }
        return res;
    }

    public int findKthLargest6(int[] nums, int k) {
        int len = nums.length;
        
        int lo = 0;
        int hi = len - 1;
        while (lo < hi) {
            int p = partition2(nums, lo, hi);
            if (p == (len - k)) return nums[p];
            if (p > (len - k)) {
                hi = p - 1;
            } else {
                lo = p + 1;
            }
        }
        
        return nums[lo];
    }

    private int partition2(int[] nums, int lo, int hi) {
        if (lo == hi) return lo;
        
        int slow = lo+1;
        int fast = lo+1;
        while (fast <= hi) {
            if (nums[fast] < nums[lo]) {
                swap(nums, slow, fast);
                slow++;
            }
            fast++;
        }
        swap(nums, lo, slow-1);
        return slow-1;
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i == j) return ;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
