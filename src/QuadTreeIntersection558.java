



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

}

