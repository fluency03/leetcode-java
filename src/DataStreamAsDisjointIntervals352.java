/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a list of disjoint intervals.
 * 
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ...,
 * then the summary will be:
 * 
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * 
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are
 * small compared to the data stream's size?
 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class DataStreamAsDisjointIntervals352 {
    class SummaryRanges {
        private TreeMap<Integer, Integer> ranges = new TreeMap<>();

        /** Initialize your data structure here. */
        public SummaryRanges() {
        }

        public void addNum(int val) {
            Map.Entry<Integer, Integer> ceiling = ranges.ceilingEntry(val);
            Map.Entry<Integer, Integer> floor = ranges.floorEntry(val);
            if (floor == null && ceiling == null) {
                ranges.put(val, val);
            } else if (floor == null) {
                if (val + 1 < ceiling.getKey()) {
                    ranges.put(val, val);
                } else if (val + 1 == ceiling.getKey()) {
                    ranges.remove(ceiling.getKey());
                    ranges.put(val, ceiling.getValue());
                }
            } else if (ceiling == null) {
                if (val > floor.getValue() + 1) {
                    ranges.put(val, val);
                } else if (val == floor.getValue() + 1) {
                    ranges.put(floor.getKey(), val);
                }
            } else {
                if (val <= floor.getValue() || val >= ceiling.getKey()) return;
                if (val > floor.getValue() + 1 && val + 1 < ceiling.getKey()) {
                    ranges.put(val, val);
                } else if (val == floor.getValue() + 1 && val + 1 == ceiling.getKey()) {
                    ranges.remove(ceiling.getKey());
                    ranges.put(floor.getKey(), ceiling.getValue());
                } else if (val == floor.getValue() + 1) {
                    ranges.put(floor.getKey(), val);
                } else if (val + 1 == ceiling.getKey()) {
                    ranges.remove(ceiling.getKey());
                    ranges.put(val, ceiling.getValue());
                }
            }
        }

        public List<Interval> getIntervals() {
            List<Interval> res = new ArrayList<>();
            for (Map.Entry<Integer, Integer> e: ranges.entrySet()) {
                res.add(new Interval(e.getKey(), e.getValue()));
            }
            return res;
        }
    }


    class SummaryRanges2 {
        private Node root;

        /** Initialize your data structure here. */
        public SummaryRanges2() {
        }

        public void addNum(int val) {
            if (this.root == null) {
                this.root = addNode(this.root, val);
                return;
            }

            Node c = findNode(this.root, val);
            if (c != null) return;
            Node l = findNode(this.root, val - 1);
            Node r = findNode(this.root, val + 1);
            
            if (l == null && r == null) {
                this.root = addNode(this.root, val);
            } else if (l == null) {
                r.inv.start = val;
            } else if (r == null) {
                l.inv.end = val;
            } else {
                int newEnd = r.inv.end;
                this.root = remove(this.root, r.inv);
                l.inv.end = newEnd;
            }
        }

        private Node remove(Node node, Interval inv) {
            if (node == null) return null;
            if (inv.end < node.inv.start) {
                node.left = remove(node.left, inv);;
            } else if (inv.start > node.inv.end) {
                node.right = remove(node.right, inv);
            } else {
                if (node.left == null) return node.right;
                else if (node.right == null) return node.left;
                node.inv = findMin(node.right).inv;
                node.right = remove(node.right, node.inv);
            }
            return node;
        }

        private Node findMin(Node node) {
            if (node == null || node.left == null) return node;
            return findMin(node.left);
        }

        private Node findNode(Node node, int val) {
            if (node == null || node.inv.start <= val && val <= node.inv.end) return node;
            if (val < node.inv.start) return findNode(node.left, val);
            return findNode(node.right, val);
        }

        private Node addNode(Node node, int val) {
            if (node == null) return new Node(new Interval(val, val));
            if (node.inv.start <= val && val <= node.inv.end) return node;
            if (val < node.inv.start) node.left = addNode(node.left, val);
            else if (node.inv.end < val) node.right = addNode(node.right, val);
            return node;
        }

        public List<Interval> getIntervals() {
            List<Interval> res = new ArrayList<>();
            inorder(this.root, res);
            return res;
        }

        private void inorder(Node node, List<Interval> res) {
            if (node == null) return;
            inorder(node.left, res);
            res.add(node.inv);
            inorder(node.right, res);
        }
    }

    class Node {
        Node left;
        Node right;
        Interval inv;
        Node(Interval inv) {
            this.inv = inv;
        }
    }


/**
* Your SummaryRanges object will be instantiated and called as such:
* SummaryRanges obj = new SummaryRanges();
* obj.addNum(val);
* List<Interval> param_2 = obj.getIntervals();
*/

}
