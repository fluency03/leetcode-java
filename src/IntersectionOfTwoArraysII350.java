/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 *
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 *
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such
 * that you cannot load all elements into the memory at once?
 *
 */


public class IntersectionOfTwoArraysII350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[0];
        Map<Integer, Integer> m1 = new HashMap<>();
        for (int i=0; i<nums1.length; i++) {
            m1.put(nums1[i], m1.getOrDefault(nums1[i], 0)+1);
        }

        Map<Integer, Integer> m2 = new HashMap<>();
        for (int i=0; i<nums2.length; i++) {
            m2.put(nums2[i], m2.getOrDefault(nums2[i], 0)+1);
        }

        List<Integer> res = new ArrayList<>();
        if (m1.size() > m2.size()) {
            Map<Integer, Integer> temp = m1;
            m1 = m2;
            m2 = temp;
        }

        for (Map.Entry<Integer, Integer> e: m1.entrySet()) {
            if (m2.containsKey(e.getKey())) {
                int minVal = Math.min(e.getValue(), m2.get(e.getKey()));
                while (minVal > 0) {
                    res.add(e.getKey());
                    minVal--;
                }
            }
        }

        int[] arr = new int[res.size()];
        for (int i=0; i<res.size(); i++) arr[i] = res.get(i);

        return arr;
    }


    // solution to 3rd follow-up
    public int[] intersect2(int[] nums1, int[] nums2) {
        int len = Math.min(nums1.length, nums2.length);
        int[] arr = new int[len];
        if (len == 0) return arr;

        Map<Integer, Integer> m1 = new HashMap<>();
        for (int i=0; i<nums1.length; i++) {
            m1.put(nums1[i], m1.getOrDefault(nums1[i], 0)+1);
        }

        int size = 0;
        for (int i=0; i<nums2.length; i++) {
            if (m1.containsKey(nums2[i]) && m1.get(nums2[i]) > 0) {
                m1.put(nums2[i], m1.get(nums2[i])-1);
                arr[size] = nums2[i];
                size++;
            }
        }

        return Arrays.copyOfRange(arr, 0, size);
    }


    public int[] intersect3(int[] nums1, int[] nums2) {
        int len = Math.min(nums1.length, nums2.length);
        int[] arr = new int[len];
        if (len == 0) return arr;

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;
        int j = 0;
        int size = 0;
        while (i < nums1.length && j < nums2.length) {
            int n1 = nums1[i];
            int n2 = nums2[j];
            if (n1 == n2) {
                arr[size] = n1;
                i++;
                j++;
                size++;
            } else if (n1 < n2) {
                do {
                    i++;
                } while (i < nums1.length && nums1[i] == n1);
            } else {
                do {
                    j++;
                } while (j < nums2.length && nums2[j] == n2);
            }

        }

        return Arrays.copyOfRange(arr, 0, size);
    }

}
