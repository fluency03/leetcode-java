/**
 * https://www.topcoder.com/community/competitive-programming/tutorials/binary-indexed-trees/
 * https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
 */

public class BinaryIndexedTree {
    private int[] tree;
    private int N;

    public BinaryIndexedTree(int[] nums) {
        if (nums == null) return;
        this.N = nums.length;
        this.tree = new int[N+1];
        constructBIT(nums);
    }

    public BinaryIndexedTree(int N) {
        this.N = N;
        this.tree = new int[N+1];
    }

    private void constructBIT(int[] nums) {
        int N = nums.length;
        for (int i=0; i<N; i++) {
            update(i, nums[i]);
        }
    }

    public void update(int i, int delta) {
        int k = i + 1;
        while (k <= this.N) {
            this.tree[k] += delta;
            k += lowBit(k);
        }
    }

    public int query(int i) {
        int k = i + 1;
        int res = 0;
        while (k > 0) {
            res += this.tree[k];
            k -= lowBit(k);
        }
        return res;
    }

    private int lowBit(int x) {
        return x & (-x);
    }

}
