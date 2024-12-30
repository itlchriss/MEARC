package g0301_0400.s0331_verify_preorder_serialization_of_a_binary_tree;

// #Medium #String #Tree #Binary_Tree #Stack #2022_07_10_Time_2_ms_(99.12%)_Space_42.5_MB_(79.33%)

public class Solution {
//@ requires(*The string parameter `preorder` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `preorder` consists of comma-separated values that are either integers in the range [0, 100] or a character '#' representing null pointer.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2521\. Count the Digits That Divide a Number*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given an integer `num`, return _the number of digits in_ `num` _that divide_ `num`.*);
//@ requires(**);
//@ requires(*An integer `val` divides `num` if:*);
//@ requires(**);
//@ requires(**   `num % val == 0`*);
//@ requires(**   `val!= 0`*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** num = 7*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** 7 divides itself, hence the answer is 1.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** num = 121*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** 121 is divisible by 1, 11, and itself. Hence the answer is 2.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** num = 1248*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** 1248 is divisible by 1, 2, 3, 4, 6, 8, and itself. Hence the answer is 3.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= num <= 100`*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve it without looping through all the digits in `num`?*);
//@ requires(**);
//@ requires(*Method signature: public int countDigits(int x)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The integer parameter `x` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2951\. Find the Peaks*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*You are given a **0-indexed** array `mountain`. Your task is to find all the **peaks** in the `mountain` array.*);
//@ requires(**);
//@ requires(*Return _an array that consists of the indices of the peaks in the given array in **any order**._*);
//@ requires(**);
//@ requires(***Notes:***);
//@ requires(**);
//@ requires(**   A peak is an element that strictly greater than its neighbors.*);
//@ requires(**   The first and last elements of the array cannot be peaks.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** mountain = \[2,1,3,1,2\]*);
//@ requires(***Output:** \[1,3\]*);
//@ requires(***Explanation:** 3 and 1 are peaks.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** mountain = \[4,2,3,2,1\]*);
//@ requires(***Output:** \[1\]*);
//@ requires(***Explanation:** There is only one peak.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `3 <= mountain.length <= 1000`*);
//@ requires(**   `1 <= mountain[i] <= 109`*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(log(n))` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public List<Integer> findPeaks(int[] mountain)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `mountain` is less than or equal to 1000 and is greater than or equal to 3.*);
//@ requires(*All values in the integer array parameter `mountain` are less than or equal to 100000000*);
//@ ensures(*The integer result is either 1 or 0.*);
//@ ensures(*If the string parameter `preorder` is equal to "9,3,4,#,#,1,#,#,2,#,6,#,#", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `preorder` is equal to "1,#", the integer result is equal to 0.*);
//@ ensures(*If the string parameter `preorder` is equal to "9,#,#,1", the integer result is equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to the number of digits in the integer parameter `x`.*);
//@ ensures(*If the integer parameter `x` is equal to 7, the integer result is equal to 1.*);
//@ ensures(*If the integer parameter `x` is equal to 121, the integer result is equal to 2.*);
//@ ensures(*If the integer parameter `x` is equal to 1248, the integer result is equal to 3.*);
    public boolean isValidSerialization(String preorder) {
        int count = 1;
        int length = preorder.length();
        for (int i = 1; i <= length; i++) {
            if (i == length || preorder.charAt(i) == ',') {
                --count;
                if (count < 0) {
                    return false;
                }
                count += preorder.charAt(i - 1) == '#' ? 0 : 2;
            }
        }
        return count == 0;
    }
}