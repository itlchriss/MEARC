package g2001_2100.s2012_sum_of_beauty_in_the_array;

// #Medium #Array #2022_05_24_Time_10_ms_(44.69%)_Space_98.1_MB_(18.43%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of `nums` is at least 3.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the sum of beauty of all `nums[i]` where `1 <= i <= nums.length - 2`.*);
//@ ensures(*The returned sum is non-negative.*);
    public int sumOfBeauties(int[] nums) {
        int[] maxArr = new int[nums.length];
        maxArr[0] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            maxArr[i] = Math.max(maxArr[i - 1], nums[i]);
        }
        int[] minArr = new int[nums.length];
        minArr[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            minArr[i] = Math.min(minArr[i + 1], nums[i]);
        }

        int sum = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > maxArr[i - 1] && nums[i] < minArr[i + 1]) {
                sum += 2;
            } else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                sum += 1;
            }
        }

        return sum;
    }
}