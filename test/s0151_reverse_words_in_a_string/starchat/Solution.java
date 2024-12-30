package g0101_0200.s0151_reverse_words_in_a_string;

// #Medium #String #Two_Pointers #Udemy_Strings
// #2022_06_25_Time_2_ms_(99.94%)_Space_42.4_MB_(88.57%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of English letters (upper-case and lower-case), digits, and spaces '.*);
//@ requires(*The string parameter `s` may contain leading or trailing spaces or multiple spaces between two words.*);
//@ requires(*The returned string should only have a single space separating the words.*);
//@ requires(*Do not include any extra spaces in the returned string.*);
//@ requires(*The returned string should be in reverse order of the words.*);
//@ requires(*If the string parameter `s` is equal to "the sky is blue", the returned string is equal to "blue is sky the".*);
//@ requires(*If the string parameter `s` is equal to " hello world ", the returned string is equal to "world hello".*);
//@ requires(*If the string parameter `s` is equal to "a good example", the returned string is equal to "example good a".*);
//@ requires(*If the string parameter `s` is equal to " Bob Loves Alice ", the returned string is equal to "Alice Loves Bob".*);
//@ requires(*If the string parameter `s` is equal to "Alice does not even like bob", the returned string is equal to "bob like even not does Alice".*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 186\. Reverse Words III*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given a string `s`, reverse the order of words.*);
//@ requires(**);
//@ requires(*A **word** is defined as a sequence of non-space characters. The **words** in `s` will be separated by at least one space.*);
//@ requires(**);
//@ requires(*Return _a string of the words in reverse order concatenated by a single space_.*);
//@ requires(**);
//@ requires(***Note** that `s` may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s = "the sky is blue"*);
//@ requires(**);
//@ requires(***Output:** "blue is sky the" *);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s = " hello world "*);
//@ requires(**);
//@ requires(***Output:** "world hello"*);
//@ requires(**);
//@ requires(***Explanation:** Your reversed string should not contain leading or trailing spaces. *);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** s = "a good example"*);
//@ requires(**);
//@ requires(***Output:** "example good a"*);
//@ requires(**);
//@ requires(***Explanation:** You need to reduce multiple spaces between two words to a single space in the reversed string. *);
//@ requires(**);
//@ requires(***Example 4:***);
//@ requires(**);
//@ requires(***Input:** s = "  Bob    Loves  Alice   "*);
//@ requires(**);
//@ requires(***Output:** "Alice Loves Bob"*);
//@ requires(**);
//@ requires(***Example 5:***);
//@ requires(**);
//@ requires(***Input:** s = "Alice does not even like bob"*);
//@ requires(**);
//@ requires(***Output:** "bob like even not does Alice" *);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 104`*);
//@ requires(**   `s` contains English letters (upper-case and lower-case), digits, and spaces `' '`.*);
//@ requires(**   There is **at least one** word in `s`.*);
//@ requires(**);
//@ requires(***Follow-up:** If the string data type is mutable in your language, can you solve it **in-place** with `O(1)` extra space?*);
//@ requires(**);
//@ requires(*Method signature: public String reverseWordsIII(String s)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of English letters (upper-case and lower-case), digits, and spaces '.*);
//@ requires(*The string parameter `s` may contain leading or trailing spaces or multiple spaces between two words.*);
//@ requires(*The returned string should only have a single space separating the words.*);
//@ requires(*Do not include any extra spaces in the returned string.*);
//@ requires(*The returned string should be in reverse order of the words.*);
//@ requires(*If the string parameter `s` is equal to "the sky is blue", the returned string is equal to "blue is sky the".*);
//@ requires(*If the string parameter `s` is equal to " hello world ", the returned string is equal to "world hello".*);
//@ requires(*If the string parameter `s` is equal to "a good example", the returned string is equal to "example good a".*);
//@ requires(*If the string parameter `s` is equal to " Bob Loves Alice ", the returned string is equal to "Alice Loves Bob".*);
//@ requires(*If the string parameter `s` is equal to "Alice does not even like bob", the returned string is equal to "bob like even not does Alice".*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software*);
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                i--;
                continue;
            }
            int start = s.lastIndexOf(' ', i);
            sb.append(' ');
            sb.append(s, start + 1, i + 1);
            i = start - 1;
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}