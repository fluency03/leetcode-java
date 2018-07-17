/**
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * 
 * For example,
 * [2,3,4], the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * 
 * Example:
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3) 
 * findMedian() -> 2
 */


public class FindMedianFromDataStream295 {
    class MedianFinder {
        private PriorityQueue<Integer> maxQ = new PriorityQueue<>(10, (i1, i2) -> Integer.compare(i2, i1));
        private PriorityQueue<Integer> minQ = new PriorityQueue<>();
        
        /** initialize your data structure here. */
        public MedianFinder() {
            
        }
        
        public void addNum(int num) {
            if (maxQ.size() == 0 && minQ.size() == 0) {
                maxQ.add(num);
            } else if (maxQ.size() == 0) {
                if (minQ.peek() < num) {
                    minQ.add(num);
                } else {
                    maxQ.add(num);
                }
            } else {
                if (maxQ.peek() > num) {
                    maxQ.add(num);
                } else {
                    minQ.add(num);
                }
            }
            while (maxQ.size() != 0 && maxQ.size() > minQ.size()) {
                minQ.add(maxQ.poll());
            }
            while (minQ.size() != 0 && maxQ.size() < minQ.size()) {
                maxQ.add(minQ.poll());
            }
        }
      
        public double findMedian() {
            if (maxQ.size() == minQ.size()) {
                return (maxQ.peek() + minQ.peek()) * 1.0 / 2;
            } else if (maxQ.size() > minQ.size()) {
                return maxQ.peek();
            } else {
                return minQ.peek();
            }
        }
    }
    

    class MedianFinder2 {
        private PriorityQueue<Integer> maxQ = new PriorityQueue<>(10, (i1, i2) -> Integer.compare(i2, i1));
        private PriorityQueue<Integer> minQ = new PriorityQueue<>();
        
        /** initialize your data structure here. */
        public MedianFinder() {
            
        }
        
        // slower than previous solution MedianFinder, but code is clearner
        public void addNum(int num) {
            maxQ.add(num);
            minQ.add(maxQ.pop());
            while (minQ.size() != 0 && maxQ.size() < minQ.size()) {
                maxQ.add(minQ.poll());
            }
        }
      
        public double findMedian() {
            if (maxQ.size() == minQ.size()) {
                return (maxQ.peek() + minQ.peek()) * 1.0 / 2;
            } else if (maxQ.size() > minQ.size()) {
                return maxQ.peek();
            } else {
                return minQ.peek();
            }
        }
    }


    /**
     * https://leetcode.com/problems/find-median-from-data-stream/discuss/74119/18ms-beats-100-Java-Solution-with-BST
     */
    class MedianFinder3 {
        class TreeNode{
            int val;
            TreeNode parent,left,right;
            TreeNode(int val, TreeNode p){
                this.val=val;
                this.parent=p;
                left=null;
                right=null;
            }
            void add(int num){
                if(num>=val){
                    if(right==null)
                        right=new TreeNode(num,this);
                    else
                        right.add(num);
                }else{
                    if(left==null)
                        left=new TreeNode(num,this);
                    else
                        left.add(num);
                }
            }
            TreeNode next(){
                TreeNode ret;
                if(right!=null){
                    ret=right;
                    while(ret.left!=null)
                        ret=ret.left;
                }else{
                    ret=this;
                    while(ret.parent.right==ret)
                        ret=ret.parent;
                    ret=ret.parent;
                }
                return ret;
            }
            TreeNode prev(){
                TreeNode ret;
                if(left!=null){
                    ret=left;
                    while(ret.right!=null)
                        ret=ret.right;
                }else{
                    ret=this;
                    while(ret.parent.left==ret)
                        ret=ret.parent;
                    ret=ret.parent;
                }
                return ret;
            }
        }
        int n;
        TreeNode root, curr;
        // Adds a number into the data structure.
        public void addNum(int num) {
            if(root==null){
                root = new TreeNode(num,null);
                curr=root;
                n=1;
            }else{
                root.add(num);
                n++;
                if(n%2==1){
                    if(curr.val<=num)
                        curr=curr.next();
                }else
                    if(curr.val>num)
                        curr=curr.prev();
            }
        }
    
        // Returns the median of current data stream
        public double findMedian() {
            if(n%2==0){
                return ((double)curr.next().val+curr.val)/2;
            }else
                return curr.val;
        }
    };


    class MedianFinder4 {
        private PriorityQueue<Integer> left;
        private PriorityQueue<Integer> right;
        
        /** initialize your data structure here. */
        public MedianFinder() {
            left = new PriorityQueue<>((i1, i2) -> Integer.compare(i2, i1));
            right = new PriorityQueue<>((i1, i2) -> Integer.compare(i1, i2));
        }
        
        public void addNum(int num) {
            if (right.size() != 0 && right.peek() < num) {
                right.add(num);
                if (left.size() < right.size()) {
                    left.add(right.poll());
                }
            } else { 
                left.add(num);
                if (left.size() > right.size() + 1) {
                    right.add(left.poll());
                }
            }
    
        }
        
        public double findMedian() {
            if (left.size() == right.size()) {
                return ((double) left.peek() + (double) right.peek()) / 2.0;
            } else {
                return (double) left.peek();
            }
            
        }
    }


/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

}


