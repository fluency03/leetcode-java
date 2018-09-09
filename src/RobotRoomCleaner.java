/**
 * Given a robot cleaner in a room modeled as a grid.
 * 
 * Each cell in the grid can be empty or blocked.
 * 
 * The robot cleaner with 4 given APIs can move forward, turn left or turn
 * right. Each turn it made is 90 degrees.
 * 
 * When it tries to move into a blocked cell, its bumper sensor detects the
 * obstacle and it stays on the current cell.
 * 
 * Design an algorithm to clean the entire room using only the 4 given APIs
 * shown below.
 * 
 * interface Robot {
 *   // returns true if next cell is open and robot moves into the cell.
 *   // returns false if next cell is obstacle and robot stays on the current cell.
 *   boolean move();
 * 
 *   // Robot will stay on the same cell after calling turnLeft/turnRight.
 *   // Each turn will be 90 degrees.
 *   void turnLeft();
 *   void turnRight();
 * 
 *   // Clean the current cell.
 *   void clean();
 * }
 * 
 * Example:
 * Input:
 * room = [
 *   [1,1,1,1,1,0,1,1],
 *   [1,1,1,1,1,0,1,1],
 *   [1,0,1,1,1,1,1,1],
 *   [0,0,0,1,0,0,0,0],
 *   [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 * 
 * Explanation:
 * All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 * 
 * Notes:
 * - The input is only given to initialize the room and the robot's position
 * internally. You must solve this problem "blindfolded". In other words,
 * you must control the robot using only the mentioned 4 APIs, without knowing
 * the room layout and the initial robot's position.
 * - The robot's initial position will always be in an accessible cell.
 * - The initial direction of the robot will be facing up.
 * - All accessible cells are connected, which means the all cells marked as 1
 * will be accessible by the robot.
 * - Assume all four edges of the grid are all surrounded by wall.
 */

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

public class RobotRoomCleaner {
    public void cleanRoom(Robot robot) {
        cleanRoom(robot, new HashMap<Integer, Set<Integer>>(), new int[2], 0, true);
    }

    public void cleanRoom(Robot robot, Map<Integer, Set<Integer>> map, int[] pos, int dir, boolean in) {
        robot.clean();
        if (!map.containsKey(pos[0])) {
            map.put(pos[0], new HashSet<>());
        }
        map.get(pos[0]).add(pos[1]);
        int[] nxt = next(pos, dir);
        if (!contains(map, nxt) && robot.move()) {
            cleanRoom(robot, map, nxt, dir, false);
        }

        dir = (dir + 1) % 4;
        robot.turnLeft();
        nxt = next(pos, dir);
        if (!contains(map, nxt) && robot.move()) {
            cleanRoom(robot, map, nxt, dir, false);
        }

        dir = (dir + 1) % 4;
        robot.turnLeft();
        nxt = next(pos, dir);
        if (in && !contains(map, nxt) && robot.move()) {
            cleanRoom(robot, map, nxt, dir, false);
        }

        dir = (dir + 1) % 4;
        robot.turnLeft();
        nxt = next(pos, dir);
        if (!contains(map, nxt) && robot.move()) {
            cleanRoom(robot, map, nxt, dir, false);
        }
        robot.turnRight();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
    }

    private boolean contains(Map<Integer, Set<Integer>> map, int[] pos) {
        return map.containsKey(pos[0]) && map.get(pos[0]).contains(pos[1]);
    }

    private int[] next(int[] now, int dir) {
        if (dir == 0) {
            return up(now);
        } else if (dir == 1) {
            return left(now);
        } else if (dir == 2) {
            return down(now);
        } else {
            return right(now);
        }
    }

    private int[] up(int[] now) {
        return new int[]{now[0]-1, now[1]};
    }

    private int[] left(int[] now) {
        return new int[]{now[0], now[1]-1};
    }

    private int[] down(int[] now) {
        return new int[]{now[0]+1, now[1]};
    }

    private int[] right(int[] now) {
        return new int[]{now[0], now[1]+1};
    }


    public void cleanRoom2(Robot robot) {
        dfs(robot, new HashMap<>(), new int[2], 0);
    }

    private void dfs(Robot robot, Map<Integer, Set<Integer>> visited, int[] pos, int dir) {
        // System.out.println(pos[0] + ", " + pos[1] + ": " + dir);
        if (visited.containsKey(pos[0]) && visited.get(pos[0]).contains(pos[1])) {
            robot.turnRight();
            robot.turnRight();
            robot.move();
            return;
        }

        if (!visited.containsKey(pos[0])) visited.put(pos[0], new HashSet<>());
        visited.get(pos[0]).add(pos[1]);
        robot.clean();

        if (robot.move()) {
            dfs(robot, visited, nextPos(pos, dir), dir);
        } else {
            robot.turnRight();
            robot.turnRight();
        }

        robot.turnRight();
        dir = nextDir(dir, 3);
        if (robot.move()) {
            dfs(robot, visited, nextPos(pos, dir), dir);
        } else {
            robot.turnRight();
            robot.turnRight();
        }

        dir = nextDir(dir, 2);
        if (robot.move()) {
            dfs(robot, visited, nextPos(pos, dir), dir);
            robot.turnLeft();
        } else {
            robot.turnRight();
        }

        dir = nextDir(dir, 1);
        if (robot.move()) {
            dfs(robot, visited, nextPos(pos, dir), dir);
            robot.turnRight();
            robot.turnRight();
        }
        robot.move();
    }

    private int nextDir(int dir, int move) {
        return (dir + move) % 4; 
    }

    private int[] nextPos(int[] pos, int dir) {
        if (dir == 0) {
            return new int[]{pos[0], pos[1]+1};
        } else if (dir == 1) {
            return new int[]{pos[0]+1, pos[1]};
        } else if (dir == 2) {
            return new int[]{pos[0], pos[1]-1};
        } else {
            return new int[]{pos[0]-1, pos[1]};
        }
    }

}
