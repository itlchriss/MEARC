package g1101_1200.s1104_path_in_zigzag_labelled_binary_tree;

// #Medium #Math #Tree #Binary_Tree #2023_06_01_Time_0_ms_(100.00%)_Space_40.1_MB_(82.91%)

import java.util.LinkedList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `label` is a positive integer.*);
//@ ensures(*The input `label` is within the range of 1 to 10^6.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a List of Integers.*);
//@ ensures(*The List contains the labels in the path from the root of the tree to the node with the given `label`.*);
//@ ensures(*The first element in the List is always 1, representing the root of the tree.*);
//@ ensures(*The last element in the List is the given `label`.*);
//@ ensures(*The labels in the List are in the correct order based on the zigzag pattern of the tree.*);
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> answer = new LinkedList<>();
        while (label != 0) {
            answer.add(0, label);
            int logNode = (int) (Math.log(label) / Math.log(2));
            int levelStart = (int) (Math.pow(2, logNode));
            int diff = label - levelStart;
            int d2 = diff / 2;
            int prevEnd = levelStart - 1;
            int prevLabel = prevEnd - d2;
            label = prevLabel;
        }
        return answer;
    }
}