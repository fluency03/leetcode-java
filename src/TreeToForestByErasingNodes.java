/**
 * 
 */

public class TreeToForestByErasingNodes {

    // shouldBeErased is predefined

    public List<Node> treeToForest(Node root) {
        List<Node> res = new ArrayList<>();
        dfs(root, true, res);
        return res;
    }

    private Node dfs(Node curr, boolean parentIsErasable, List<Node> res) {
        if (curr == null) return null;
        boolean currIsErasable = shouldBeErased(curr);
        if (parentIsErasable && !currIsErasable) res.add(curr);
        curr.left = dfs(curr.left, currIsErasable, res);
        curr.right = dfs(curr.right, currIsErasable, res);
        return currIsErasable ? null : curr;
    }

    class Node {
        Node left;
        Node right;
    }

}
