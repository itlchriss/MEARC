package g0401_0500.s0406_queue_reconstruction_by_height;

// #Medium #Array #Sorting #Greedy #Segment_Tree #Binary_Indexed_Tree
// #2022_07_16_Time_5_ms_(99.82%)_Space_43_MB_(93.43%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	//@ requires(*The input array `people` is not null.*);
	//@ requires(*The length of the input array `people` is greater than or equal to 1.*);
	//@ requires(*Each element `people[i]` in the input array `people` is an array of length 2.*);
	//@ requires(*The first element `people[i][0]` in each element `people[i]` of the input array `people` is an integer representing the height of the person.*);
	//@ requires(*The second element `people[i][1]` in each element `people[i]` of the input array `people` is an integer representing the number of people taller or equal in height to the person.*);
	//@ ensures(*The returned queue `queue` is not null.*);
	//@ ensures(*The length of the returned queue `queue` is equal to the length of the input array `people`.*);
	//@ ensures(*Each element `queue[j]` in the returned queue `queue` is an array of length 2.*);
	//@ ensures(*The first element `queue[j][0]` in each element `queue[j]` of the returned queue `queue` is an integer representing the height of the person.*);
	//@ ensures(*The second element `queue[j][1]` in each element `queue[j]` of the returned queue `queue` is an integer representing the number of people taller or equal in height to the person.*);
	//@ ensures(*The returned queue `queue` is a valid reconstruction of the input array `people`, meaning that for each element `queue[j]` in the returned queue `queue`, there are exactly `queue[j][1]` number of people taller or equal in height to the person in front of them.*);
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? -a[0] + b[0] : a[1] - b[1]);
        List<int[]> res = new ArrayList<>();
        for (int[] a : people) {
            res.add(a[1], a);
        }
        return res.toArray(new int[people.length][]);
    }
}