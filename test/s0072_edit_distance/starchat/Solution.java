package g0001_0100.s0072_edit_distance;

// #Hard #Top_100_Liked_Questions #String #Dynamic_Programming
// #Algorithm_II_Day_18_Dynamic_Programming #Dynamic_Programming_I_Day_19
// #Udemy_Dynamic_Programming #Big_O_Time_O(n^2)_Space_O(n2)
// #2023_08_11_Time_4_ms_(90.13%)_Space_41.8_MB_(99.78%)

@SuppressWarnings("java:S2234")
public class Solution {
//@ requires(*The length of the string parameter `w1` is less than or equal to 500 and is greater than or equal to 0.*);
//@ requires(*The length of the string parameter `w2` is less than or equal to 500 and is greater than or equal to 0.*);
//@ requires(*The string parameter `w1` consists of only lowercase English letters.*);
//@ requires(*The string parameter `w2` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 115\. Distinct Subsequences II*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(*Given a string `S` and a string `T`, count the number of distinct non-empty subsequences of `S` which equals `T`.*);
//@ requires(**);
//@ requires(*A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, `"ACE "` is a subsequence of `"ABCDE "` while `"AEC "` is not).*);
//@ requires(**);
//@ requires(*It's guaranteed the answer fits in a 32-bit integer.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** S =  "rabbbit ", T =  "rabbit "*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:***);
//@ requires(*As shown above, there are 3 subsequences of S that equals T:*);
//@ requires(*"rabbbit" --> "rabbit"*);
//@ requires(*"rbabbit" --> "rabbit"*);
//@ requires(*"babbit" --> "rabbit"*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** S =  "babgbag ", T =  "bag "*);
//@ requires(***Output:** 5*);
//@ requires(***Explanation:***);
//@ requires(*As shown above, there are 5 subsequences of S that equals T:*);
//@ requires(*"babgbag" --> "bag"*);
//@ requires(*"babgbag" --> "ba"*);
//@ requires(*"babgbag" --> "bg"*);
//@ requires(*"babgbag" --> "bag"*);
//@ requires(*"babgbag" --> "b"*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= S.length, T.length <= 2000`*);
//@ requires(**   `S` and `T` consist of lowercase English letters.*);
//@ requires(**);
//@ requires(***Follow up:** What if the strings are encoded as characters (for example, using a binary format)? How would you solve this problem?*);
//@ requires(**);
//@ requires(***Note:** This question is an extension of 1140: [https://leetcode.com/problems/stone-game-ii/](https://leetcode.com/problems/stone-game-ii/)*);
//@ requires(**);
//@ requires(*Method signature: public int numDistinctSubseqII(String S, String T)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the string parameter `S` is less than or equal to 2000 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `T` is less than or equal to 2000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `S` consists of only lowercase English letters.*);
//@ requires(*The string parameter `T` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 1140\. Stone Game II*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(*Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array `stoneValue`.*);
//@ requires(**);
//@ requires(*Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take one, two or three stones from the beginning of the row.*);
//@ requires(**);
//@ requires(*However, if that player takes a stone, then the scores of both players are changed: the player score increases by the value of that stone, and the opponent score decreases*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the sum of the lengths of the string parameters `w1` and `w2`.*);
//@ ensures(*If the string parameters `w1` and `w2` are equal to "horse" and "ros", the integer result is equal to 3.*);
//@ ensures(*If the string parameters `w1` and `w2` are equal to "intention" and "execution", the integer result is equal to 5.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to 2^31 - 1.*);
//@ ensures(*If the string parameters `S` and `T` are equal to "rabbbit" and "rabbit", the integer result is equal to 3.*);
//@ ensures(*If the string parameters `S` and `T` are equal to "babgbag" and "bag", the integer result is equal to 5.*);
    public int minDistance(String w1, String w2) {
        int n1 = w1.length();
        int n2 = w2.length();
        if (n2 > n1) {
            return minDistance(w2, w1);
        }
        int[] dp = new int[n2 + 1];
        //@ maintaining 0 <= j <= n2 + 1;
        for (int j = 0; j <= n2; j++) {
            dp[j] = j;
        }
        //@ maintaining 1 <= i <= n1 + 1;
        for (int i = 1; i <= n1; i++) {
            int pre = dp[0];
            dp[0] = i;
            //@ maintaining 1 <= j <= n2 + 1;
            for (int j = 1; j <= n2; j++) {
                int tmp = dp[j];
                dp[j] =
                        w1.charAt(i - 1) != w2.charAt(j - 1)
                                ? 1 + Math.min(pre, Math.min(dp[j], dp[j - 1]))
                                : pre;
                pre = tmp;
            }
        }
        return dp[n2];
    }
}