/**
 * Design and implement a data structure for a compressed string iterator. It
 * should support the following operations: next and hasNext.
 * 
 * The given compressed string will be in the form of each letter followed by
 * a positive integer representing the number of this letter existing in the
 * original uncompressed string.
 * 
 * next() - if the original string still has uncompressed characters, return
 * the next letter; Otherwise return a white space.
 * hasNext() - Judge whether there is any letter needs to be uncompressed.
 * 
 * Note:
 * Please remember to RESET your class variables declared in StringIterator,
 * as static/class variables are persisted across multiple test cases.
 * Please see here for more details.
 * 
 * Example:
 * 
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 * 
 * iterator.next(); // return 'L'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 't'
 * iterator.next(); // return 'C'
 * iterator.next(); // return 'o'
 * iterator.next(); // return 'd'
 * iterator.hasNext(); // return true
 * iterator.next(); // return 'e'
 * iterator.hasNext(); // return false
 * iterator.next(); // return ' '
 */

public class DesignCompressedStringIterator604 {

    class StringIterator {

        private int index;
        private int count;
        private int countLen;
        private char[] chars;
        private int len;

        public StringIterator(String compressedString) {
            chars = compressedString.toCharArray();
            len = chars.length;
            index = 0;
            move();
        }
        
        public char next() {
            if (index >= len) return ' ';
            char returned = chars[index];
            count--;
            if (count == 0) {
                index += countLen + 1;
                move();
            }
            return returned;
        }

        public boolean hasNext() {
            return index < len;
        }
        
        private void move() {
            if (index < len) {
                int i = index + 1;
                while (i < len && isNumeric(chars[i])) {
                    i++;
                }
                countLen = i - index - 1;
                count = Integer.parseInt(new String(chars, index + 1, countLen));
            }
        }
        
        private boolean isNumeric(char c) {
            return c >= '0' && c <= '9';
        }
        
    }


    /**
     * https://leetcode.com/problems/design-compressed-string-iterator/discuss/103828/Java-Concise-Single-Queue-Solution
     */
    class StringIterator2 {
        Queue<int[]> queue = new LinkedList<>();
        
        public StringIterator(String s) {
            int i = 0, n = s.length();
            while (i < n) {
                int j = i+1;
                while (j < n && s.charAt(j) - 'A' < 0) j++;
                queue.add(new int[]{s.charAt(i) - 'A',  Integer.parseInt(s.substring(i+1, j))});
                i = j;
            }
        }
        
        public char next() {
            if (queue.isEmpty()) return ' ';
            int[] top = queue.peek();
            if (--top[1] == 0) queue.poll();
            return (char) ('A' + top[0]);
        }
        
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }


/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */


}
