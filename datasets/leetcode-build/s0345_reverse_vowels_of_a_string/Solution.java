package g0301_0400.s0345_reverse_vowels_of_a_string;

// #Easy #String #Two_Pointers #2022_07_11_Time_3_ms_(98.02%)_Space_42.2_MB_(98.08%)

public class Solution {
    private boolean isVowel(char c) {
        return c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o'
                || c == 'O' || c == 'u' || c == 'U';
    }
	//@ requires(*1. The input string `str` is not null.*);
	//@ requires(*2. The input string `str` is not empty.*);
	//@ requires(*3. The input string `str` consists of printable ASCII characters.*);
	//@ ensures(*1. The returned string is not null.*);
	//@ ensures(*2. The returned string has the same length as the input string `str`.*);
	//@ ensures(*3. The returned string has the vowels reversed while keeping the non-vowel characters in their original positions.*);
	//@ ensures(*4. The returned string contains the vowels in both cases, i.e., uppercase and lowercase vowels are reversed correctly.*);

    public String reverseVowels(String str) {
        int i = 0;
        int j = str.length() - 1;
        char[] str1 = str.toCharArray();
        while (i < j) {
            if (!isVowel(str1[i])) {
                i++;
            } else if (!isVowel(str1[j])) {
                j--;
            } else {
                // swapping
                char t = str1[i];
                str1[i] = str1[j];
                str1[j] = t;
                i++;
                j--;
            }
        }
        return String.copyValueOf(str1);
    }
}