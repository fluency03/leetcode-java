/**
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 *
 */


import java.util.SortedMap;
import java.util.TreeMap;


class MinStack {
    SortedMap<Integer, Integer> map = new TreeMap<>();;
    Stack<Integer> st = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() { }

    public void push(int x) {
        if (map.containsKey(x)) {
            map.put(x, map.get(x)+1);
        } else {
            map.put(x, 1);
        }

        st.push(x);
    }

    public void pop() {
        Integer topEle = st.peek();
        int count = map.get(topEle);
        count--;
        if (count <= 0) {
            map.remove(topEle);
        } else {
            map.put(topEle, count);
        }

        st.pop();
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return map.firstKey();
    }
}


/**
 * https://discuss.leetcode.com/topic/4953/share-my-java-solution-with-only-one-stack
 */
class MinStack2 {
    static class Element
    {
        final int value;
        final int min;
        Element(final int value, final int min)
        {
            this.value = value;
            this.min = min;
        }
    }
    final Stack<Element> stack = new Stack<>();

    public void push(int x) {
        final int min = (stack.empty()) ? x : Math.min(stack.peek().min, x);
        stack.push(new Element(x, min));
    }

    public void pop()
    {
        stack.pop();
    }

    public int top()
    {
        return stack.peek().value;
    }

    public int getMin()
    {
        return stack.peek().min;
    }
}


/**
 * https://discuss.leetcode.com/topic/4953/share-my-java-solution-with-only-one-stack
 */
class MinStack3 {
    long min;
    Stack<Long> stack;

    public MinStack(){
        stack=new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(0L);
            min=x;
        }else{
            stack.push(x-min);//Could be negative if min value needs to change
            if (x<min) min=x;
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;

        long pop=stack.pop();

        if (pop<0)  min=min-pop;//If negative, increase the min value

    }

    public int top() {
        long top=stack.peek();
        if (top>0){
            return (int)(top+min);
        }else{
           return (int)(min);
        }
    }

    public int getMin() {
        return (int)min;
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
