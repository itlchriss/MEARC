package g1401_1500.s1424_diagonal_traverse_ii;

// #Medium #Array #Sorting #Heap_Priority_Queue
// #2022_03_28_Time_39_ms_(85.56%)_Space_109.5_MB_(78.06%)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `nums` is a 2D integer array.*);
//@ ensures(*The length of `nums` is greater than or equal to 1 and less than or equal to 100,Each element in `nums` is a list of integers.*);
//@ ensures(*The length of each list in `nums` is greater than or equal to 1 and less than or equal to 100,The sum of the lengths of all lists in `nums` is greater than or equal to 1 and less than or equal to 100,Each element in `nums` is an integer greater than or equal to 1 and less than or equal to 100,*);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an array of integers.*);
//@ ensures(*The length of the returned array is equal to the sum of the lengths of all lists in `nums`.*);
//@ ensures(*The elements in the returned array are in diagonal order, as shown in the given examples.*);
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<Integer> ans = new ArrayList<>();
        ArrayDeque<Iterator<Integer>> queue = new ArrayDeque<>();
        int pos = 0;
        do {
            if (pos < nums.size()) {
                queue.offerFirst(nums.get(pos).iterator());
            }
            int sz = queue.size();
            while (--sz >= 0) {
                Iterator<Integer> cur = queue.poll();
                ans.add(Objects.requireNonNull(cur).next());
                if (cur.hasNext()) {
                    queue.offer(cur);
                }
            }
            pos++;
        } while (!queue.isEmpty() || pos < nums.size());
        return ans.stream().mapToInt(o -> o).toArray();
    }
}