package g0001_0100.s0003_longest_substring_without_repeating_characters;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #String #Hash_Table #Sliding_Window
// #Algorithm_I_Day_6_Sliding_Window #Level_2_Day_14_Sliding_Window/Two_Pointer #Udemy_Strings
// #Big_O_Time_O(n)_Space_O(1) #2024_01_04_Time_2_ms_(99.52%)_Space_43.6_MB_(75.37%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ ensures(*1. The method returns an integer representing the length of the longest substring without repeating characters.*);
	//@ ensures(*2. The returned length is greater than or equal to 0.*);
	//@ ensures(*3. If the input string `s` is empty, the method returns 0.*);
	//@ ensures(*4. The method does not modify the input string `s`.*);
	//@ ensures(*5. The method does not throw any exceptions.*);
    public int lengthOfLongestSubstring(String s) {
        int[] lastIndices = new int[256];
        for (int i = 0; i < 256; i++) {
            lastIndices[i] = -1;
        }
        int maxLen = 0;
        int curLen = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (lastIndices[cur] < start) {
                lastIndices[cur] = i;
                curLen++;
            } else {
                int lastIndex = lastIndices[cur];
                start = lastIndex + 1;
                curLen = i - start + 1;
                lastIndices[cur] = i;
            }
            if (curLen > maxLen) {
                maxLen = curLen;
            }
        }
        return maxLen;
    }
}