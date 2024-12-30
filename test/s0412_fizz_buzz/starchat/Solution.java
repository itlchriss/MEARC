package g0401_0500.s0412_fizz_buzz;

// #Easy #Top_Interview_Questions #String #Math #Simulation #Udemy_Integers
// #2022_07_16_Time_1_ms_(100.00%)_Space_48.4_MB_(48.76%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1041\. Remove All Adjacent Duplicates In String II*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given a string `S` of lowercase letters and an integer `K`, a duplicate removal consists of choosing `K` adjacent and equal letters, and removing them.*);
//@ requires(**);
//@ requires(*We repeatedly make duplicate removals on S until we no longer can.*);
//@ requires(**);
//@ requires(*Return the final string after all such duplicate removals have been made. It is guaranteed the answer is unique.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** "deeedbbcccbdaa", 3*);
//@ requires(**);
//@ requires(***Output:** "aa"*);
//@ requires(**);
//@ requires(***Explanation:** *);
//@ requires(**);
//@ requires(*Initially, we remove "eee" and "bbb", so the string becomes "dcccbdaa" with 3 characters left.*);
//@ requires(**);
//@ requires(*Then we remove "ccc", so the string becomes "dbdaa" with 3 characters left.*);
//@ requires(**);
//@ requires(*Finally, we remove "ddd", so the final string is "aa".*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** "abcd", 2*);
//@ requires(**);
//@ requires(***Output:** "abcd"*);
//@ requires(**);
//@ requires(***Explanation:** *);
//@ requires(**);
//@ requires(*We cannot remove any characters since the string does not have any adjacent, equal letters.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** "deeedbbcccbdaa", 3*);
//@ requires(**);
//@ requires(***Output:** "aa"*);
//@ requires(**);
//@ requires(***Explanation:** *);
//@ requires(**);
//@ requires(*First, we remove "eee" and "bbb", so the string becomes "dcccbdaa" with 3 characters left.*);
//@ requires(**);
//@ requires(*Then we remove "ccc", so the string becomes "dbdaa" with 3 characters left.*);
//@ requires(**);
//@ requires(*Finally, we remove "ddd", so the final string is "aa".*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= S.length <= 20000`*);
//@ requires(**   `S` consists only of English lowercase letters.*);
//@ requires(**   `1 <= K <= S.length`*);
//@ requires(**);
//@ requires(*Method signature: public String removeDuplicates(String S, int K)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(*The length of the string parameter `S` is less than or equal to 20000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `S` consists only of English lowercase letters.*);
//@ requires(*The integer parameter `K` is less than or equal to the length of the string parameter `S` and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 1347\. Minimum Number of Steps to Make Two Strings Anagram*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given two equal-size strings `s` and `t`. In one step you can choose any character of `t` and replace it with another character.*);
//@ requires(**);
//@ requires(*Return _the minimum number of steps to make_ `t` _an anagram of_ `s`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "bab ", t =  "aba "*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** Replace the first 'a' in t with b, t =  "bba " which is an anagram of s.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "leetcode ", t =  "practice "*);
//@ requires(***Output:** 5*);
//@ requires(***Explanation:** Replace 'p', 'r', 'a', '*);
//@ ensures(*The length of the list result is equal to the integer parameter `n`.*);
//@ ensures(*All elements in the list result are either strings "FizzBuzz", "Fizz", "Buzz", or the string representation of an integer.*);
//@ ensures(*If the integer parameter `n` is equal to 3, the list result is equal to ["1","2","Fizz"].*);
//@ ensures(*If the integer parameter `n` is equal to 5, the list result is equal to ["1","2","Fizz","4","Buzz"].*);
//@ ensures(*If the integer parameter `n` is equal to 15, the list result is equal to ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"].*);
//@ ensures(*The string result is a unique string.*);
//@ ensures(*If the integer parameter `K` is equal to 3 and the string parameter `S` is equal to "deeedbbcccbdaa", the string result is equal to "aa".*);
//@ ensures(*If the integer parameter `K` is equal to 2 and the string parameter `S` is equal to "abcd", the string result is equal to "abcd".*);
//@ ensures(*If the integer parameter `K` is equal to 3 and the string parameter `S` is equal to "deeedbbcccbdaa", the string result is equal to "aa".*);
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(Integer.toString(i));
            }
        }
        return result;
    }
}