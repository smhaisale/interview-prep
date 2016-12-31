package interviewbit.linkedlist;

import common.ListNode;

import static interviewbit.linkedlist.Common.print;

/**
 * Created by shahbaaz on 12/29/16.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/palindrome-list/
     * @param A
     * @return
     */
    public int lPalin(ListNode A) {
        int n = 0;
        ListNode B = A, C = A;
        while (B != null) {
            n++;
            B = B.next;
        }
        B = A;
        C = A.next;

        print(A);

        for(int i = 1; i < n/2; i++) {
            ListNode next = C.next;
            C.next = B;
            B = C;
            C = next;
        }
        A.next = C;
        A = B;

        print(A);

        B = A;
        for(int i = 0; i < (n+1)/2; i++) B = B.next;

        for(int i = 0; i < n/2; i++) {
            if (A.val != B.val) return 0;
            A = A.next;
            B = B.next;
        }
        return 1;
    }

    /**
     * https://www.interviewbit.com/problems/add-two-numbers-as-lists/
     * @param a
     * @param b
     * @return
     */
    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode x = a, y = b;
        ListNode z = new ListNode(0), z2 = z;
        int m, n, carry = 0;
        while(x != null || y != null) {
            m = (x == null) ? 0 : x.val;
            n = (y == null) ? 0 : y.val;

            int sum = m + n + carry;
            z.next = new ListNode(sum % 10);
            z = z.next;
            carry = sum / 10;

            if (x != null) x = x.next;
            if (y != null) y = y.next;
        }
        if (carry > 0) {
            z.next = new ListNode(carry);
        }
        return z2.next;
    }

    /**
     * https://www.interviewbit.com/problems/partition-list/
     * @param a
     * @param b
     * @return
     */
    public ListNode partition(ListNode a, int b) {
        ListNode z = new ListNode(b-1, a);
        ListNode end = z;

        while (end.next != null && end.next.val < b) end = end.next;

        ListNode x = end.next;
        while(x != null && x.next != null) {
            if (x.next.val < b) {
                ListNode t1 = x.next;
                x.next = x.next.next;
                t1.next = end.next;
                end.next = t1;
                end = end.next;
            }
            else x = x.next;
        }

        return z.next;
    }

    /**
     * https://www.interviewbit.com/problems/swap-list-nodes-in-pairs/
     * @param a
     * @return
     */
    public ListNode swapPairs(ListNode a) {
        if (a == null || a.next == null) return a;
        ListNode head = a.next, n1 = a, n2 = a.next;

        while(a != null && a.next != null) {
            a = n2.next;
            n2.next = n1;
            n1.next = a;

            if (a == null || a.next == null) return head;

            n1.next = a.next;
            n1 = a; n2 = a.next;
        }
        return head;
    }

    private ListNode reverse(ListNode a) {
        if (a == null || a.next == null) return a;
        ListNode x = null, y = a;
        while(y != null) {
            ListNode z = y.next;
            y.next = x;
            x = y;
            y = z;
        }
        return x;
    }

    /**
     * https://www.interviewbit.com/problems/reverse-link-list-ii/
     * @param a
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode a, int m, int n) {
        if (m == n) return a;
        ListNode x = a, y = a, z;
        for(int i = 2; i < m; i++) {
            x = x.next;
        }
        for(int i = 0; i < n-1; i++) {
            y = y.next;
        }
        z = y.next; y.next = null; y = z;

        ListNode rev = (m == 1) ? reverse(a) : reverse(x.next);

        ListNode end = rev; while (end.next != null) end = end.next;
        end.next = y;

        if (m == 1) {
            return rev;
        }
        x.next = rev;
        return a;
    }

    /**
     * https://www.interviewbit.com/problems/list-cycle/
     * @param a
     * @return
     */
    public ListNode detectCycle(ListNode a) {
        if (a == null || a.next == null) return null;
        ListNode x = a, y = a, z = a;
        do {
            if (x == null || y == null || y.next == null) return null;
            x = x.next;
            y = y.next.next;
        } while (x != y);
        while (x != z) {
            x = x.next;
            z = z.next;
        }
        return z;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(72);
        ListNode a2 = new ListNode(21, a);
        ListNode b = new ListNode(13, a2);
        ListNode c = new ListNode(83, b);
        ListNode c2 = new ListNode(1, c);
        ListNode d = new ListNode(3, c2);
        ListNode e = new ListNode(4, d);
        ListNode f = new ListNode(1, e);


        print(new Solution().reverseBetween(c2, 2, 5));
    }

}
