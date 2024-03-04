package g2201_2300.s2289_steps_to_make_array_non_decreasing;

// #Medium #Array #Stack #Linked_List #Monotonic_Stack
// #2022_06_17_Time_11_ms_(92.82%)_Space_75.9_MB_(79.44%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are within the range of 1 to 10^*);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the number of steps performed.*);
//@ ensures(*The input array `nums` is modified such that it becomes a non-decreasing array.*);
//@ ensures(*The modified input array `nums` does not contain any elements where `nums[i - 1] > nums[i]` for all `0 < i < nums.length`.*);
    public int totalSteps(int[] nums) {
        int max = 0;
        int[] pos = new int[nums.length + 1];
        int[] steps = new int[nums.length + 1];
        int top = -1;
        for (int i = 0; i <= nums.length; i++) {
            int val = i == nums.length ? Integer.MAX_VALUE : nums[i];
            while (top >= 0 && nums[pos[top]] <= val) {
                if (top == 0) {
                    max = Math.max(max, steps[pos[top--]]);
                } else {
                    steps[pos[--top]] = Math.max(steps[pos[top]] + 1, steps[pos[top + 1]]);
                }
            }
            pos[++top] = i;
        }
        return max;
    }
}