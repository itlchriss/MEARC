package g0101_0200.s0179_largest_number;

// #Medium #Top_Interview_Questions #String #Sorting #Greedy
// #2022_06_26_Time_10_ms_(54.50%)_Space_43.6_MB_(59.08%)

import java.util.Arrays;

public class Solution {
	//@ requires(*1. The input array `nums` is not null.*);
	//@ requires(*2. The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*3. The elements in the input array `nums` are non-negative integers.*);
	//@ ensures(*1. The method returns a string representation of the largest number formed by arranging the elements in the input array `nums`.*);
	//@ ensures(*2. The returned string is not null.*);
	//@ ensures(*3. The length of the returned string is equal to the length of the input array `nums`.*);
	//@ ensures(*4. The returned string consists of the same digits as the input array `nums`, but arranged in a way that forms the largest number.*);
	//@ ensures(*5. The returned string does not contain any leading zeros, except when the input array `nums` contains only zeros.*);
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        for (String str : s) {
            sb.append(str);
        }
        String result = sb.toString();
        return result.startsWith("0") ? "0" : result;
    }
}