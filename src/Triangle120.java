/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step
 * you may move to adjacent numbers on the row below.
 * 
 * For example, given the following triangle
 * 
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where
 * n is the total number of rows in the triangle.
 */

public class Triangle120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int size = triangle.size();
        List<Integer> sums = triangle.get(size - 1);
        int i = size - 2;
        while (i >= 0) {
            List<Integer> curr = triangle.get(i);
            int level = curr.size();
            for (int j=0; j<level; j++) {
                curr.set(j, curr.get(j) + Math.min(sums.get(j), sums.get(j+1)));
            }
            sums = curr;
            i--;
        }
        return sums.get(0);
    }


    /**
     * https://leetcode.com/problems/triangle/discuss/38724/7-lines-neat-Java-Solution
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
            }
        }
        return A[0];
    }

}
