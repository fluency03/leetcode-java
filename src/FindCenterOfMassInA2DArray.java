/**
 * Find Center Of Mass In A 2D Array.
 * https://en.wikipedia.org/wiki/Center_of_mass
 */

public class FindCenterOfMassInA2DArray {
    public int[] findCenterOfMass(int[][] weightMap) {
        if (weightMap == null || weightMap.length == 0 || weightMap[0].length == 0) return new int[]{-1, -1};
        int numRows = weightMap.length;
        int numCols = weightMap[0].length;
        int sumX = 0;
        int sumY = 0;
        int sumWeight = 0;
        for (int i=0; i<numRows; i++) {
            for (int j=0; j<numCols; j++) {
                int w = weightMap[i][j];
                if (w <= 0) continue;
                sumX += w * i;
                sumY += w * j;
                sumWeight += w;
            }
        }
        return new int[]{sumX / sumWeight, sumY / sumWeight};
    }

}
