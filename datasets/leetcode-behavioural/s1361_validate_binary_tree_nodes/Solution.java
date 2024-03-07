package g1301_1400.s1361_validate_binary_tree_nodes;

// #Medium #Depth_First_Search #Breadth_First_Search #Tree #Binary_Tree #Graph #Union_Find
// #2022_03_21_Time_8_ms_(69.78%)_Space_55.4_MB_(51.49%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` must be a positive integer.*);
//@ ensures(*The length of the `leftChild` array must be equal to the length of the `rightChild` array.*);
//@ ensures(*The values in the `leftChild` and `rightChild` arrays must be within the range of `-1` to `n - 1`.*);
//@ ensures(*The values in the `leftChild` and `rightChild` arrays must be integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return a boolean value indicating whether all the given nodes form exactly one valid binary tree.*);
//@ ensures(*If the method returns `true`, it means that all the given nodes form exactly one valid binary tree.*);
//@ ensures(*If the method returns `false`, it means that the given nodes do not form exactly one valid binary tree.*);
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] inDeg = new int[n];
        for (int i = 0; i < n; i++) {
            if (leftChild[i] >= 0) {
                inDeg[leftChild[i]] += 1;
            }
            if (rightChild[i] >= 0) {
                inDeg[rightChild[i]] += 1;
            }
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                if (queue.isEmpty()) {
                    queue.offer(i);
                } else {
                    // Violate rule 1.
                    return false;
                }
            }
            if (inDeg[i] > 1) {
                // Violate rule 2.
                return false;
            }
        }
        int tpLen = 0;
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            tpLen++;
            int left = leftChild[curNode];
            int right = rightChild[curNode];
            if (left > -1 && --inDeg[left] == 0) {
                queue.offer(left);
            }
            if (right > -1 && --inDeg[right] == 0) {
                queue.offer(right);
            }
        }
        return tpLen == n;
    }
}