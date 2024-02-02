package g1701_1800.s1721_swapping_nodes_in_a_linked_list;

// #Medium #Two_Pointers #Linked_List #2022_04_25_Time_2_ms_(100.00%)_Space_182.3_MB_(38.16%)

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
//@ ensures(*Return _the head of the linked list after **swapping** the values of the_ <code>k<sup>th</sup></code> _node from the beginning and the_ <code>k<sup>th</sup></code> _node from the end (the list is **1-indexed**)._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public ListNode swapNodes(ListNode head, int k) {
        ListNode beg = null;
        ListNode end = null;
        ListNode node = head;
        while (node != null) {
            k--;
            if (k == 0) {
                beg = node;
                end = head;
            } else if (end != null) {
                end = end.next;
            }
            node = node.next;
        }
        if (beg != null) {
            int tem = beg.val;
            beg.val = end.val;
            end.val = tem;
        }
        return head;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
