/**
 * We want to use quad trees to store an N x N boolean grid. Each cell in the
 * grid can only be true or false. The root node represents the whole grid. For
 * each node, it will be subdivided into four children nodes until the values
 * in the region it represents are all the same.
 * 
 * Each node has another two boolean attributes : isLeaf and val. isLeaf is
 * true if and only if the node is a leaf node. The val attribute for a leaf
 * node contains the value of the region it represents.
 * 
 * Your task is to use a quad tree to represent a given grid. The following
 * example may help you understand the problem better:
 * 
 * Given the 8 x 8 grid below, we want to construct the corresponding quad tree:
 * https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/01/962_grid.png
 * 
 * It can be divided according to the definition above:
 * https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/01/962_grid_divided.png
 * 
 * The corresponding quad tree should be as following, where each node is
 * represented as a (isLeaf, val) pair.
 * 
 * For the non-leaf nodes, val can be arbitrary, so it is represented as *.
 * https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/01/962_quad_tree.png
 * 
 * Note:
 * N is less than 1000 and guaranteened to be a power of 2.
 * If you want to know more about the quad tree, you can refer to its wiki.
 */

/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/

public class ConstructQuadTree427 {
    public Node construct(int[][] grid) {
        if (grid == null) return null;
        int N = grid.length;
        return construct(grid, 0, N-1, 0, N-1);
    }

    public Node construct(int[][] grid, int xi, int xj, int yi, int yj) {
        if (xi > xj) return null;
        Node res = new Node();
        if (xi == xj) {
            res.isLeaf = true;
            res.val = grid[xi][yi] == 1;
            return res;
        }

        int xm = (xi + xj) / 2;
        int ym = (yi + yj) / 2;
        res.topLeft = construct(grid, xi, xm, yi, ym);
        res.topRight = construct(grid, xi, xm, ym+1, yj);
        res.bottomLeft = construct(grid, xm+1, xj, yi, ym);
        res.bottomRight = construct(grid, xm+1, xj, ym+1, yj);

        if (allLeaves(res) && allSame(res)) {
            res.isLeaf = true;
            res.val = res.topLeft.val;
            res.topLeft = null;
            res.topRight = null;
            res.bottomLeft = null;
            res.bottomRight = null;
        }
        return res;
    }

    private boolean allLeaves(Node n) {
        return n.topLeft.isLeaf &&
            n.topRight.isLeaf &&
            n.bottomLeft.isLeaf &&
            n.bottomRight.isLeaf;
    }

    private boolean allSame(Node n) {
        return (n.topLeft.val == n.topRight.val) &&
                (n.bottomLeft.val == n.bottomRight.val) &&
                (n.topLeft.val == n.bottomLeft.val);
    }

}




