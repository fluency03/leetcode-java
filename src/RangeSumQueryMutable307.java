/**
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * The update(i, val) function modifies nums by updating the element at
 * index i to val.
 * 
 * Example:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 
 * Note:
 * 
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is
 * distributed evenly.
 */


public class RangeSumQueryMutable307 {
    class NumArray {
        private int[] sum;
        private int[] nums;
        
        public NumArray(int[] nums) {
            this.nums = nums;
            sum = new int[nums.length+1];
            for (int i=1; i<=nums.length; i++) {
                sum[i] = sum[i-1] + nums[i-1];
            }
        }
        
        public void update(int i, int val) {
            int diff = val - nums[i];
            for (int j=i+1; j<sum.length; j++) {
                sum[j] += diff;
            }
            nums[i] = val;
        }
        
        public int sumRange(int i, int j) {
            return sum[j+1] - sum[i];
        }
    }


    class NumArray2 {
        private Node root;
        
        public NumArray(int[] nums) {
            this.root = constructTree(nums);
        }
        
        private Node constructTree(int[] nums) {
            return constructTree(nums, 0, nums.length-1); 
        }
        
        private Node constructTree(int[] nums, int l, int r) {
            if (l > r) return null;
            Node res = new Node(l, r, nums[l]);
            if (l == r) return res;
            
            int mid = (l + r) / 2;
            Node left = constructTree(nums, l, mid);
            Node right = constructTree(nums, mid+1, r); 
            res.left = left;
            res.right = right;
            res.sum = left.sum + right.sum;
            return res;
        }
        
        public void update(int i, int val) {
            update(this.root, i, val);
        }
        
        public void update(Node root, int i, int val) {
            if (root.l == root.r && root.l == i) {
                root.sum = val;
                return;
            }
            int mid = (root.l + root.r) / 2;
            
            if (i <= mid) {
                update(root.left, i, val);
            } else {
                update(root.right, i, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
        
        public int sumRange(int i, int j) {
            return sumRange(this.root, i, j);
        }
        
        private int sumRange(Node root, int i, int j) {
            if (root.l == i && root.r == j) {
                return root.sum;
            }
            int mid = (root.l + root.r) / 2;
            
            if (mid < i) {
                return sumRange(root.right, i, j);
            } else if (mid >= j) {
                return sumRange(root.left, i, j);
            } else {
                return sumRange(root.left, i, mid) + sumRange(root.right, mid+1, j);
            }
        }
        
        class Node {
            Node left;
            Node right;
            int l;
            int r;
            int sum;
            Node(int l, int r, int s) {
                this.l = l;
                this.r = r;
                sum = s;
            }
        }
        
    }


    /**
     * https://leetcode.com/problems/range-sum-query-mutable/solution/
     */
    class NumArray3 {
        int[] tree;
        int n;
        public NumArray(int[] nums) {
            if (nums.length > 0) {
                n = nums.length;
                tree = new int[n * 2];
                buildTree(nums);
            }
        }
        private void buildTree(int[] nums) {
            for (int i = n, j = 0;  i < 2 * n; i++,  j++)
                tree[i] = nums[j];
            for (int i = n - 1; i > 0; --i)
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        public void update(int pos, int val) {
            pos += n;
            tree[pos] = val;
            while (pos > 0) {
                int left = pos;
                int right = pos;
                if (pos % 2 == 0) {
                    right = pos + 1;
                } else {
                    left = pos - 1;
                }
                // parent is updated after child is updated
                tree[pos / 2] = tree[left] + tree[right];
                pos /= 2;
            }
        }

        public int sumRange(int l, int r) {
            // get leaf with value 'l'
            l += n;
            // get leaf with value 'r'
            r += n;
            int sum = 0;
            while (l <= r) {
                if ((l % 2) == 1) {
                  sum += tree[l];
                  l++;
                }
                if ((r % 2) == 0) {
                  sum += tree[r];
                  r--;
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }
    }


    class NumArray4 {

        private int[] tree;
        private int L;
        
        public NumArray(int[] nums) {
            tree = new int[nums.length * 3];
            L = nums.length;
            constructTree(nums);
        }
        
        private void constructTree(int[] nums) {
            constructTree(nums, 0, nums.length-1, 0); 
        }
        
        private void constructTree(int[] nums, int l, int r, int i) {
            if (l > r) return;
            if (l == r) {
                tree[i] = nums[l];
                return;
            }
            
            int mid = (l + r) / 2;
            constructTree(nums, l, mid, left(i));
            constructTree(nums, mid+1, r, right(i));
            tree[i] = tree[left(i)] + tree[right(i)];
        }
        
        public void update(int i, int val) {
            update(0, L-1, i, val, 0);
        }
        
        public void update(int l, int r, int i, int val, int n) {
            if (l == r && l == i) {
                tree[n] = val;
                return;
            }
            int mid = (l + r) / 2;
            
            if (i <= mid) {
                update(l, mid, i, val, left(n));
            } else {
                update(mid + 1, r, i, val, right(n));
            }
            tree[n] = tree[left(n)] + tree[right(n)];
        }
        
        public int sumRange(int i, int j) {
            return sumRange(0, L-1, i, j, 0);
        }
        
        private int sumRange(int l, int r, int i, int j, int n) {
            if (l == i && r == j) {
                return tree[n];
            }
            int mid = (l + r) / 2;
            
            if (mid < i) {
                return sumRange(mid+1, r, i, j, right(n));
            } else if (mid >= j) {
                return sumRange(l, mid, i, j, left(n));
            } else {
                return sumRange(l, mid, i, mid, left(n)) + sumRange(mid+1, r, mid+1, j, right(n));
            }
        }
        
        private int left(int i) {
            return 2 * i + 1;
        }
        private int right(int i) {
            return 2 * i + 2;
        }
        
    }


    class NumArray5 {

        private int[] nums;
        private int[] tree;
        private int L;
        
        public NumArray(int[] nums) {
            this.nums = nums;
            this.L = nums.length;
            constructTree(nums);
        }
        
        private void constructTree(int[] nums) {
            this.tree = new int[nums.length + 1];
            for (int i=0; i<L; i++) {
                updateDiff(i, nums[i]);
            }
        }
    
        private void updateDiff(int i, int val) {
            int idx = i + 1;
            while (idx <= L) {
                this.tree[idx] += val;
                idx += idx & -idx;
            }
        }
        
        public void update(int i, int val) {
            int diff = val - this.nums[i];
            this.nums[i] = val;
            updateDiff(i, diff);
        }
    
        public int sumRange(int i, int j) {
            return getSum(j) - getSum(i-1);
        }
        
        public int getSum(int i) {
            int res = 0;
            int idx = i + 1;
            while (idx > 0) {
                res += this.tree[idx];
                idx -= idx & -idx;
            }
            return res;
        }
    
    }


/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

}

