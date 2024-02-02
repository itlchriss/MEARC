package g0001_0100.s0092_reverse_linked_list_ii;

// #Medium #Linked_List #2022_06_21_Time_0_ms_(100.00%)_Space_41.8_MB_(52.21%)

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
//@ ensures(*Given the `head` of a singly linked list and two integers `left` and `right` where `left <= right`, reverse the nodes of the list from position `left` to position `right`, and return _the reversed list_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode prev = null;
        ListNode temp = head;
        ListNode start;
        int k = left;
        while (temp != null && k > 1) {
            prev = temp;
            temp = temp.next;
            k--;
        }
        if (left > 1 && prev != null) {
            prev.next = null;
        }
        ListNode prev1 = null;
        start = temp;
        while (temp != null && right - left >= 0) {
            prev1 = temp;
            temp = temp.next;
            right--;
        }
        if (prev1 != null) {
            prev1.next = null;
        }
        if (left > 1 && prev != null) {
            prev.next = reverse(start);
        } else {
            head = reverse(start);
            prev = head;
        }
        while (prev.next != null) {
            prev = prev.next;
        }
        prev.next = temp;
        return head;
    }

    public ListNode reverse(ListNode head) {
        ListNode p;
        ListNode q;
        ListNode r = null;
        p = head;
        while (p != null) {
            q = p.next;
            p.next = r;
            r = p;
            p = q;
        }
        return r;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
