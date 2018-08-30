/**
 * Design a Snake game that is played on a device with screen
 * size = width x height. Play the game online if you are not familiar with
 * the game.
 * 
 * The snake is initially positioned at the top left corner (0,0) with
 * length = 1 unit.
 * 
 * You are given a list of food's positions in row-column order. When a snake
 * eats the food, its length and the game's score both increase by 1.
 * 
 * Each food appears one by one on the screen. For example, the second food
 * will not appear until the first food was eaten by the snake.
 * 
 * When a food does appear on the screen, it is guaranteed that it will not
 * appear on a block occupied by the snake.
 * 
 * Example:
 * 
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 * 
 * Snake snake = new Snake(width, height, food);
 * 
 * Initially the snake appears at position (0,0) and the food at (1,2).
 * 
 * |S| | |
 * | | |F|
 * 
 * snake.move("R"); -> Returns 0
 * 
 * | |S| |
 * | | |F|
 * 
 * snake.move("D"); -> Returns 0
 * 
 * | | | |
 * | |S|F|
 * 
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after
 * that, the second food appears at (0,1) )
 * 
 * | |F| |
 * | |S|S|
 * 
 * snake.move("U"); -> Returns 1
 * 
 * | |F|S|
 * | | |S|
 * 
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 * 
 * | |S|S|
 * | | |S|
 * 
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */

public class DesignSnakeGame353 {

    class SnakeGame {
        private static Map<String, int[]> directions = new HashMap<>();
        
        private Set<Integer> snake = new HashSet<>();
        private Node head;
        private Node tail;
        private int score = 0;
        private int width;
        private int height;
        private int[][] food;
        private int nextFood = 0;

        /** Initialize your data structure here.
            @param width - screen width
            @param height - screen height 
            @param food - A list of food positions
            E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.snake.add(0);
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            this.tail.prev = this.head;
            this.head.next = this.tail;
            this.food = food;
            directions.put("U", new int[]{-1, 0});
            directions.put("L", new int[]{0, -1});
            directions.put("R", new int[]{0, 1});
            directions.put("D", new int[]{1, 0});
        }

        /** Moves the snake.
            @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
            @return The game's score after the move. Return -1 if game over. 
            Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            int[] dir = directions.get(direction);
            int nextX = head.x + dir[0];
            int nextY = head.y + dir[1];
            if (nextX < 0 || nextY < 0 || nextX >= this.height || nextY >= this.width) return -1;
            Node newHead = new Node(nextX, nextY);
            newHead.next = this.head;
            this.head.prev = newHead;
            this.head = newHead;
            if (isFood(nextX, nextY)) {
                this.score++;
                moveNextFood();
            } else {
                this.tail = this.tail.prev;
                this.tail.next = null;
                this.snake.remove(this.tail.x * this.width + this.tail.y);
            }
            int nextPos = nextX * this.width + nextY;
            if (this.snake.contains(nextPos)) return -1;
            this.snake.add(nextPos);
            return this.score;
        }

        private void moveNextFood() {
            if (this.nextFood < this.food.length) {
                this.nextFood++;
            }
        }

        private boolean isFood(int nextX, int nextY) {
            if (this.nextFood >= this.food.length) return false;
            return this.food[this.nextFood][0] == nextX && this.food[this.nextFood][1] == nextY;
        }

        class Node {
            int x;
            int y;
            Node prev;
            Node next;
            Node(int i, int j) {
                this.x = i;
                this.y = j;
            }
        }
    }


/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

}

