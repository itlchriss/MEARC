package g0601_0700.s0696_count_binary_substrings;

// #Easy #String #Two_Pointers #2022_03_22_Time_5_ms_(100.00%)_Space_42.5_MB_(91.17%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*3. The characters in the input string `s` are either '0' or '1'.*);
	//@ ensures(*1. The method returns an integer representing the number of non-empty substrings that have the same number of '0's and '1's, and all the '0's and '1's in these substrings are grouped consecutively.*);
    public int countBinarySubstrings(String s) {
        int start = 0;
        int ans = 0;
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                ans++;
                start = i - 1;
            } else if (start > 0 && arr[--start] != arr[i]) {
                // if start isn't 0, we may still have a valid substring
                ans++;
            } else {
                // if not, then reset start to 0
                start = 0;
            }
        }
        return ans;
    }
}