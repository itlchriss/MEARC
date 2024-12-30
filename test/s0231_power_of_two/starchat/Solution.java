package g0201_0300.s0231_power_of_two;

// #Easy #Math #Bit_Manipulation #Recursion #Algorithm_I_Day_13_Bit_Manipulation
// #2022_07_04_Time_1_ms_(100.00%)_Space_39.6_MB_(90.19%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ requires(*The method should not use loops or recursion to solve the problem.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 234. Number of Segments in a String*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*You are given a string `s`, return _the number of segments in the string_.*);
//@ requires(**);
//@ requires(*A segment is defined to be a contiguous sequence of non-space characters.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "Hello, my name is John "*);
//@ requires(***Output:** 5*);
//@ requires(***Explanation:** The five segments are \[ "Hello, ",  "my ",  "name ",  "is ",  "John "\]*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "Hello "*);
//@ requires(***Output:** 1*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** s =  "love live! mu'sic forever "*);
//@ requires(***Output:** 4*);
//@ requires(**);
//@ requires(***Example 4:***);
//@ requires(**);
//@ requires(***Input:** s =  ""*);
//@ requires(***Output:** 0*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `0 <= s.length <= 300`*);
//@ requires(**   `s` consists of lower-case and upper-case English letters, digits or one of the following characters `"!@#$%^&*()_+-=',.:;"`*);
//@ requires(**   The only space character in `s` is `' '`.*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve it in `O(1)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int countSegments(String s)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The string parameter `s` is not null.*);
//@ requires(*The length of the string parameter `s` is less than or equal to 300.*);
//@ requires(*The string parameter `s` consists of lower-case and upper-case English letters, digits or one of the following characters `"!@#$%^&*()_+-=',.:;"`*);
//@ requires(*The only space character in `s` is `' '`.*);
//@ requires(*The method should not use any built-in string manipulation functions or regular expressions to solve the problem.*);
//@ requires(*The method should solve the problem in O(n) time complexity, where n is the length of the string parameter `s`.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 235. Reverse Vowels of a String*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given a string `s`, reverse only the vowels of a string and return it.*);
//@ requires(**);
//@ requires(*The vowels are `'a'`, `'e'`, `'i'`, `'o'`, and `'u'`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "hello "*);
//@ requires(***Output:**  "holle "*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "leetcode "*);
//@ requires(***Output:**  "leotcede "*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 3 * 105`*);
//@ requires(**   `s` consists of printable ASCII characters.*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve it in `O(n)` time and `O(1)` extra space?*);
//@ requires(**);
//@ requires(*Method signature: public String reverseVowels(String s)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format*);
//@ ensures(*If the integer parameter `n` is equal to 1, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 16, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 3, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 4, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 5, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is a power of two, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is not a power of two, the boolean result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to "Hello, my name is John ", the integer result is equal to 5.*);
//@ ensures(*If the string parameter `s` is equal to "Hello ", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to "love live! mu'sic forever ", the integer result is equal to 4.*);
//@ ensures(*If the string parameter `s` is an empty string, the integer result is equal to 0.*);
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (true) {
            if (n == 1) {
                return true;
            }
            if (n % 2 == 1) {
                return false;
            }
            n /= 2;
        }
    }
}