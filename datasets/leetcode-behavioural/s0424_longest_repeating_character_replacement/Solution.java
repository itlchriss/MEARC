package g0401_0500.s0424_longest_repeating_character_replacement;

// #Medium #String #Hash_Table #Sliding_Window #Level_1_Day_12_Sliding_Window/Two_Pointer
// #2022_07_16_Time_5_ms_(95.15%)_Space_43.4_MB_(48.28%)

public class Solution {
//@ ensures(*The string parameter `s` must not be null.*);
//@ ensures(*The integer parameter `k` must be greater than or equal to 0 and less than or equal to the length of the string parameter `s`.*);
//@ ensures(*The integer result is the length of the longest substring containing the same letter after performing at most `k` character changes.*);
    public int characterReplacement(String s, int k) {
        int left = 0;
        int right = 0;
        int len = s.length();
        int[] count = new int[256];
        char[] sArr = s.toCharArray();
        int currMax = 0;
        int maxLen = 0;
        char curr;
        while (right < len) {
            curr = sArr[right];
            count[curr]++;
            currMax = Math.max(currMax, count[curr]);
            if (right - left + 1 <= currMax + k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            while (right - left + 1 > currMax + k) {
                curr = sArr[left];
                count[curr]--;
                left++;
            }
            right++;
        }
        return maxLen;
    }
}