/**
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you
 * find more than one maximum elements, only remove the top-most one.
 * 
 * Example 1:
 * MaxStack stack = new MaxStack();
 * stack.push(5); 
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 * 
 * Note:
 * -1e7 <= x <= 1e7
 * Number of operations won't exceed 10000.
 * The last four operations won't be called when stack is empty.
 */


public class MaxStack716 {

    class MaxStack {
        
        private Stack<Element> stack;
        
        /** initialize your data structure here. */
        public MaxStack() {
            this.stack = new Stack<>();
        }
        
        public void push(int x) {
            int max = this.stack.isEmpty() ? x : Math.max(x, this.peekMax());
            this.stack.push(new Element(x, max));
        }
        
        public int pop() {
            return this.stack.pop().val;
        }
        
        public int top() {
            return this.stack.peek().val;
        }
        
        public int peekMax() {
            return this.stack.peek().max;
        }
        
        public int popMax() {
            int max = this.peekMax();
            Stack<Integer> temp = new Stack<>();
            while (this.top() != max) {
                temp.push(this.pop());
            }
            this.pop();
            while (!temp.isEmpty()) {
                this.push(temp.pop());
            }
            return max;
        }

        class Element {
            int val;
            int max;
            public Element(int val, int max) {
                this.val = val;
                this.max = max;
            }
        }
    }

    /**
     * Your MaxStack object will be instantiated and called as such:
     * MaxStack obj = new MaxStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.peekMax();
     * int param_5 = obj.popMax();
     */




}
