package g1101_1200.s1130_minimum_cost_tree_from_leaf_values;

// #Medium #Dynamic_Programming #Greedy #Stack #Monotonic_Stack
// #2023_06_01_Time_1_ms_(98.05%)_Space_40.4_MB_(69.65%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The length of the input array `arr` is at least 2 and at most 40.*);
	//@ requires(*Each element in the input array `arr` is a positive integer.*);
	//@ requires(*Each element in the input array `arr` is at least 1 and at most 15.*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value is the smallest possible sum of the values of each non-leaf node in the binary tree.*);
	//@ ensures(*The return value fits into a 32-bit signed integer (less than 2^31).*);
	//@ ensures(*The binary tree has either 0 or 2 children for each node.*);
	//@ ensures(*The values of the leaves in the binary tree correspond to the values in the input array `arr` in an in-order traversal.*);
	//@ ensures(*The value of each non-leaf node in the binary tree is equal to the product of the largest leaf value in its left and right subtree.*);
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(Integer.MAX_VALUE);
        for (int num : arr) {
            // do until the present num is bigger than nums in stack (we need to maintain the
            // increasing order in stack (bottom to up))
            while (st.peek() <= num) {
                // find two smallest leafs (integer on top of stack is smallest and at bottom is
                // largest UPTIL NOW)
                // the next smaller leaf could either be present num or the
                int smallestLeaf = st.pop();
                int smallerLeaf = Math.min(st.peek(), num);
                // num on top of stack after above pop()
                // multiply minimum leafs to reduce the SUM
                res += smallestLeaf * smallerLeaf;
            }
            st.push(num);
        }
        // if the size is 2 or less we do not to worry because we have already used it in above step
        // since 1st num we added was Integer.MAX, and we do not need to use that, so just do this
        // step if the size > 2 (basically there are at least 2 elements from the array)
        while (st.size() > 2) {
            int smallestLeaf = st.pop();
            int smallerLeaf = st.peek();
            res += smallestLeaf * smallerLeaf;
        }
        return res;
    }
}