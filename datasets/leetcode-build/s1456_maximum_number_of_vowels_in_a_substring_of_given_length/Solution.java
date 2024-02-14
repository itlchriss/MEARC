package g1401_1500.s1456_maximum_number_of_vowels_in_a_substring_of_given_length;

// #Medium #String #Sliding_Window #2022_03_28_Time_19_ms_(53.73%)_Space_47.8_MB_(64.37%)

public class Solution {
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` is not empty.*);
	//@ requires(*The input integer `k` is greater than 0.*);
	//@ requires(*The input integer `k` is less than or equal to the length of the string `s`.*);
	//@ ensures(*The output integer is the maximum number of vowel letters in any substring of `s` with length `k`.*);
	//@ ensures(*The output integer is greater than or equal to 0.*);
	//@ ensures(*The output integer is less than or equal to `k`.*);
	//@ ensures(*The output integer is the maximum possible number of vowel letters in any substring of `s` with length `k`.*);

    public int maxVowels(String s, int k) {
        int maxVowelCount = 0;
        int vowelCount = 0;

        int i = 0;
        int j = 0;

        while (j < s.length()) {

            char c = s.charAt(j);
            if (isVowel(c)) {
                vowelCount++;
            }

            if ((j - i + 1) == k) {
                maxVowelCount = Math.max(maxVowelCount, vowelCount);
                if (isVowel(s.charAt(i))) {
                    vowelCount--;
                }
                i++;
            }

            j++;
        }

        return maxVowelCount;
    }
}