package g0301_0400.s0344_reverse_string;

// #Easy #Top_Interview_Questions #String #Two_Pointers #Recursion #Algorithm_I_Day_4_Two_Pointers
// #Udemy_Strings #2022_07_11_Time_1_ms_(99.91%)_Space_54.4_MB_(64.26%)

public class Solution {
	//@ requires(*The input array `s` is not null.*);
	//@ requires(*The length of the input array `s` is greater than or equal to - Each element `s[i]` in the input array `s` is a printable ASCII character.*);
	//@ ensures(*The input array `s` is modified in-place to contain the reversed string.*);
	//@ ensures(*The order of the elements in the input array `s` is reversed.*);
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }
}