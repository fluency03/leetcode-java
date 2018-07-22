/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the
 * Pascal's triangle.
 * 
 * Note that the row index starts from 0.
 * https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif
 * 
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * 
 * Example:
 * Input: 3
 * Output: [1,3,3,1]
 * 
 * Follow up:
 * Could you optimize your algorithm to use only O(k) extra space?
 */

public class PascalsTriangleII119 {
    public List<Integer> getRow(int rowIndex) {
        LinkedList<Integer> res = new LinkedList<>();
        res.add(1);
        if (rowIndex == 0) return res;
        res.add(1);
        if (rowIndex == 1) return res;

        int i = 2;
        while (i <= rowIndex) {
            int size = res.size();
            res.add(1);
            int pre = res.removeFirst();
            for (int j=0; j<size-1; j++) {
                int now = res.removeFirst();
                res.add(pre + now);
                pre = now;
            }
            res.add(1);
            i++;
        }

        return res;
    }


    /**
     * https://leetcode.com/problems/pascals-triangle-ii/discuss/38420/Here-is-my-brief-O(k)-solution
     */
    public List<Integer> getRow2(int rowIndex) {
        long nCk = 1;
        List<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<=rowIndex;i++){
            result.add((int)nCk);
            nCk = nCk *(rowIndex-i)/(i+1);
        }
        return result;
    }

}
