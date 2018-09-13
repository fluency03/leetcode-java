/**
 * Design an in-memory file system to simulate the following functions:
 * 
 * ls: Given a path in string format. If it is a file path, return a list that
 * only contains this file's name. If it is a directory path, return the list
 * of file and directory names in this directory. Your output (file and
 * directory names together) should in lexicographic order.
 * 
 * mkdir: Given a directory path that does not exist, you should make a new
 * directory according to the path. If the middle directories in the path
 * don't exist either, you should create them as well. This function has void
 * return type.
 * 
 * addContentToFile: Given a file path and file content in string format. If
 * the file doesn't exist, you need to create that file containing given
 * content. If the file already exists, you need to append given content to
 * original content. This function has void return type.
 * 
 * readContentFromFile: Given a file path, return its content in string format.
 * 
 * Example:
 * 
 * Input: 
 * ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 * [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
 * Output:
 * [null,[],null,null,["a"],"hello"]
 * Explanation:
 * https://leetcode.com/static/images/problemset/filesystem.png
 * 
 * Note:
 * You can assume all file or directory paths are absolute paths which begin
 * with / and do not end with / except that the path is just "/".
 * You can assume that all operations will be passed valid parameters and users
 * will not attempt to retrieve file content or list a directory or file that
 * does not exist.
 * You can assume that all directory names and file names only contain
 * lower-case letters, and same names won't exist in the same directory.
 */


public class DesignInMemoryFileSystem588 {

    class FileSystem {
        private Trie root = new Trie();

        public FileSystem() {
        }

        public List<String> ls(String path) {
            return root.find(path.split("/"));
        }

        public void mkdir(String path) {
            root.addDir(path.split("/"));
        }

        public void addContentToFile(String filePath, String content) {
            root.addFile(filePath.split("/"), content);
        }

        public String readContentFromFile(String filePath) {
            return root.retrieve(filePath.split("/"));
        }

        class Trie {
            Map<String, Trie> children = new HashMap<>();
            boolean isDirectory = true;
            String name = "";
            List<String> contents = new ArrayList<>();

            private int startPos(String[] path) {
                return (path.length > 0 && path[0].length() == 0) ? 1 : 0;
            }

            List<String> find(String[] path) {
                return find(path, startPos(path));
            }

            List<String> find(String[] path, int i) {
                if (path.length == i) {
                    if (isDirectory) {
                        List<String> list = new ArrayList<>(children.keySet());
                        Collections.sort(list);
                        return list;
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add(name);
                        return list;
                    }
                }
                if (!children.containsKey(path[i])) {
                    return new ArrayList<>();
                }
                return children.get(path[i]).find(path, i+1); 
            }

            void addDir(String[] path) {
                addDir(path, startPos(path));
            }

            void addDir(String[] path, int i) {
                name = path[i-1];
                if (path.length == i) {
                    return;
                }
                children.computeIfAbsent(path[i], k -> new Trie()).addDir(path, i+1); 
            }

            void addFile(String[] path, String content) {
                addFile(path, startPos(path), content);
            }

            void addFile(String[] path, int i, String content) {
                name = path[i-1];
                if (path.length == i) {
                    isDirectory = false;
                    contents.add(content);
                    return;
                }
                children.computeIfAbsent(path[i], k -> new Trie()).addFile(path, i+1, content); 
            }

            String retrieve(String[] path) {
                return retrieve(path, startPos(path));
            }

            String retrieve(String[] path, int i) {
                if (path.length == i) {
                    StringBuilder sb = new StringBuilder();
                    for (String c: contents) sb.append(c);
                    return sb.toString();
                }
                if (!children.containsKey(path[i])) {
                    return "";
                }
                return children.get(path[i]).retrieve(path, i+1); 
            }
        }
    }

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */

}
