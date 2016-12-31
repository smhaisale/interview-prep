package interviewbit.stacks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by shahbaaz on 12/29/16.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/min-stack/
     */
    private class MinStack {

        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> minStack = new Stack<Integer>();

        public void push(int x) {
            stack.push(x);
            if (x <= minStack.peek()) minStack.push(x);
        }

        public void pop() {
            if (stack.empty()) return;
            int x = stack.pop();
            if (x == minStack.peek()) minStack.pop();
        }

        public int top() {
            if (stack.empty()) return -1;
            return stack.peek();
        }

        public int getMin() {
            if (minStack.empty()) return -1;
            return minStack.peek();
        }
    }

    /**
     * https://www.interviewbit.com/problems/simplify-directory-path/
     * @param a
     * @return
     */
    public String simplifyPath(String a) {
        String[] split = a.split("/");
        Stack<String> stack = new Stack<String>();
        for(int i = 1; i < split.length; i++) {
            System.out.println(split[i]);
            if (split[i].equals(".")) continue;
            if (split[i].equals("/")) continue;
            if (split[i].equals("")) continue;
            if (split[i].equals("..")) {
                if(!stack.empty()) stack.pop();
            } else {
                stack.push(split[i]);
            }
        }
        StringBuilder sb = new StringBuilder("/");
        Object[] arr = stack.toArray();
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i].toString());
            sb.append("/");
        }
        if (sb.length() > 1 && sb.charAt(sb.length()-1) == '/') sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    /**
     * https://www.interviewbit.com/problems/nearest-smaller-element/
     * @param arr
     * @return
     */
    public ArrayList<Integer> prevSmaller(ArrayList<Integer> arr) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < arr.size(); i++) {
            while (!stack.empty() && stack.peek() >= arr.get(i)) stack.pop();
            if (stack.empty()) result.add(-1);
            else result.add(stack.peek());
            stack.push(arr.get(i));
        }
        return result;
    }

    /**
     * https://www.interviewbit.com/problems/evaluate-expression/
     * @param a
     * @return
     */
    public int evalRPN(ArrayList<String> a) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < a.size(); i++) {
            String op = a.get(i);
            if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
                Integer n2 = stack.pop();
                Integer n1 = stack.pop();
                if (op.equals("+")) stack.push(n1 + n2);
                else if (op.equals("-")) stack.push(n1 - n2);
                else if (op.equals("*")) stack.push(n1 * n2);
                else if (op.equals("/")) stack.push(n1 / n2);
            } else {
                stack.push(Integer.parseInt(op));
            }
        }
        return stack.peek();
    }


    public static void main(String[] args) {
        System.out.println(new Solution().simplifyPath("/home//foo/"));
    }
}
