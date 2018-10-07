/**
 * Design a data structure that supports all following operations in average
 * O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements.
 *     Each element must have the same probability of being returned.
 *
 * Example:
 *
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 *
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 *
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 *
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 *
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 *
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 *
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 *
 */


public class InsertDeleteGetRandomOOne380 {
    public class RandomizedSet {
        private Set<Integer> set;
        private Random rand;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            this.set = new HashSet<Integer>();
            this.rand = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            return this.set.add(val);
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            return this.set.remove(val);
        }

        /** Get a random element from the set. */
        public int getRandom() {
            Object[] values = this.set.toArray();
            return (Integer) values[rand.nextInt(values.length)];
        }
    }

    public class RandomizedSet2 {

        private Map<Integer, Integer> map;
        private ArrayList<Integer> values;
        private Random rand;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            this.map = new HashMap<Integer, Integer>();
            this.values = new ArrayList<Integer>();
            this.rand = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) return false;
            map.put(val, values.size());
            values.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;

            int pos = map.get(val);
            int len = values.size();
            if (pos != len-1) {
                int temp = values.get(len-1);
                values.set(pos, temp);
                map.put(temp, pos);
            }

            map.remove(val);
            values.remove(len-1);

            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return values.get(rand.nextInt(values.size()));
        }
    }


    class RandomizedSet3 {
        private Map<Integer, Integer> indexToValue = new HashMap<>();
        private Map<Integer, Integer> valueToIndex = new HashMap<>();
        private Queue<Integer> q = new LinkedList<>();
        private int index = 0;
        private Random rand = new Random();

        /** Initialize your data structure here. */
        public RandomizedSet() {
            
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (valueToIndex.containsKey(val)) return false;
            if (q.isEmpty()) {
                indexToValue.put(index, val);
                valueToIndex.put(val, index);
                index++;
            } else {
                int currIdx = q.poll();
                indexToValue.put(currIdx, val);
                valueToIndex.put(val, currIdx);
            }
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!valueToIndex.containsKey(val)) return false;
            int idx = valueToIndex.remove(val);
            indexToValue.remove(idx);
            q.add(idx);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            if (valueToIndex.isEmpty()) return -1;
            while (true) {
                int idx = rand.nextInt(index);
                if (!indexToValue.containsKey(idx)) continue;
                return indexToValue.get(idx);
            }
        }
    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

}
