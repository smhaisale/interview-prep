package interviewbit.heaps;

import common.Interval;
import common.ListNode;

import java.util.*;

/**
 * Created by shahbaaz on 1/1/17.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/merge-k-sorted-lists/
     * @param a
     * @return
     */
    public ListNode mergeKLists(ArrayList<ListNode> a) {

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < a.size(); i++) {
            queue.add(a.get(i));
        }

        ListNode head = new ListNode(), head2 = head;
        while(!queue.isEmpty()) {
            ListNode node = queue.poll();
            if (node != null) {
                head.next = node;
                queue.add(node.next);
                head = head.next;
            }
        }

        return head2.next;
    }

    /**
     * https://www.interviewbit.com/problems/distinct-numbers-in-window/
     * @param A
     * @param B
     * @return
     */
    public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();

        if (B > A.size()) return result;

        for(int i = 0; i < B; i++) {
            if (!count.containsKey(A.get(i))) count.put(A.get(i), 0);
            count.put(A.get(i), count.get(A.get(i)) + 1);
        }

        int distinct = count.size();

        result.add(distinct);
        for (int i = B; i < A.size(); i++) {
            if (A.get(i) != A.get(i)-B) {
                Integer n1 = count.get(A.get(i));
                Integer n2 = count.get(A.get(i-B));
                if (n1 == null) { distinct++; count.put (A.get(i), 1); }
                if (n2 == 1) { distinct--; count.remove(A.get(i)); }
                else { count.put (A.get(i-B), n2-1); }
            }
            result.add(distinct);
        }

        return result;
    }
}
