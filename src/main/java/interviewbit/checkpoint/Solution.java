package interviewbit.checkpoint;

import apple.laf.JRSUIUtils;
import common.ListNode;
import common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shahbaaz on 12/28/16.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/kth-smallest-element-in-the-array/
     * @param a
     * @param k
     * @return
     */
    public int kthsmallest(final List<Integer> a, int k) {
        ArrayList<Integer> arr = new ArrayList<Integer>(a);
        Collections.sort(arr);
        return arr.get(k-1);
    }

    /**
     * https://www.interviewbit.com/problems/numrange/
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int numRange(ArrayList<Integer> a, int b, int c) {
        ArrayList<Integer> sums = new ArrayList<Integer>();
        Integer count = 0;
        for(int i = 0; i < a.size(); i++) {
            Integer x = a.get(i);
            if(x > c) {
                sums.clear();
                continue;
            }
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int j = 0; j < sums.size(); j++) {
                int y = sums.get(j);
                if (x + y <= c) {
                    temp.add(x+y);
                    if (x + y >= b) count++;
                }
            }
            temp.add(x);
            if (x >= b) count++;
            sums = temp;
        }
        return count;
    }

    private ListNode subtract(ListNode node, int k, int n) {
        ListNode next;
        if (k == n/2) {
            next = (n%2 == 0) ? node.next : node.next.next;
        } else {
            next = subtract(node.next, k+1, n);
        }
        node.val = next.val - node.val;
        return next.next;
    }

    /**
     * https://www.interviewbit.com/problems/subtract/
     * @param a
     * @return
     */
    public ListNode subtract(ListNode a) {
        int n = 0;
        ListNode b = a;
        while (b != null) {
            n++;
            b = b.next;
        }
        if (n > 1) subtract(a, 1, n);
        return a;
    }

    private ArrayList<TreeNode> generate(int begin, int end) {
        ArrayList<TreeNode> result = new ArrayList<TreeNode>();
        if (begin > end) return result;

        if (begin == end) {
            result.add(new TreeNode(begin));
            return result;
        }

        for(int i = begin; i <= end; i++) {
            ArrayList<TreeNode> left = generate(begin, i-1);
            ArrayList<TreeNode> right = generate(i+1, end);
            for(int j = 0; j < left.size(); j++) {
                for(int k = 0; k < left.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = left.get(j);
                    root.right = right.get(k);
                    result.add(root);
                }
            }
        }

        return result;
    }

    /**
     * https://www.interviewbit.com/problems/unique-binary-search-trees/
     * @param a
     * @return
     */
    public ArrayList<TreeNode> generateTrees(int a) {
        return generate(1, a);
    }
}
