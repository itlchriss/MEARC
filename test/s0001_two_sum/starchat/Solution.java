package g0001_0100.s0001_two_sum;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table
// #Data_Structure_I_Day_2_Array #Level_1_Day_13_Hashmap #Udemy_Arrays #Big_O_Time_O(n)_Space_O(n)
// #2024_01_04_Time_2_ms_(85.97%)_Space_44.8_MB_(15.45%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of the integer array parameter `numbers` is less than or equal to 10000 and is greater than or equal to 2.*);
//@ requires(*All values in the integer array parameter `numbers` are less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ requires(*The integer parameter `target` is less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ requires(*If there are multiple valid answers, return any one of them.*);
//@ requires(**);
//@ requires(*Example #1*);
//@ requires(**);
//@ requires(*Software specification: *);
//@ requires(*2\. Add Two Numbers*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order**, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.*);
//@ requires(**);
//@ requires(*You may assume the two numbers do not contain any leading zero, except the number 0 itself.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** l1 = \[2,4,3\], l2 = \[5,6,4\]*);
//@ requires(**);
//@ requires(***Output:** \[7,0,8\]*);
//@ requires(**);
//@ requires(***Explanation:** 342 + 465 = 807.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** l1 = \[0\], l2 = \[0\]*);
//@ requires(**);
//@ requires(***Output:** \[0\]*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** l1 = \[9,9,9,9,9,9,9\], l2 = \[9,9,9,9\]*);
//@ requires(**);
//@ requires(***Output:** \[8,9,9,9,0,0,0,1\]*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   The number of nodes in each linked list is in the range `[1, 100]`.*);
//@ requires(**   `0 <= Node.val <= 9`*);
//@ requires(**   It is **guaranteed** that the list represents a **number** that does not have leading zeros.*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve it in one pass?*);
//@ requires(**);
//@ requires(*Method signature: public ListNode addTwoNumbers(ListNode l1, ListNode l2)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The two linked list parameters `l1` and `l2` are non-empty.*);
//@ requires(*The number of nodes in each linked list parameter is in the range `[1, 100]`.*);
//@ requires(*The value of each node in each linked list parameter is in the range `[0, 9]`.*);
//@ requires(*The linked list parameter `l1` and `l2` do not contain any leading zeros.*);
//@ requires(*If there are multiple valid answers, return any one of them.*);
//@ requires(**);
//@ requires(*Example #1*);
//@ requires(**);
//@ requires(*Software specification: *);
//@ requires(*3\. Longest Substring Without Repeating Characters*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given a string `s`, find the length of the **longest substring** without repeating characters.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "abcabcbb "*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** The answer is  "abc ", with the length of 3.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "bbbbb "*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** The answer is  "b ", with the length of 1.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** s =  "pwwkew "*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** The*);
//@ ensures(*The integer array result is of length 2.*);
//@ ensures(*The values in the integer array result are less than or equal to the length of the integer array parameter `numbers` and is greater than or equal to 0.*);
//@ ensures(*The sum of the values at the indices in the integer array result is equal to the integer parameter `target`.*);
//@ ensures(*The values at the indices in the integer array result are unique.*);
//@ ensures(*The values at the indices in the integer array result are in ascending order.*);
//@ ensures(*The linked list result is non-empty.*);
//@ ensures(*The number of nodes in the linked list result is in the range `[1, 101]`.*);
//@ ensures(*The value of each node in the linked list result is in the range `[0, 9]`.*);
//@ ensures(*The linked list result does not contain any leading zeros.*);
//@ ensures(*The sum of the values of the nodes in the linked list result is equal to the sum of the values of the nodes in the linked list parameters `l1` and `l2`.*);
//@ ensures(*The linked list result is in the reverse order.*);
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        //@ loop_invariant 0 <= i <= numbers.length;
        for (int i = 0; i < numbers.length; i++) {
            // assume Integer.MIN_VALUE + 1 <= target - numbers[i] <= Integer.MAX_VALUE - 1;
            Integer requiredNum = target - numbers[i];
            if (indexMap.containsKey(requiredNum)) {
                return new int[] {indexMap.get(requiredNum), i};
            }
            indexMap.put(numbers[i], i);
        }
        return new int[] {-1, -1};
    }
}