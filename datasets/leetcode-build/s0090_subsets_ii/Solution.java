package g0001_0100.s0090_subsets_ii;

// #Medium #Array #Bit_Manipulation #Backtracking #Algorithm_II_Day_9_Recursion_Backtracking
// #2022_06_20_Time_2_ms_(82.94%)_Space_43.5_MB_(77.86%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S5413")
public class Solution {
    List<List<Integer>> allComb = new ArrayList<>();
    List<Integer> comb = new ArrayList<>();
    int[] nums;
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of -10 to 10.*);
	//@ ensures(*The output is a list of lists of integers.*);
	//@ ensures(*The output list does not contain any duplicate subsets.*);
	//@ ensures(*The output list contains all possible subsets of the input array `nums`.*);
	//@ ensures(*The order of the subsets in the output list can be arbitrary.*);

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        dfs(0);
        allComb.add(new ArrayList<>());
        return allComb;
    }

    private void dfs(int start) {
        if (start > nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            comb.add(nums[i]);
            allComb.add(new ArrayList<>(comb));
            dfs(i + 1);
            comb.remove(comb.size() - 1);
        }
    }
}