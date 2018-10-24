/**
 * A quadtree is a tree data in which each internal node has exactly four
 * children: topLeft, topRight, bottomLeft and bottomRight. Quad trees are
 * often used to partition a two-dimensional space by recursively subdividing
 * it into four quadrants or regions.
 * 
 * We want to store True/False information in our quad tree. The quad tree is
 * used to represent a N * N boolean grid. For each node, it will be
 * subdivided into four children nodes until the values in the region it
 * represents are all the same. Each node has another two boolean attributes:
 * isLeaf and val. isLeaf is true if and only if the node is a leaf node.
 * The val attribute for a leaf node contains the value of the region it
 * represents.
 * 
 * For example, below are two quad trees A and B:
 * 
 * A:
 * +-------+-------+   T: true
 * |       |       |   F: false
 * |   T   |   T   |
 * |       |       |
 * +-------+-------+
 * |       |       |
 * |   F   |   F   |
 * |       |       |
 * +-------+-------+
 * topLeft: T
 * topRight: T
 * bottomLeft: F
 * bottomRight: F
 * 
 * B:               
 * +-------+---+---+
 * |       | F | F |
 * |   T   +---+---+
 * |       | T | T |
 * +-------+---+---+
 * |       |       |
 * |   T   |   F   |
 * |       |       |
 * +-------+-------+
 * topLeft: T
 * topRight:
 *      topLeft: F
 *      topRight: F
 *      bottomLeft: T
 *      bottomRight: T
 * bottomLeft: T
 * bottomRight: F 
 * 
 * Your task is to implement a function that will take two quadtrees and return
 * a quadtree that represents the logical OR (or union) of the two trees.
 * 
 * A:                 B:                 C (A or B):
 * +-------+-------+  +-------+---+---+  +-------+-------+
 * |       |       |  |       | F | F |  |       |       |
 * |   T   |   T   |  |   T   +---+---+  |   T   |   T   |
 * |       |       |  |       | T | T |  |       |       |
 * +-------+-------+  +-------+---+---+  +-------+-------+
 * |       |       |  |       |       |  |       |       |
 * |   F   |   F   |  |   T   |   F   |  |   T   |   F   |
 * |       |       |  |       |       |  |       |       |
 * +-------+-------+  +-------+-------+  +-------+-------+
 * 
 * Note:
 * Both A and B represent grids of size N * N.
 * N is guaranteed to be a power of 2.
 * If you want to know more about the quad tree, you can refer to its wiki.
 * The logic OR operation is defined as this: "A or B" is true if A is true, or
 * if B is true, or if both A and B are true.
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

public class QuadTreeIntersection558 {
    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1 == null || quadTree2 == null) {
            return null;
        }

        Node res = new Node();
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            res.isLeaf = true;
            res.val = quadTree1.val || quadTree2.val;
            return res;
        }

        res.topLeft = intersect(getTopLeft(quadTree1), getTopLeft(quadTree2));
        res.topRight = intersect(getTopRight(quadTree1), getTopRight(quadTree2));
        res.bottomLeft = intersect(getBottomLeft(quadTree1), getBottomLeft(quadTree2));
        res.bottomRight = intersect(getBottomRight(quadTree1), getBottomRight(quadTree2));

        if (allLeaves(res) && allSame(res)) {
            res.isLeaf = true;
            res.val = res.topLeft.val;
            return res;
        }
        return res;
    }

    private Node getTopLeft(Node n) {
        return n.isLeaf ? n : n.topLeft;
    }

    private Node getTopRight(Node n) {
        return n.isLeaf ? n : n.topRight;
    }

    private Node getBottomLeft(Node n) {
        return n.isLeaf ? n : n.bottomLeft;
    }

    private Node getBottomRight(Node n) {
        return n.isLeaf ? n : n.bottomRight;
    }

    private boolean allLeaves(Node n) {
        return n.topLeft.isLeaf &&
            n.topRight.isLeaf &&
            n.bottomLeft.isLeaf &&
            n.bottomRight.isLeaf;
    }

    private boolean allSame(Node n) {
        return (n.topLeft.val == n.topRight.val) &&
                (n.bottomLeft.val && n.bottomRight.val) &&
                (n.topLeft.val && n.bottomLeft.val);
    }


    public Node intersect2(Node q1, Node q2) {
        if (q1.isLeaf) return q1.val ? q1 : q2;
        if (q2.isLeaf) return q2.val ? q2 : q1;
        q1.topLeft = intersect(q1.topLeft, q2.topLeft);
        q1.topRight = intersect(q1.topRight, q2.topRight);
        q1.bottomLeft = intersect(q1.bottomLeft, q2.bottomLeft);
        q1.bottomRight = intersect(q1.bottomRight, q2.bottomRight);
        if (allLeaves(q1) && allSame(q1)) {
            q1.isLeaf = true;
            q1.val = q1.topLeft.val;
        }
        return q1;
    }

}

