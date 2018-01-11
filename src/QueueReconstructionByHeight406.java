/**
 * Suppose you have a random list of people standing in a queue. Each person
 * is described by a pair of integers (h, k), where h is the height of the
 * person and k is the number of people in front of this person who have a
 * height greater than or equal to h. Write an algorithm to reconstruct the
 * queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */


public class QueueReconstructionByHeight406 {
    public int[][] reconstructQueue(int[][] people) {
        int y = people.length;
        if (y == 0) return people;

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < y; i++) {
            int[] p = people[i];
            persons.add(new Person(p[0], p[1]));
        }
        Collections.sort(persons, new SortRule());

        LinkedList<Person> resTemp = new LinkedList<>();
        for (int i = 0; i < y; i++) {
            Person s = persons.get(i);
            resTemp.add(s.k , s);
        }

        int[][] res = new int[y][2];
        int c = 0;
        for (Person e: resTemp) {
            res[c][0] = e.h;
            res[c][1] = e.k;
            c++;
        }

        return res;
    }

    class SortRule implements Comparator<Person> {
        @Override
        public int compare(Person a, Person b) {
            int diff = a.h - b.h;
            if (diff < 0) return 1;
            else if (diff > 0) return -1;
            else return a.k - b.k;
        }
    }

    class Person {
        Integer h;
        Integer k;
        Person(Integer h, Integer k) {
            this.h = h;
            this.k = k;
        }


    }


}
