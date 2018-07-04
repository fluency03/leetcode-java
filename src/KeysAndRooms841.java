/**
 * There are N rooms and you start in room 0.  Each room has a distinct number
 * in 0, 1, 2, ..., N-1, and each room may have some keys to access the next
 * room. 
 * 
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j]
 * is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key
 * rooms[i][j] = v opens the room with number v.
 * 
 * Initially, all the rooms start locked (except for room 0). 
 * 
 * You can walk back and forth between rooms freely.
 * 
 * Return true if and only if you can enter every room.
 * 
 * Example 1:
 * Input: [[1],[2],[3],[]]
 * Output: true
 * Explanation:  
 * We start in room 0, and pick up key 1.
 * We then go to room 1, and pick up key 2.
 * We then go to room 2, and pick up key 3.
 * We then go to room 3.  Since we were able to go to every room, we return true.
 * 
 * Example 2:
 * Input: [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can't enter the room with number 2.
 * 
 * Note:
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * The number of keys in all rooms combined is at most 3000.
 */

public class KeysAndRooms841 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N  = rooms.size();
        Set<Integer> visited = new HashSet<>();

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (visited.contains(curr)) continue;
            visited.add(curr);
            for (Integer next: rooms.get(curr)) {
                if (!visited.contains(next)) {
                    q.add(next);
                }
            }
        }

        return visited.size() == N;
    }


    /**
     * https://leetcode.com/problems/keys-and-rooms/discuss/133895/Clean-Code
     */
    HashSet<Integer> enteredRooms = new HashSet<>();

    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        enterRoom(0, rooms);
        return enteredRooms.size() == rooms.size();
    }
    
    private void enterRoom(int roomId, List<List<Integer>> rooms) {
        enteredRooms.add(roomId);
        List<Integer> keysInRoom = rooms.get(roomId);
        for (int key: keysInRoom)
            if (!enteredRooms.contains(key))
                enterRoom(key, rooms);
    }

}
