/**
 * A Range Module is a module that tracks ranges of numbers. Your task is to
 * design and implement the following interfaces in an efficient manner.
 * 
 * - addRange(int left, int right) Adds the half-open interval [left, right),
 * tracking every real number in that interval. Adding an interval that
 * partially overlaps with currently tracked numbers should add any numbers in
 * the interval [left, right) that are not already tracked.
 * - queryRange(int left, int right) Returns true if and only if every real
 * number in the interval [left, right) is currently being tracked.
 * - removeRange(int left, int right) Stops tracking every real number
 * currently being tracked in the interval [left, right).
 * 
 * Example 1:
 * addRange(10, 20): null
 * removeRange(14, 16): null
 * queryRange(10, 14): true (Every number in [10, 14) is being tracked)
 * queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are
 * not being tracked)
 * queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked,
 * despite the remove operation)
 * 
 * Note:
 * A half open interval [left, right) denotes all real numbers left <= x < right.
 * 0 < left < right < 10^9 in all calls to addRange, queryRange, removeRange.
 * The total number of calls to addRange in a single test case is at most 1000.
 * The total number of calls to queryRange in a single test case is at most 5000.
 * The total number of calls to removeRange in a single test case is at most 1000.
 */


public class RangeModule715 {
    // class RangeModule {
    //     private TreeNode root;
        
    //     public RangeModule() {
    //     }
        
    //     public void addRange(int left, int right) {
    //         this.root = addRange(root, left, right);
    //     }
        
    //     public TreeNode addRange(TreeNode root, int left, int right) {
    //         if (left >= right) return root;
    //         if (root == null) return new TreeNode(left, right);
    //         if (left >= root.left && right <= root.right) return root;

    //         if (root.left >= right) {
    //             root.leftNode = addRange(root.leftNode, left, right);
    //         } else if (root.right <= left) {
    //             root.rightNode = addRange(root.rightNode, left, right);
    //         } else {
    //             if (left < root.left) {
    //                 root.leftNode = addRange(root.leftNode, left, root.left);
    //             }
    //             if (right > root.right) {
    //                 root.rightNode = addRange(root.rightNode, root.right, right);
    //             }
    //         }
    //         return root;
    //     }
        
    //     public boolean queryRange(int left, int right) {
    //         return queryRange(this.root, left, right);
    //     }
        
    //     public boolean queryRange(TreeNode root, int left, int right) {
    //         if (root == null) return false;
    //         if (left >= root.left && right <= root.right) return true;

    //         if (root.left >= right) return queryRange(root.leftNode, left, right);
    //         if (root.right <= left) return queryRange(root.rightNode, left, right);

    //         if (left < root.left && !queryRange(root.leftNode, left, root.left)) return false;
    //         if (right > root.right && !queryRange(root.rightNode, root.right, right)) return false; 
    //         return true;
    //     }
        
    //     public void removeRange(int left, int right) {
    //         this.root = removeRange(this.root, left, right);
    //     }

    //     public TreeNode removeRange(TreeNode root, int left, int right) {
    //         if (root == null || left >= right) return root;
            
    //         if (root.left >= right) {
    //             root.leftNode = removeRange(root.leftNode, left, right);
    //         } else if (root.right <= left) {
    //             root.rightNode = removeRange(root.rightNode, left, right);
    //         } else if (left > root.left && right < root.right) {
    //             TreeNode tmp = new TreeNode(right, root.right);
    //             tmp.rightNode = root.rightNode;
    //             root.rightNode = tmp;
    //             root.right = left;
    //         } else if (left <= root.left && right >= root.right) {
    //             root.right = root.left;
    //             root.leftNode = removeRange(root.leftNode, left, root.left);
    //             root.rightNode = removeRange(root.rightNode, root.right, right);
    //         } else if (left <= root.left) {
    //             root.leftNode = removeRange(root.leftNode, left, root.left);
    //             root.left = right;
    //         } else {
    //             root.rightNode = removeRange(root.rightNode, root.right, right);
    //             root.right = left;
    //         }
    //         return root;
    //     }

    //     class TreeNode {
    //         TreeNode leftNode;
    //         TreeNode rightNode;
    //         int left;
    //         int right;
    //         TreeNode(int left, int right) {
    //             this.left = left;
    //             this.right = right;
    //         }
    //     }
      
    // }


    class RangeModule {
        private TreeSet<Range> ranges = new TreeSet<>((r1, r2) -> Integer.compare(r1.left, r2.left));
        public RangeModule() {
        }
        
        public void addRange(int left, int right) {
            Range newRange = new Range(left, right);
            Range floor = ranges.floor(newRange);
            if (floor != null && floor.right >= left) {
                if (floor.right >= right) return;
                newRange.left = floor.left;
                newRange.right = Math.max(floor.right, right);
                ranges.remove(floor);
            }
            
            while (true) {
                Range ceiling = ranges.ceiling(newRange);
                if (ceiling == null || ceiling.left > newRange.right) break;
                newRange.right = Math.max(ceiling.right, newRange.right);
                ranges.remove(ceiling);
            }
            ranges.add(newRange);
        }
    
        public boolean queryRange(int left, int right) {
            Range floor = ranges.floor(new Range(left, right));
            if (floor == null) return false;
            return floor.right >= right;
        }
        
        public void removeRange(int left, int right) {
            Range toBeRemoved = new Range(left, right);
            Range floor = ranges.floor(toBeRemoved);
            if (floor != null && floor.right >= left) {
                int fRight = floor.right;
                floor.right = left;
                if (fRight == right) return;
                if (fRight > right) {
                    Range add = new Range(right, fRight);
                    ranges.add(add);
                    return;
                }
            }
    
            while (true) {
                Range ceiling = ranges.ceiling(toBeRemoved);
                if (ceiling == null || ceiling.left >= toBeRemoved.right) break;
                ranges.remove(ceiling);
                if (ceiling.right > toBeRemoved.right) {
                    ceiling.left = toBeRemoved.right;
                    ranges.add(ceiling);
                    break;
                }
            }
        }
    
        class Range {
            int left;
            int right;
            Range (int l, int r) {
                left = l;
                right = r;
            }
        }
    }


/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */

}
