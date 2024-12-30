package g0101_0200.s0165_compare_version_numbers;

// #Medium #String #Two_Pointers #2022_06_25_Time_1_ms_(88.88%)_Space_41.8_MB_(66.14%)

public class Solution {
//@ requires(*The length of the string parameter `version1` is less than or equal to 500 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `version2` is less than or equal to 500 and is greater than or equal to 1.*);
//@ requires(*The string parameter `version1` and `version2` only contain digits and '.'.*);
//@ requires(*The string parameter `version1` and `version2` are valid version numbers.*);
//@ requires(*All the given revisions in `version1` and `version2` can be stored in a 32-bit integer.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2521\. Count the Digits That Divide a Number and Are Prime*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given an integer `num`, return _the count of digits in_ `num` _that divide_ `num` _and are prime numbers_.*);
//@ requires(**);
//@ requires(*A **prime number** is an integer greater than `1` that has no positive divisors other than `1` and itself.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** num = 123*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** The only prime number that divides 123 is 3.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** num = 456*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** There are no prime numbers that divide 456.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** num = 789*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** There are no prime numbers that divide 789.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= num <= 108`*);
//@ requires(**);
//@ requires(*Method signature: public int countPrimeDigits(int num)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The integer parameter `num` is less than or equal to 100000000 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2522\. Count the Digits That Divide a Number and Are Composite*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given an integer `num`, return _the count of digits in_ `num` _that divide_ `num` _and are composite numbers_.*);
//@ requires(**);
//@ requires(*A **composite number** is a positive integer that has at least one positive divisor other than `1` and itself.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** num = 123*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** The only composite number that divides 123 is 3.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** num = 456*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** There are no composite numbers that divide 456.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** num = 789*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** There are no composite numbers that divide 789.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= num <= 108`*);
//@ requires(**);
//@ requires(*Method signature: public int countCompositeDigits(int num)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The integer parameter `num` is less than or equal to*);
//@ ensures(*If the string parameter `version1` is equal to "1.01" and the string parameter `version2` is equal to "1.001", the integer result is equal to 0.*);
//@ ensures(*If the string parameter `version1` is equal to "1.0" and the string parameter `version2` is equal to "1.0.0", the integer result is equal to 0.*);
//@ ensures(*If the string parameter `version1` is equal to "0.1" and the string parameter `version2` is equal to "1.1", the integer result is equal to -1.*);
//@ ensures(*If the string parameter `version1` is equal to "1.0.1" and the string parameter `version2` is equal to "1", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `version1` is equal to "7.5.2.4" and the string parameter `version2` is equal to "7.5.3", the integer result is equal to -1.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the number of digits in the integer parameter `num`.*);
//@ ensures(*If the integer parameter `num` is equal to 123, the integer result is equal to 1.*);
//@ ensures(*If the integer parameter `num` is equal to 456, the integer result is equal to 0.*);
//@ ensures(*If the integer parameter `num` is equal to 789, the integer result is equal to 0.*);
    public int compareVersion(String version1, String version2) {
        // acquire first number
        int numA = 0;
        int i;
        for (i = 0; i < version1.length(); i++) {
            char c = version1.charAt(i);
            if (c == '.') {
                break;
            } else {
                numA = numA * 10 + (c - 48);
            }
        }
        // acquire second number
        int numB = 0;
        int j;
        for (j = 0; j < version2.length(); j++) {
            char c = version2.charAt(j);
            if (c == '.') {
                break;
            } else {
                numB = numB * 10 + (c - 48);
            }
        }
        // compare
        if (numA > numB) {
            return 1;
        } else if (numA < numB) {
            return -1;
        } else {
            // equal
            String v1 = "";
            String v2 = "";
            if (i != version1.length()) {
                v1 = version1.substring(i + 1);
            }
            if (j != version2.length()) {
                v2 = version2.substring(j + 1);
            }
            // if both versions end here, they are equal
            if (v1.equals("") && v2.equals("")) {
                return 0;
            } else {
                return compareVersion(v1, v2);
            }
        }
    }
}