package g1901_2000.s1946_largest_number_after_mutating_substring;

// #Medium #Array #String #Greedy #2022_05_18_Time_18_ms_(70.91%)_Space_72.8_MB_(9.09%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `num` must not be null.*);
//@ ensures(*The input array `change` must not be null.*);
//@ ensures(*The length of `num` must be greater than or equal to 1 and less than or equal to 10^5.*);
//@ ensures(*The length of `change` must be equal to 10.*);
//@ ensures(*Each element in `change` must be an integer between 0 and 9 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a string representing the largest possible integer after mutating a single substring of `num`.*);
//@ ensures(*The returned string is not null.*);
//@ ensures(*The returned string has the same length as `num`.*);
//@ ensures(*The returned string is formed by replacing each digit `num[i]` with the digit it maps to in `change` (i.e. replace `num[i]` with `change[num[i]]`).*);
//@ ensures(*The returned string represents the largest number that can be created after mutating a single substring of `num`.*);
    public String maximumNumber(String num, int[] change) {
        int n = num.length();
        char[] nums = num.toCharArray();
        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            int val = nums[i] - '0';
            arr[i] = (char) (change[val] + '0');
        }
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] < arr[i]) {
                nums[i] = arr[i];
                flag = true;
            } else if (flag && nums[i] > arr[i]) {
                break;
            }
        }
        return String.valueOf(nums);
    }
}