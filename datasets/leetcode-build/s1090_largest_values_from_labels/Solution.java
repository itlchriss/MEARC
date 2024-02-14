package g1001_1100.s1090_largest_values_from_labels;

// #Medium #Array #Hash_Table #Sorting #Greedy #Counting
// #2022_02_23_Time_14_ms_(95.96%)_Space_43.2_MB_(80.43%)

import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution {
    private static class Node {
        int val;
        int label;

        Node(int val, int label) {
            this.val = val;
            this.label = label;
        }
    }
	//@ requires(*The length of the `values` array is equal to the length of the `labels` array.*);
	//@ requires(*The length of the `values` array is greater than or equal to - The length of the `values` array is less than or equal to - The length of the `labels` array is greater than or equal to - The length of the `labels` array is less than or equal to - The elements in the `values` array are integers greater than or equal to - The elements in the `values` array are integers less than or equal to - The elements in the `labels` array are integers greater than or equal to - The elements in the `labels` array are integers less than or equal to - The value of `numWanted` is greater than or equal to - The value of `numWanted` is less than or equal to the length of the `values` array.*);
	//@ requires(*The value of `useLimit` is greater than or equal to - The value of `useLimit` is less than or equal to the length of the `values` array.*);
	//@ ensures(*The method returns an integer representing the maximum score of a subset `s`.*);
	//@ ensures(*The maximum score of a subset `s` is the sum of the values in the subset.*);
	//@ ensures(*The size of the subset `s` is less than or equal to `numWanted`.*);
	//@ ensures(*There are at most `useLimit` items with the same label in `s`.*);

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        PriorityQueue<Node> maxHeap =
                new PriorityQueue<>((a, b) -> b.val != a.val ? b.val - a.val : a.label - b.label);
        int n = values.length;
        for (int i = 0; i < n; i++) {
            maxHeap.offer(new Node(values[i], labels[i]));
        }
        int ans = 0;
        HashMap<Integer, Integer> labelAddedCount = new HashMap<>();
        while (!maxHeap.isEmpty() && numWanted > 0) {
            Node cur = maxHeap.poll();
            if (labelAddedCount.containsKey(cur.label)
                    && labelAddedCount.get(cur.label) >= useLimit) {
                continue;
            }
            if (cur.val > 0) {
                ans += cur.val;
                labelAddedCount.put(cur.label, labelAddedCount.getOrDefault(cur.label, 0) + 1);
                numWanted--;
            }
        }
        return ans;
    }
}