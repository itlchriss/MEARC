package g2901_3000.s2967_minimum_cost_to_make_array_equalindromic;

// #Medium #Array #Math #Sorting #Greedy #2024_01_16_Time_15_ms_(97.78%)_Space_56.5_MB_(20.47%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ requires(*The elements in the input array `nums` are less than or equal to 10^9.*);
	//@ ensures(*The output is an integer denoting the minimum possible total cost to make `nums` equalindromic.*);
	//@ ensures(*The output is greater than or equal to 0.*);
    public long minimumCost(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int m = len % 2 != 0 ? len / 2 : len / 2 - 1;
        int previousPalindrome = getPreviousPalindrome(nums[m]);
        int nextPalindrome = getNextPalindrome(nums[m]);
        long ans1 = 0;
        long ans2 = 0;
        for (int num : nums) {
            ans1 += Math.abs(previousPalindrome - num);
            ans2 += Math.abs(nextPalindrome - num);
        }
        return Math.min(ans1, ans2);
    }

    private int getPreviousPalindrome(int num) {
        int previousPalindrome = num;
        while (!isPalindrome(previousPalindrome)) {
            previousPalindrome--;
        }
        return previousPalindrome;
    }

    private int getNextPalindrome(int num) {
        int nextPalindrome = num;
        while (!isPalindrome(nextPalindrome)) {
            nextPalindrome++;
        }
        return nextPalindrome;
    }

    private boolean isPalindrome(int num) {
        int copyNum = num;
        int reverseNum = 0;
        while (num > 0) {
            reverseNum = reverseNum * 10 + num % 10;
            num /= 10;
        }
        return copyNum == reverseNum;
    }
}