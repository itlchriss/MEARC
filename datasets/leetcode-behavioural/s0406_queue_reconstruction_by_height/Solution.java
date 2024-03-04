package g0401_0500.s0406_queue_reconstruction_by_height;

// #Medium #Array #Sorting #Greedy #Segment_Tree #Binary_Indexed_Tree
// #2022_07_16_Time_5_ms_(99.82%)_Space_43_MB_(93.43%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*The integer array parameter `people` must not be null.*);
//@ ensures(*The integer array parameter `people` must contain attributes of people in a queue, where each element is an array of two integers representing the height and the number of people in front with greater or equal height.*);
//@ ensures(*The integer array result `queue` must be formatted as an array where each element is an array of two integers representing the height and the number of people in front for each person in the reconstructed queue.*);
//@ ensures(*The reconstructed queue must satisfy the conditions specified in the input array `people`, where each person's position is determined by the number of people in front with greater or equal height.*);
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? -a[0] + b[0] : a[1] - b[1]);
        List<int[]> res = new ArrayList<>();
        for (int[] a : people) {
            res.add(a[1], a);
        }
        return res.toArray(new int[people.length][]);
    }
}