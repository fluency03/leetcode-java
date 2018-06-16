/**
 * Given an integer n, generate all structurally unique BST's (binary search
 * trees) that store values 1...n.
 *
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class UniqueBinarySearchTreesII95 {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(n, 1, n);
    }

    public List<TreeNode> generateTrees(int n, int l, int r) {
        List<TreeNode> res = new ArrayList<>();
        if (l > r) return res;
        for (int i=l; i<=r; i++) {
            List<TreeNode> lefts = generateTrees(n, l, i-1);
            if (lefts.isEmpty()) lefts.add(null);
            List<TreeNode> rights = generateTrees(n, i+1, r);
            if (rights.isEmpty()) rights.add(null);
            for (TreeNode ll: lefts) {
                for (TreeNode rr: rights) {
                    TreeNode t = new TreeNode(i);
                    t.left = ll;
                    t.right = rr;
                    res.add(t);
                }
            }
        }

        return res;
    }


    // DP
    public List<TreeNode> generateTrees2(int n) {
        return generateTrees(n, 1, n, new List[n][n]);
    }

    public List<TreeNode> generateTrees(int n, int l, int r, List[][] dp) {
        List<TreeNode> res = new ArrayList<>();
        if (l > r) return res;
        if (dp[l-1][r-1] != null) return dp[l-1][r-1];
        for (int i=l; i<=r; i++) {
            List<TreeNode> lefts = generateTrees(n, l, i-1, dp);
            if (lefts.isEmpty()) lefts.add(null);
            List<TreeNode> rights = generateTrees(n, i+1, r, dp);
            if (rights.isEmpty()) rights.add(null);
            for (TreeNode ll: lefts) {
                for (TreeNode rr: rights) {
                    TreeNode t = new TreeNode(i);
                    t.left = ll;
                    t.right = rr;
                    res.add(t);
                }
            }
        }

        dp[l-1][r-1] = res;
        return res;
    }


    public List<TreeNode> generateTrees3(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        List<TreeNode>[][] dp = new List[n][n];
        
        List<TreeNode> lefts;
        List<TreeNode> rights;
        for (int len=1; len<=n; len++) {
            for (int start=1; start<=n+1-len; start++) {
                int end = start + len - 1;
                List<TreeNode> list = new ArrayList<>();
                for (int k=start; k<=end; k++) {
                    
                    if (start > k-1) {
                        lefts = new ArrayList<TreeNode>();
                        lefts.add(null);
                    } else {
                        lefts = dp[start-1][k-2];
                    }
                    
                    if (k+1 > end) {
                        rights = new ArrayList<TreeNode>();
                        rights.add(null);
                    } else {
                        rights = dp[k][end-1];
                    }
                    for (TreeNode left: lefts) {
                        for (TreeNode right: rights) {
                            TreeNode curr = new TreeNode(k);
                            curr.left = left;
                            curr.right = right;
                            list.add(curr);
                        }
                    }
                }
                dp[start-1][end-1] = list;
            }
        }
        
        return dp[0][n-1];
    }


    public List<TreeNode> generateTrees4(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        List<TreeNode>[][] dp = new List[n][n];
        for (int i=0; i<n; i++) {
            List<TreeNode> l = new ArrayList<>();
            l.add(new TreeNode(i+1));
            dp[i][i] = l;
        }
        for (int len=1; len<n; len++) {
            for (int i=0; i<n-len; i++) {
                List<TreeNode> l = new ArrayList<>();
                for (int j=i; j<=i+len; j++) {
                    add(dp, i, j, i+len, l);
                }
                dp[i][i+len] = l;
            }
        }
        
        return dp[0][n-1];
    }

    private void add(List<TreeNode>[][] dp, int i, int j, int k, List<TreeNode> l) {
        List<TreeNode> lefts = get(dp, i, j-1);
        List<TreeNode> rights = get(dp, j+1, k);
        
        for (TreeNode lf: lefts) {
            for (TreeNode rt: rights) {
                TreeNode n = new TreeNode(j+1);
                n.left = lf;
                n.right = rt;
                l.add(n);
            }
        }
    }
    
    private List<TreeNode> get(List<TreeNode>[][] dp, int i, int j) {
        if (i > j) {
            List<TreeNode> l = new ArrayList<>();
            l.add(null);
            return l;
        }
        return dp[i][j];
    }

}
