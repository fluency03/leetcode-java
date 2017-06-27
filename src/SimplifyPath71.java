/**
 * Given an absolute path for a file (Unix-style), simplify it.
 *
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 *
 * Corner Cases:
 *     - Did you consider the case where path = "/../"?
 *       In this case, you should return "/".
 *     - Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 *       In this case, you should ignore redundant slashes and return "/home/foo".
 */


public class SimplifyPath71 {
    public String simplifyPath(String path) {
        String[] parts = path.split("/", -1);

        List<String> result = new ArrayList<>();
        for (String p: parts) {
            if (p.equals(".") || p.equals("")) {
                continue;
            }

            if (p.equals("..")) {
                if (result.size() > 0) {
                    result.remove(result.size() - 1);
                }
                continue;
            }

            result.add(p);
        }

        return "/" + String.join("/", result.toArray(new String[result.size()]));
    }





}
