package g0501_0600.s0528_random_pick_with_weight;

// #Medium #Math #Binary_Search #Prefix_Sum #Randomized #Binary_Search_II_Day_13
// #2022_07_28_Time_42_ms_(50.90%)_Space_60.1_MB_(13.48%)

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.TreeSet;

@SuppressWarnings("java:S2245")
public class Solution {
    private int prefix;
    private final Random random;
    private final TreeSet<int[]> treeSet;
<<<<<<< HEAD
=======
//@ ensures(*You need to implement the function `pickIndex()`, which **randomly** picks an index in the range `[0, w.length - 1]` (**inclusive**) and returns it. The **probability** of picking an index `i` is `w[i] / sum(w)`. solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w. solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4. solution.pickIndex(); // return 1 solution.pickIndex(); // return 1 solution.pickIndex(); // return 1 solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03

    public Solution(int[] w) {
        prefix = 0;
        treeSet = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < w.length; i++) {
            prefix += w[i];
            treeSet.add(new int[] {prefix, i});
        }
        random = new Random();
    }

    public int pickIndex() {
        int target = random.nextInt(prefix) + 1;
        return Objects.requireNonNull(treeSet.ceiling(new int[] {target, 1}))[1];
    }
}

/*
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
<<<<<<< HEAD
 */
=======
 */
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
