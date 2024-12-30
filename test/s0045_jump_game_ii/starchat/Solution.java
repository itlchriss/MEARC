package g0001_0100.s0045_jump_game_ii;

// #Medium #Top_100_Liked_Questions #Array #Dynamic_Programming #Greedy
// #Algorithm_II_Day_13_Dynamic_Programming #Dynamic_Programming_I_Day_4
// #Big_O_Time_O(n)_Space_O(1) #2023_08_11_Time_2_ms_(49.02%)_Space_44.7_MB_(52.72%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2528\. Maximum Number of Non-Overlapping Substrings with a Given Length*);
//@ requires(*Medium*);
//@ requires(*Given a string `s` and an integer `k`, return _the maximum number of non-overlapping substrings of length_ `k` _that contain only lowercase English letters_.*);
//@ requires(**);
//@ requires(*A substring is a contiguous sequence of characters within a string.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s = "adefaddaccc", k = 3*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** The substring of length 3 with maximum frequency "add" is 2.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s = "abbcccaa", k = 3*);
//@ requires(***Output:** 0*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 105`*);
//@ requires(**   `s` consists of only lowercase English letters.*);
//@ requires(**   `1 <= k <= 26`*);
//@ requires(**);
//@ requires(*Method signature: public int maxNonOverlappingSubstrings(String s, int k)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only lowercase English letters.*);
//@ requires(*The integer parameter `k` is less than or equal to 26 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2527\. Maximum Number of Non-Overlapping Substrings with a Given Length and a Given Character*);
//@ requires(*Medium*);
//@ requires(*Given a string `s` and an integer `k`, return _the maximum number of non-overlapping substrings of length_ `k` _that contain only lowercase English letters and at least one occurrence of a given character_.*);
//@ requires(**);
//@ requires(*A substring is a contiguous sequence of characters within a string.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s = "adefaddaccc", k = 3, c = 'a'*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** The substring of length 3 with maximum frequency "add" is 2. The character 'a' appears at least once.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s = "abbcccaa", k = 3, c = 'b'*);
//@ requires(***Output:** 0*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 105`*);
//@ requires(**   `s` consists of only lowercase English letters.*);
//@ requires(**   `1 <= k <= 26`*);
//@ requires(**   `c` is a lowercase English letter.*);
//@ requires(**);
//@ requires(*Method signature: public int maxNonOverlappingSubstrings(String s, int k, char c)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only lowercase English letters.*);
//@ requires(*The integer parameter `k` is less than or equal to 26 and is greater than or equal to 1.*);
//@ requires(*The character parameter `c` is a lowercase English letter.*);
//@ requires(*If the string parameter `s` is equal to "adefaddaccc" and the integer parameter `k` is equal to 3*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to the length of the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [2,3,1,1,4], the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [2,3,0,1,4], the integer result is equal to 2.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "adefaddaccc" and the integer parameter `k` is equal to 3, the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to "abbcccaa" and the integer parameter `k` is equal to 3, the integer result is equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
    public int jump(int[] nums) {
        int length = 0;
        int maxLength = 0;
        int minJump = 0;
        //@ assume nums.length > 1;
        //@ decreases nums.length - 1 - i;
        //@ loop_invariant 0 <= i <= nums.length - 1;
        //@ maintaining length <= nums.length - i - 1;
        for (int i = 0; i < nums.length - 1; ++i) {
            length--;
            maxLength--;
            maxLength = Math.max(maxLength, nums[i]);
            if (length <= 0) {
                length = maxLength;
                minJump++;
            }
            if (length >= nums.length - i - 1) {
                return minJump;
            }
        }
        return minJump;
    }
}