/**
 * Note: This is a companion problem to the System Design problem:
 * Design TinyURL (https://leetcode.com/problems/design-tinyurl/)
 *
 * TinyURL is a URL shortening service where you enter a URL such as
 * https://leetcode.com/problems/design-tinyurl and it returns a short URL
 * such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service. There is no
 * restriction on how your encode/decode algorithm should work. You just need
 * to ensure that a URL can be encoded to a tiny URL and the tiny URL can be
 * decoded to the original URL.
 *
 */

public class EncodeAndDecodeTinyURL535 {
    public class Codec {
        public static final char[] ALPHABETS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-', '_'
        };
        public static final int BASE = 64;
        public static final Random rand = new Random();

        private Map<String, String> lookup = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            StringBuilder str = new StringBuilder();
            for (int i=0; i<7; i++) {
                str.append(ALPHABETS[rand.nextInt(BASE)]);
            }
            String key = str.toString();
            lookup.put(key, longUrl);
            return key;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return lookup.get(shortUrl);
        }
    }


    public class Codec2 {

        private char[] BASE62 = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
        
        Map<String, String> map = new HashMap<>();
        int id = 0;
        
        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            StringBuilder sb = new StringBuilder();
            int n = id;
            while (n != 0) {
                sb.append(BASE62[n % 26]);
                n /= 62;
            }
            sb.append(BASE62[n % 26]);
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, longUrl);
                id++;
            }
            return key;
        }
    
        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return map.get(shortUrl);
        }
    }


    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.decode(codec.encode(url));

}
