/**
 * Implement the following operations of a stack using queues.
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * 
 * Notes:
 * - You must use only standard operations of a queue -- which means only push
 *      to back, peek/pop from front, size, and is empty operations are valid.
 * - Depending on your language, queue may not be supported natively. You may
 *      simulate a queue by using a list or deque (double-ended queue), as long
 *      as you use only standard operations of a queue.
 * - You may assume that all operations are valid (for example, no pop or top
 *      operations will be called on an empty stack).
 */


public class ImplementStackUsingQueues225 {
    
    public class MyStack {
        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();
        private int top = 0;
        
        /** Initialize your data structure here. */
        public MyStack() {
            
        }
        
        /** Push element x onto stack. */
        public void push(int x) {
            if (empty()) {
                q1.add(x);
            } else {
                if (q1.isEmpty()) {
                    q2.add(x);
                } else {
                    q1.add(x);
                }
            }
            top = x;
        }
        
        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if (q1.isEmpty()) {
                int len = q2.size();
                for (int i=1; i<len; i++) {
                    top = q2.poll();
                    q1.add(top);
                }
                return q2.poll();
            } else {
                int len = q1.size();
                for (int i=1; i<len; i++) {
                    top = q1.poll();
                    q2.add(top);
                }
                return q1.poll();
            }
        }
        
        /** Get the top element. */
        public int top() {
            return top;
        }
        
        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }

    }

    public class MyStack2 {
    
        private Queue<Integer> q = new LinkedList<>();
        
        /** Initialize your data structure here. */
        public MyStack() {
            
        }
        
        /** Push element x onto stack. */
        public void push(int x) {
            q.add(x);
            int s = q.size();
            while (s > 1) {
                q.add(q.remove());
                s--;
            }
        }
        
        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return q.remove();
        }
        
        /** Get the top element. */
        public int top() {
            return q.peek();
        }
        
        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q.isEmpty();
        }
    }

}

/**
* Your MyStack object will be instantiated and called as such:
* MyStack obj = new MyStack();
* obj.push(x);
* int param_2 = obj.pop();
* int param_3 = obj.top();
* boolean param_4 = obj.empty();
*/
