package g0501_0600.s0561_array_partition_i;

// #Easy #Array #Sorting #Greedy #Counting_Sort
// #2022_08_03_Time_14_ms_(84.99%)_Space_44.2_MB_(95.29%)

import java.util.Arrays;

public class Solution {
//@ requires(*Given an integer array param_nums of `2n` integers, group these integers into `n` pairs <code>(a<sub>1</sub>, b<sub>1</sub>), (a<sub>2</sub>, b<sub>2</sub>), ..., (a<sub>n</sub>, b<sub>n</sub>)</code> such that the sum of <code>min(a<sub>i</sub>, b<sub>i</sub>)</code> for all `i` is maximized.*);
//@ requires(*Return the maximized sum.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,4,3,2]*);
//@ requires(*Output: 4*);
//@ requires(*Explanation: All possible pairings (ignoring the ordering of elements) are: 1. (*);
//@ requires(*1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3 2. (*);
//@ requires(*1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3 3. (*);
//@ requires(*1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4 So the maximum possible sum is 4.*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [6,2,6,5,1,2]*);
//@ requires(*Output: 9*);
//@ requires(*Explanation: The optimal pairing is (2, 1), (2, 5), (6, 6).*);
//@ requires(*min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9.*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= n <= 10<sup>4</sup></code>*);
//@ requires(*`nums.length == 2  n`*);
//@ requires(*<code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code>*);
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            sum += Math.min(nums[i], nums[i + 1]);
        }
        return sum;
    }
}