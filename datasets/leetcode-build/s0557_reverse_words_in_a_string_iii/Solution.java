package g0501_0600.s0557_reverse_words_in_a_string_iii;

// #Easy #String #Two_Pointers #Algorithm_I_Day_4_Two_Pointers
// #2022_08_03_Time_4_ms_(97.75%)_Space_42.5_MB_(97.15%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is at least 1.*);
	//@ requires(*The input string `s` does not contain any leading or trailing spaces.*);
	//@ requires(*The input string `s` contains printable ASCII characters.*);
	//@ requires(*The input string `s` contains at least one word.*);
	//@ requires(*All the words in the input string `s` are separated by a single space.*);
	//@ ensures(*The output string is not null.*);
	//@ ensures(*The length of the output string is the same as the input string.*);
	//@ ensures(*The order of characters in each word within the output string is reversed.*);
	//@ ensures(*The whitespace and initial word order in the input string are preserved in the output string.*);
    public String reverseWords(String s) {
        int l;
        int r = 0;
        int len = s.length();
        char[] ch = s.toCharArray();
        for (int i = 0; i <= len; i++) {
            if (i == len || ch[i] == ' ') {
                l = r;
                r = i;
                reverse(ch, l, r - 1);
                r++;
            }
        }
        return new String(ch);
    }

    private void reverse(char[] s, int l, int r) {
        char c;
        while (r > l) {
            c = s[l];
            s[l++] = s[r];
            s[r--] = c;
        }
    }
}