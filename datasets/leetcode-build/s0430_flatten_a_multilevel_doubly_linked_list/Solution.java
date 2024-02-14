package g0401_0500.s0430_flatten_a_multilevel_doubly_linked_list;

// #Medium #Depth_First_Search #Linked_List #Doubly_Linked_List
// #2022_07_16_Time_0_ms_(100.00%)_Space_42.4_MB_(29.79%)

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
public class Solution {
    // is true ONLY for the first element of the list
    private boolean first = true;
    // Holds the head node of the newly constructed doubly linked list
    private Node root;
    // Holds the current node of the newly constructed doubly linked list
    private Node current;
	//@ requires(*Behavioural requirements for the `flatten` method:*);
	//@ requires(*1. The `head` parameter must not be null.*);
	//@ requires(*2. The `head` parameter must be the head of the first level of the doubly linked list.*);
	//@ ensures(*1. The nodes in the flattened list must appear in a single-level, doubly linked list.*);
	//@ ensures(*2. The nodes in the child lists should appear after the current node (`curr`) and before `curr.next` in the flattened list.*);
	//@ ensures(*3. All nodes in the flattened list must have their child pointers set to null.*);
	//@ ensures(*4. The `head` of the flattened list must be returned.*);
	//@ ensures(*5. The original structure of the doubly linked list must be preserved, except for the flattening of the child lists.*);
	//@ ensures(*6. The order of the nodes in the flattened list must be the same as the order in the original doubly linked list.*);
	//@ ensures(*7. The child pointers of the nodes in the original doubly linked list must not be modified.*);
	//@ ensures(*8. The next and previous pointers of the nodes in the original doubly linked list must not be modified, except for updating the next and previous pointers to flatten the list.*);
	//@ ensures(*9. The child pointers of the nodes in the child lists must not be modified.*);
	//@ ensures(*10. The next and previous pointers of the nodes in the child lists must not be modified, except for updating the next and previous pointers to flatten the list.*);
	//@ ensures(*Note: The given requirements do not specify any constraints on the values of the nodes or any specific operations to be performed on the nodes. Therefore, the behavioural requirements focus on the structure and organization of the doubly linked list during the flattening process.*);

    public Node flatten(Node head) {
        if (head == null) {
            return root;
        } else {
            // Construct our doubly linked list
            if (first) {
                first = !first;
                root = new Node(head.val);
                current = root;
            } else {
                // Map all values to the newly constructed list.
                // temp value to hold our prev element
                Node temp = current;
                current.next = new Node(head.val);
                current = current.next;
                current.prev = temp;
            }
        }
        // iterate over child nodes.
        if (head.child != null) {
            flatten(head.child);
        }
        if (head.next != null) {
            // iterate next
            flatten(head.next);
        }
        return root;
    }
}