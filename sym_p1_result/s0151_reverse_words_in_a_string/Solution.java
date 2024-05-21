package g0101_0200.s0151_reverse_words_in_a_string;

// #Medium #String #Two_Pointers #Udemy_Strings
// #2022_06_25_Time_2_ms_(99.94%)_Space_42.4_MB_(88.57%)

public class Solution {
//@ requires(*Given an input string param_s, reverse the order of the words.*);
//@ requires(*A word is defined as a sequence of non-space characters.*);
//@ requires(*The words in param_s will be separated by at least one space.*);
//@ requires(*Return a string of the words in reverse order concatenated by a single space.*);
//@ requires(*Note that param_s may contain leading or trailing spaces or multiple spaces between two words.*);
//@ requires(*Do not include any extra spaces.*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "the sky is blue"*);
//@ requires(*Output: "blue is sky the"*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = " hello world "*);
//@ requires(*Output: "world hello"*);
//@ requires(*Explanation: Your reversed string should not contain leading or trailing spaces.*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "a good example"*);
//@ requires(*Output: "example good a"*);
//@ requires(*Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.*);
//@ requires(*Example 4:*);
//@ requires(*Input: s = " Bob Loves Alice "*);
//@ requires(*Output: "Alice Loves Bob"*);
//@ requires(*Example 5:*);
//@ requires(*Input: s = "Alice does not even like bob"*);
//@ requires(*Output: "bob like even not does Alice"*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= s.length <= 10<sup>4</sup></code>*);
//@ requires(*param_s contains English letters (upper-case and lower-case), digits, and spaces `' '`.*);
//@ requires(*There is at least one word in param_s.*);
//@ requires(*Follow-up: If the string data type is mutable in your language, can you solve it in-place with `O(1)` extra space?*);
//@ ensures(*The the result ised string should only have a single space separating the words.*);
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