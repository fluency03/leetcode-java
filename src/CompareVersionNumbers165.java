/**
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;
 * otherwise return 0.
 * 
 * You may assume that the version strings are non-empty and contain only
 * digits and the . character.
 * 
 * The . character does not represent a decimal point and is used to separate
 * number sequences.
 * 
 * For instance, 2.5 is not "two and a half" or "half way to version three",
 * it is the fifth second-level revision of the second first-level revision.
 * 
 * Example 1:
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * 
 * Example 2:
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 * 
 * Example 3:
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 */


public class CompareVersionNumbers165 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        int len = Math.min(v1.length, v2.length);
        for (int i=0; i<len; i++) {
            int i1 = Integer.valueOf(v1[i]);
            int i2 = Integer.valueOf(v2[i]);
            if (i1 == i2) {
                continue;
            }
            return Integer.compare(i1, i2);
        }

        int l = len;
        while (l < v1.length) {
            if (Integer.valueOf(v1[l]) > 0) return 1;
            l++;
        }
        l = len;
        while (l < v2.length) {
            if (Integer.valueOf(v2[l]) > 0) return -1;
            l++;
        }
        return 0;
    }


    public int compareVersion2(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        int len = Math.max(v1.length, v2.length);
        for (int i=0; i<len; i++) {
            int i1 = i < v1.length ? Integer.valueOf(v1[i]) : 0;
            int i2 = i < v2.length ? Integer.valueOf(v2[i]) : 0;
            if (i1 == i2) {
                continue;
            }
            return Integer.compare(i1, i2);
        }

        return 0;
    }

}
