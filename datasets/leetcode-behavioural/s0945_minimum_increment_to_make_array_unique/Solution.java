package g0901_1000.s0945_minimum_increment_to_make_array_unique;

// #Medium #Array #Sorting #Greedy #Counting #2022_12_26_Time_5_ms_(99.79%)_Space_54.1_MB_(76.59%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum number of moves required to make every value in the input array `nums` unique.*);
//@ ensures(*The input array `nums` remains unchanged.*);
//@ ensures(*The elements in the input array `nums` are non-negative integers.*);
//@ ensures(*After applying the minimum number of moves, every value in the input array `nums` is unique.*);
    public int minIncrementForUnique(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] counts = new int[nums.length + max];
        for (int num : nums) {
            counts[num]++;
        }
        int minMoves = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] <= 1) {
                continue;
            }
            int remaining = counts[i] - 1;
            minMoves = minMoves + remaining;
            counts[i + 1] = counts[i + 1] + remaining;
        }
        return minMoves;
    }
}