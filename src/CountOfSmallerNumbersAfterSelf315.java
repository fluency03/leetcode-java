/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller
 * elements to the right of nums[i].
 *
 * Example:
 *
 * Given nums = [5, 2, 6, 1]
 *
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * Return the array [2, 1, 1, 0].
 *
 */


public class CountOfSmallerNumbersAfterSelf315 {
    // brutal force method, Time Limit Exceeded
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>(nums.length);
        for (int i=0; i<nums.length; i++) {
            int now = nums[i];
            int count = 0;
            for (int j=i+1; j<nums.length; j++) {
                if (nums[j] < now) {
                    count++;
                }
            }
            res.set(i, count);
        }
        return res;
    }

    // another method, Time Limit Exceeded
    public List<Integer> countSmaller2(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<Integer>();
        }
        LinkedList<Integer> res = new LinkedList<>();
        res.add(0);
        ListNode head = new ListNode(nums[nums.length-1]);
        for (int i=nums.length-2; i>=0; i--) {
            ListNode newNode = new ListNode(nums[i]);
            int count = 0;
            ListNode temp = head;
            if (nums[i] <= temp.val) {
                newNode.next = temp;
                head = newNode;
                res.addFirst(count);
                continue;
            }
            while (temp.next != null && nums[i] > temp.next.val) {
                temp = temp.next;
                count++;
            }
            if (temp.next == null) {
                temp.next = newNode;
            } else {
                newNode.next = temp.next;
                temp.next = newNode;
            }
            count++;
            res.addFirst(count);
        }
        return res;
    }


    public List<Integer> countSmaller3(int[] nums) {

        Integer[] count = new Integer[nums.length];

        int[][] arr = new int[nums.length][2];
        for (int i=0; i<nums.length; i++) {
            arr[i][0] = i;
            arr[i][1] = nums[i];
            count[i] = 0;
        }

        mergeSort(arr, count, 0, nums.length-1);

        return Arrays.asList(count);
    }

    private void mergeSort(int[][] arr, Integer[] count, int l, int r) {
        if (l >= r) return;

        int m = (l+r)/2;
        mergeSort(arr, count, l, m);
        mergeSort(arr, count, m+1, r);

        merge(arr, count, l, m, r);
    }

    private void merge(int[][] arr, Integer[] count, int l, int m, int r) {
        int len1 = m - l + 1;
        int len2 = r - m;

        int[][] L = new int [len1][2];
        int[][] R = new int [len2][2];

        for (int i=0; i < len1; i++)
            L[i] = arr[l + i];
        for (int j=0; j < len2; j++)
            R[j] = arr[m + 1 + j];

        int i = 0;
        int j = 0;
        int k = l;
        while (i < len1 && j < len2) {
            if (L[i][1] <= R[j][1]) {
                arr[k] = L[i];
                count[L[i][0]] += j;
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < len2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    /**
     * https://discuss.leetcode.com/topic/31405/9ms-short-java-bst-solution-get-answer-when-building-bst
     */
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(nums[i], root, ans, i, 0);
        }
        return Arrays.asList(ans);
    }
    private Node insert(int num, Node node, Integer[] ans, int i, int preSum) {
        if (node == null) {
            node = new Node(num, 0);
            ans[i] = preSum;
        } else if (node.val == num) {
            node.dup++;
            ans[i] = preSum + node.sum;
        } else if (node.val > num) {
            node.sum++;
            node.left = insert(num, node.left, ans, i, preSum);
        } else {
            node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);
        }
        return node;
    }

    class Node {
        Node left, right;
        int val, sum, dup = 1;
        public Node(int v, int s) {
            val = v;
            sum = s;
        }
    }

    /**
     * https://discuss.leetcode.com/topic/31173/my-simple-ac-java-binary-search-code
     */
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        List<Integer> sorted = new ArrayList<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(sorted, nums[i]);
            ans[i] = index;
            sorted.add(index, nums[i]);
        }
        return Arrays.asList(ans);
    }
    private int findIndex(List<Integer> sorted, int target) {
        if (sorted.size() == 0) return 0;
        int start = 0;
        int end = sorted.size() - 1;
        if (sorted.get(end) < target) return end + 1;
        if (sorted.get(start) >= target) return 0;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (sorted.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (sorted.get(start) >= target) return start;
        return end;
    }

}
