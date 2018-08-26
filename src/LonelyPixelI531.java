/**
 * Given a picture consisting of black and white pixels, find the number of
 * black lonely pixels.
 * 
 * The picture is represented by a 2D char array consisting of 'B' and 'W',
 * which means black and white pixels respectively.
 * 
 * A black lonely pixel is character 'B' that located at a specific position
 * where the same row and same column don't have any other black pixels.
 * 
 * Example:
 * Input: 
 * [['W', 'W', 'B'],
 *  ['W', 'B', 'W'],
 *  ['B', 'W', 'W']]
 * Output: 3
 * Explanation: All the three 'B's are black lonely pixels.
 * 
 * Note:
 * The range of width and height of the input 2D array is [1,500].
 */

public class LonelyPixelI531 {
    public int findLonelyPixel(char[][] picture) {
        if (picture == null || picture.length == 0 || picture[0].length == 0) return 0;
        int M = picture.length;
        int N = picture[0].length;
        int[] hori = new int[M];
        int[] vert = new int[N];
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                hori[i] = hori[i] + (picture[i][j] == 'B' ? 1 : 0);
                vert[j] = vert[j] + (picture[i][j] == 'B' ? 1 : 0);
            }
        }
        
        int res = 0;
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                if (picture[i][j] == 'B' && hori[i] == 1 && vert[j] == 1) {
                    res++;
                    continue;
                }
            }
        }
        return res;
    }

}
