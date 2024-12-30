package g0501_0600.s0532_k_diff_pairs_in_an_array;

// #Medium #Array #Hash_Table #Sorting #Binary_Search #Two_Pointers #Udemy_Arrays
// #2022_07_28_Time_13_ms_(58.23%)_Space_48.7_MB_(27.94%)

import java.util.HashSet;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 100000000 and is greater than or equal to -100000000.*);
//@ requires(*The integer parameter `k` is less than or equal to 100000000 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1011\. Capitalize First Letter of Each Word*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given a string `title` consisting of words separated by a single space, capitalize the first letter of each word.*);
//@ requires(**);
//@ requires(*Return _the capitalized title_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** title = "hello world"*);
//@ requires(***Output:** "Hello World"*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** title = "i love leetcode"*);
//@ requires(***Output:** "I Love Leetcode"*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** title = "first letter of every word capitalized"*);
//@ requires(***Output:** "First Letter Of Every Word Capitalized"*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= title.length <= 100`*);
//@ requires(**   `title` consists of words separated by a single space.*);
//@ requires(**   Each word consists of lowercase and uppercase English letters.*);
//@ requires(**   `title` will be given in the form of a valid string.*);
//@ requires(**);
//@ requires(*Method signature: public String capitalizeTitle(String title)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the string parameter `title` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `title` consists of words separated by a single space.*);
//@ requires(*Each word in the string parameter `title` consists of lowercase and uppercase English letters.*);
//@ requires(*The string parameter `title` will be given in the form of a valid string.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 1576\. Remove All Adjacent Duplicates In String II*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given a string `S` of lowercase letters and an integer `K`.*);
//@ requires(**);
//@ requires(*We want to remove some characters from `S` such that the number of different characters is exactly `K`.*);
//@ requires(**);
//@ requires(*If it is not possible to remove `K` characters, then return an empty string `" "`.*);
//@ requires(**);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** S =  "deeedbbcccbdaa ", K = 3*);
//@ requires(***Output:**  "aa "*);
//@ requires(***Explanation:** *);
//@ requires(*First we remove  "eee " and  "bbb ", so the string becomes  "dcccbdaa ". Then we remove  "ccc ", so the string becomes  "dbdaa ". Finally, we remove  "ddd ", so the final string is  "aa ".*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** S =  "abcd ", K = 2*);
//@ requires(***Output:**  "abcd "*);
//@ requires(***Explanation:** *);
//@ requires(*We cannot remove any characters since the number of different characters is 4, which is greater than K.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** S =  "aaaaaabbbccccc ", K = 2*);
//@ requires(***Output:**  "cc "*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= S.length <=*);
//@ ensures(*If the integer array parameter `nums` is equal to [3,1,4,1,5] and the integer parameter `k` is equal to 2, the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,4,5] and the integer parameter `k` is equal to 1, the integer result is equal to 4.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,3,1,5,4] and the integer parameter `k` is equal to 0, the integer result is equal to 1.*);
//@ ensures(*If the string parameter `title` is equal to "hello world", the string result is equal to "Hello World".*);
//@ ensures(*If the string parameter `title` is equal to "i love leetcode", the string result is equal to "I Love Leetcode".*);
//@ ensures(*If the string parameter `title` is equal to "first letter of every word capitalized", the string result is equal to "First Letter Of Every Word Capitalized".*);
//@ ensures(*Return the resulting string after removing `K` characters. You may return any valid answer, and the answer is guaranteed to be unique under the given constraint.*);
    public int findPairs(int[] nums, int k) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> twice = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                if (k == 0 && !twice.contains(n)) {
                    res++;
                    twice.add(n);
                } else {
                    continue;
                }
            } else {
                if (set.contains(n - k)) {
                    res++;
                }
                if (set.contains(n + k)) {
                    res++;
                }
            }
            set.add(n);
        }
        return res;
    }
}