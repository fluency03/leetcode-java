/**
 * Given a list of people with their birth and death years, find the year with the highest population.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HighestPopulationYear {

    // brute force
    public static int find(int[][] people) {
        int minB = Integer.MAX_VALUE;
        int maxD = Integer.MIN_VALUE;
        for (int[] bd: people) {
            if (bd[0] < minB) minB = bd[0];
            if (bd[1] > maxD) maxD = bd[1];
        }

        int highest = -1;
        int year = -1;
        for (int i = minB; i <= maxD; i++) {
            int c = 0;
            for (int[] bd: people) {
                if (bd[0] <= i && bd[1] >= i) c++;
            }
            if (c > highest) {
              highest = c;
              year = i;
            }
        }

        return year;
    }


    public static int find2(int[][] people) {
        Map<Integer, Integer> map = new HashMap<>();

        int max = Integer.MIN_VALUE;
        int year = -1;
        for (int[] p: people) {
            if (map.containsKey(p[0])) {
                continue;
            } else {
                int c = 0;
                for (int[] q: people) {
                    if (q[0] <= p[0] && q[1] >= p[0]) c++;
                }
                map.put(p[0], c);
                if (c > max) {
                    max = c;
                    year = p[0];
                }
            }
        }

        return year;
    }


    public static int find3(int[][] people) {
        int minB = Integer.MAX_VALUE;
        int maxB = Integer.MIN_VALUE;
        for (int[] p: people) {
            if (p[0] < minB) minB = p[0];
            if (p[0] > maxB) maxB = p[0];
        }

        int[] arr = new int[maxB - minB + 1];
        for (int[] p: people) {
            arr[p[0]-minB] += 1;
            if (p[1] <= maxB) {
                arr[p[1]-minB] -= 1;
            }
        }

        int year = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < maxB - minB; i++) {
            if (arr[i] > max) {
                max = arr[i];
                year = i + minB;
            }
        }

        return year;
    }


    public static void main(String[] args) {
      // Arrays.sort(people, (p1, p2) -> Integer.compare(p1[0], p2[0]));
        int[][] people = {
            {2000, 2010},
            {1975, 2005},
            {1975, 2003},
            {1803, 1809},
            {1750, 1869},
            {1890, 1935},
            {1803, 1921},
            {1894, 1921}
        };

        System.out.println(HighestPopulationYear.find(people));
        System.out.println(HighestPopulationYear.find2(people));
        System.out.println(HighestPopulationYear.find3(people));
    }


}