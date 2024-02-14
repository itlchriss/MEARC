package g1901_2000.s2000_reverse_prefix_of_word;

// #Easy #String #Two_Pointers #2022_05_09_Time_0_ms_(100.00%)_Space_40.7_MB_(79.75%)

public class Solution {
	//@ requires(*The input string `word` is not null.*);
	//@ requires(*The input character `ch` is a lowercase English letter.*);
	//@ ensures(*If the character `ch` exists in `word`, the segment of `word` from index 0 to the index of the first occurrence of `ch` (inclusive) is reversed.*);
	//@ ensures(*If the character `ch` does not exist in `word`, the string remains unchanged.*);
    public String reversePrefix(String word, char ch) {
        int i = 0;
        int j = word.indexOf(ch);
        char[] charArr = word.toCharArray();
        while (i < j) {
            char temp = charArr[i];
            charArr[i] = charArr[j];
            charArr[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(charArr);
    }
}