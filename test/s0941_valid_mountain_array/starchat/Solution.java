package g0901_1000.s0941_valid_mountain_array;

// #Easy #Array #2022_03_30_Time_1_ms_(100.00%)_Space_43_MB_(93.41%)

public class Solution {
//@ requires(*The length of the integer array parameter `arr` is less than or equal to 10000 and is greater than or equal to 3.*);
//@ requires(*The integer array parameter `arr` consists of only non-negative integers.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1044\. Strange Printer*);
//@ requires(*Description are as below: There is a strange printer with the following two special properties:*);
//@ requires(*The printer can only print a sequence of **the same character** each time.*);
//@ requires(*At each turn, the printer can print new characters starting from and ending at any place and will cover the old characters.*);
//@ requires(**);
//@ requires(*Given a string `s`, return _the minimum number of turns the printer needed to print it_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aaabbb "*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** Print  "aaa " first and then print  "bbb ".*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "aba "*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** Print  "aaa " first and then print  "b " from the second place of the string, which will cover the existing character 'a'.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 100`*);
//@ requires(**   `s` consists of lowercase English letters.*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve the problem in `O(n)` time complexity?*);
//@ ensures(*The integer result is either true or false.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,1], the integer result is equal to false.*);
//@ ensures(*If the integer array parameter `arr` is equal to [3,5,5], the integer result is equal to false.*);
//@ ensures(*If the integer array parameter `arr` is equal to [0,3,2,1], the integer result is equal to true.*);
    public boolean validMountainArray(int[] arr) {
        int i = 0;
        for (; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return false;
            } else if (arr[i] > arr[i + 1]) {
                break;
            }
        }
        if (i == 0 || i >= arr.length - 1) {
            return false;
        }
        for (; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return false;
            }
        }
        return i == arr.length - 1;
    }
}