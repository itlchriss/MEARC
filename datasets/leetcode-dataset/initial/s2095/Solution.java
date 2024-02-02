package g2001_2100.s2095_delete_the_middle_node_of_a_linked_list;

// #Medium #Two_Pointers #Linked_List #2022_05_24_Time_4_ms_(95.21%)_Space_221.2_MB_(35.96%)

import com_github_leetcode.ListNode;

/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*You are given the `head` of a linked list. **Delete** the **middle node**, and return _the_ `head` _of the modified linked list_. We return the new list after removing this node.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
