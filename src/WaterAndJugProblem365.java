/**
 * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available.
 * You need to determine whether it is possible to measure exactly z litres using these two jugs.
 *
 * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
 *
 * Operations allowed:
 *
 * Fill any of the jugs completely with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 *
 * Example 1: (From the famous "Die Hard" example)
 * Input: x = 3, y = 5, z = 4
 * Output: True
 *
 * Example 2:
 * Input: x = 2, y = 6, z = 5
 * Output: False
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class WaterAndJugProblem365 {

    public boolean canMeasureWater(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0) {
            return false;
        }

        if (x == z || y == z || z == 0 || ((x + y) == z)) {
            return true;
        }

        if (x == y) {
            return false;
        }

        return canGetZ(Math.min(x, y), Math.max(x, y), z);

    }

    private boolean canGetZ(int x, int y, int z) {
        Integer a = x;
        Integer b = 0;
        do {
            Integer tempB = a + b;
            if (tempB == z) {
                return true;
            }

            if (b == y) {
                b = 0;
                continue;
            }

            if (a == 0) {
                a = x;
                continue;
            }

            if (tempB > y) {
                a = tempB - y;
                b = y;
            } else {
                a = 0;
                b = tempB;
            }
        } while (!(a == x && b == 0));
        return false;
    }


    /** -------------------------------------------------------------------
     * Top Solution:
     * https://discuss.leetcode.com/topic/49238/math-solution-java-solution
     * https://en.wikipedia.org/wiki/B%C3%A9zout%27s_identity
     * --------------------------------------------------------------------
     */


    public boolean canMeasureWaterMath(int x, int y, int z) {
        //limit brought by the statement that water is finallly in one or both buckets
        if(x + y < z) return false;
        //case x or y is zero
        if( x == z || y == z || x + y == z ) return true;

        //get GCD, then we can use the property of Bézout's identity
        return z%GCD(x, y) == 0;
    }

    public int GCD(int a, int b){
        while(b != 0 ){
            int temp = b;
            b = a%b;
            a = temp;
        }
        return a;
    }




    public static void main(String[] args) {
        WaterAndJugProblem365 wjp = new WaterAndJugProblem365();
        System.out.println(wjp.canMeasureWaterMath(3, 5, 4));
        System.out.println(wjp.canMeasureWaterMath(4, 6, 8));
        System.out.println(wjp.canMeasureWaterMath(1, 2, 3));
        System.out.println(wjp.canMeasureWaterMath(2, 6, 5));
    }

}
