/**
 * Given an encoded string, return it's decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the
 * square brackets is being repeated exactly k times. Note that k is guaranteed
 * to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces,
 * square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any
 * digits and that digits are only for those repeat numbers, k. For example,
 * there won't be input like 3a or 2[4].
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 */


public class DecodeString394 {
  public String decodeString(String s) {
      StringBuilder sb = new StringBuilder("");

      int i = 0;
      StringBuilder num = new StringBuilder("");
      while (i < s.length()) {
          char c = s.charAt(i);
          System.out.println("ou: " + c);
          if (c >= '0' && c <= '9') {
              num.append(c);
              i++;
              continue;
          }

          if (c == '[') {
              Res res = decodeString(s, i);
              int next = res.next;
              String in = res.in;
              for (int j = 0; j<Integer.parseInt(num.toString()); j++) {
                  sb.append(in);
              }
              num = new StringBuilder("");
              i = next;
              continue;
          }

          if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
              sb.append(c);
              i++;
              continue;
          }

      }

      return sb.toString();
  }

  public Res decodeString(String s, int start) {
      StringBuilder sb = new StringBuilder("");

      int i = start+1;
      StringBuilder num = new StringBuilder("");
      while (i < s.length()) {
          char c = s.charAt(i);
          System.out.println("in: " + c);
          if (c >= '0' && c <= '9') {
              num.append(c);
              i++;
              continue;
          }

          if (c == '[') {
              Res res = decodeString(s, i);
              int next = res.next;
              String in = res.in;
              for (int j = 0; j<Integer.parseInt(num.toString()); j++) {
                  sb.append(in);
              }
              num = new StringBuilder("");
              i = next;
              continue;
          }

          if (c == ']') {
              return new Res(i+1, sb.toString());
          }

          if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
              sb.append(c);
              i++;
              continue;
          }

      }
      return new Res(i+1, sb.toString());
  }

  class Res {
      int next;
      String in;
      Res (int next, String in) {
          this.next = next;
          this.in = in;
      }
  }
}
