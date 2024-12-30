package g0201_0300.s0229_majority_element_ii;

// #Medium #Array #Hash_Table #Sorting #Counting
// #2022_07_04_Time_2_ms_(92.96%)_Space_50.2_MB_(35.08%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 50000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification:2528\. Count Substrings That Differ by One Character*);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aba ", t =  "abb "*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** The two substrings are  "aba " and  "bab ". We can replace the first 'a' with 'b' to get  "bba " which is a substring of  "abb ". The second 'a' can also be replaced with 'b' to get  "bab " which is a substring of  "abb ".*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "ab ", t =  "bb "*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** We can only replace the first character 'a' with 'b' to get  "bb " which is not a substring of  "bb ".*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length, t.length <= 100`*);
//@ requires(**   `s` and `t` consist of lowercase English letters.*);
//@ requires(**);
//@ requires(***Follow up:** Consider the performance of your solution. Could you solve it in `O(n^2)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int countSubstrings(String s, String t)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `t` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` and `t` consist of lowercase English letters.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification:2527\. Count Substrings with Only One Distinct Letter*);
//@ requires(*Medium*);
//@ requires(*Given a string `s`, return _the number of non-empty substrings of_ `s` _that have only one distinct letter_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aaaba "*);
//@ requires(***Output:** 8*);
//@ requires(***Explanation:** The substrings with one distinct letter are  "aaa ",  "aa ",  "a ",  "b ",  "ab ",  "ba ",  "aaba ",  "aaaba ".*);
//@ requires(*Note that some of these substrings repeat and are counted multiple times.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "aaaaaaaaaa "*);
//@ requires(***Output:** 55*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 1000`*);
//@ requires(**   `s[i]` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(*Method signature: public int countSubstrings(String s)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ ensures(*The list result contains all elements that appear more than ⌊ n/3 ⌋ times.*);
//@ ensures(*The list result does not contain duplicate elements.*);
//@ ensures(*If the integer array parameter `nums` is equal to [3,2,3], the list result is equal to [3].*);
//@ ensures(*If the integer array parameter `nums` is equal to [1], the list result is equal to [1].*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2], the list result is equal to [1,2].*);
//@ ensures(*Given two strings `s` and `t`, return _the number of ways you can choose a non-empty substring of_ `s` _and replace a single character by a different character such that the resulting substring is a substring of_ `t`_.*);
//@ ensures(*In other words, find the number of substrings in `s` such that replacing exactly one character in `s` with a different character results in a substring that is a substring of `t`.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "aba" and the string parameter `t` is equal to "abb", the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to "ab" and the string parameter `t` is equal to "bb", the integer result is equal to 0.*);
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> results = new ArrayList<>();
        int len = nums.length;
        int first = 0;
        int second = 1;
        int count1 = 0;
        int count2 = 0;
        // now we have two candidates(any integer can be chosed as),and their votes are
        // zero.
        for (int temp : nums) {
            if (temp == first) {
                count1++;
            } else if (temp == second) {
                count2++;
            } else if (count1 == 0) {
                first = temp;
                count1++;
            } else if (count2 == 0) {
                second = temp;
                count2++;
            } else {
                // otherwise,if one of the vote is zero,that's meaning that
                // we only have or even don't have a candidate.So we set the number to the
                // candidate.
                count1--;
                count2--;
            }
            // where we have two candidates whose votes bigger than zero,
            // but the current number is not one of them.Votes decrease by 1 and
            // the current number complete its "mission" and is skipped at the same time.
            // once the cycle finished,the target is left after all the counteraction,as its
            // count is bigger than n/3.
        }
        count1 = 0;
        count2 = 0;
        for (int temp : nums) {
            // check both of them is bigger than n/3.Becasue we may have only one satisfying
            // the demand.
            if (temp == first) {
                count1++;
            }
            if (temp == second) {
                count2++;
            }
        }
        if (count1 > len / 3) {
            results.add(first);
        }
        if (count2 > len / 3) {
            results.add(second);
        }
        return results;
    }
}