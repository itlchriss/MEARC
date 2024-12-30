package g0101_0200.s0171_excel_sheet_column_number;

// #Easy #Top_Interview_Questions #String #Math #2022_06_26_Time_2_ms_(76.43%)_Space_43_MB_(34.53%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 7 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists only of uppercase English letters.*);
//@ requires(*The string parameter `s` is in the range ["A", "FXSHRXW"].*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1688\. Convert to Title Case*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given a string `title` and an integer `caseStyle`, convert the `title` according to the `caseStyle`.*);
//@ requires(**);
//@ requires(*The rules are as follows:*);
//@ requires(**);
//@ requires(**   `caseStyle == 0` --> Convert to **Lower Case** where the first letter of each word should be in lowercase and the rest of the letters should be in uppercase.*);
//@ requires(*    *   For example, `"Hello World"` will be converted to `"hello world"`.*);
//@ requires(**   `caseStyle == 1` --> Convert to **Upper Case** where all the letters should be in uppercase.*);
//@ requires(*    *   For example, `"Hello World"` will be converted to `"HELLO WORLD"`.*);
//@ requires(**   `caseStyle == 2` --> Convert to **Title Case** where the first letter of each word should be in uppercase and the rest of the letters should be in lowercase.*);
//@ requires(*    *   For example, `"hello world"` will be converted to `"Hello World"`.*);
//@ requires(**   `caseStyle == 3` --> Convert to **Pascal Case** where the first letter of each word should be in uppercase and the rest of the letters should be in lowercase with no spaces between words.*);
//@ requires(*    *   For example, `"hello world"` will be converted to `"HelloWorld"`.*);
//@ requires(**   `caseStyle == 4` --> Convert to **Snake Case** where the first letter of each word should be in lowercase and the rest of the letters should be in lowercase with underscores between words.*);
//@ requires(*    *   For example, `"Hello World"` will be converted to `"hello_world"`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** title = "Hello World", caseStyle = 0*);
//@ requires(**);
//@ requires(***Output:** "hello world"*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** title = "HELLO WORLD", caseStyle = 1*);
//@ requires(**);
//@ requires(***Output:** "HELLO WORLD"*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** title = "hello world", caseStyle = 2*);
//@ requires(**);
//@ requires(***Output:** "Hello World"*);
//@ requires(**);
//@ requires(***Example 4:***);
//@ requires(**);
//@ requires(***Input:** title = "hello world", caseStyle = 3*);
//@ requires(**);
//@ requires(***Output:** "HelloWorld"*);
//@ requires(**);
//@ requires(***Example 5:***);
//@ requires(**);
//@ requires(***Input:** title = "Hello World", caseStyle = 4*);
//@ requires(**);
//@ requires(***Output:** "hello_world"*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= title.length <= 100`*);
//@ requires(**   `title` consists of only English letters and spaces `' '`.*);
//@ requires(**   `title` does not have leading or trailing spaces.*);
//@ requires(**   `0 <= caseStyle <= 4`*);
//@ requires(**);
//@ requires(*Method signature: public String convertToTitleCase(String title, int caseStyle)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(*The length of the string parameter `title` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `title` consists of only English letters and spaces.*);
//@ requires(*The string parameter `title` does not have leading or trailing spaces.*);
//@ requires(*The integer parameter `caseStyle` is less than or equal to 4 and is greater than or equal to 0.*);
//@ requires(*If the integer parameter*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to 2147483647.*);
//@ ensures(*If the string parameter `s` is equal to "A", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to "AB", the integer result is equal to 28.*);
//@ ensures(*If the string parameter `s` is equal to "ZY", the integer result is equal to 701.*);
//@ ensures(*If the string parameter `s` is equal to "FXSHRXW", the integer result is equal to 2147483647.*);
//@ ensures(*If the integer parameter `caseStyle` is equal to 0, the string result will be in lower case.*);
//@ ensures(*If the integer parameter `caseStyle` is equal to 1, the string result will be in upper case.*);
//@ ensures(*If the integer parameter `caseStyle` is equal to 2, the string result will be in title case.*);
//@ ensures(*If the integer parameter `caseStyle` is equal to 3, the string result will be in pascal case.*);
    public int titleToNumber(String s) {
        int num = 0;
        int pow = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            num += (int) Math.pow(26, pow++) * (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}