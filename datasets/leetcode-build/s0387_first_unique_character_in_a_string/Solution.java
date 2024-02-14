package g0301_0400.s0387_first_unique_character_in_a_string;

// #Easy #Top_Interview_Questions #String #Hash_Table #Counting #Queue
// #Data_Structure_I_Day_6_String #2022_07_13_Time_1_ms_(100.00%)_Space_42.9_MB_(86.44%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` is not empty.*);
	//@ requires(*3. The input string `s` consists of only lowercase English letters.*);
	//@ ensures(*1. If there is a non-repeating character in the input string `s`, return the index of the first occurrence of that character.*);
	//@ ensures(*2. If there is no non-repeating character in the input string `s`, return -1.*);
    public int firstUniqChar(String s) {
        int ans = Integer.MAX_VALUE;
        for (char i = 'a'; i <= 'z'; i++) {
            int ind = s.indexOf(i);
            if (ind != -1 && ind == s.lastIndexOf(i)) {
                ans = Math.min(ans, ind);
            }
        }
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }
}