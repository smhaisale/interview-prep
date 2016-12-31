package interviewbit.linkedlist;

import common.ListNode;

/**
 * Created by shahbaaz on 12/29/16.
 */
public class Common {

    public static void print(ListNode a) {
        ListNode b = a;
        while(b != null) {
            System.out.print(b.val + "->");
            b = b.next;
        }
        System.out.println("null");
    }
}
