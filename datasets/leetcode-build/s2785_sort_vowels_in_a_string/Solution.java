package g2701_2800.s2785_sort_vowels_in_a_string;

// #Medium #String #Sorting #2023_09_15_Time_5_ms_(100.00%)_Space_44.6_MB_(96.53%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` is not empty.*);
	//@ requires(*The input string `s` consists only of letters of the English alphabet in uppercase and lowercase.*);
	//@ ensures(*The output string `t` is not null.*);
	//@ ensures(*The output string `t` has the same length as the input string `s`.*);
	//@ ensures(*The consonants in the output string `t` remain in their original places.*);
	//@ ensures(*The vowels in the output string `t` are sorted in nondecreasing order of their ASCII values.*);
	//@ ensures(*The output string `t` consists only of letters of the English alphabet in uppercase and lowercase.*);
    public String sortVowels(String s) {
        int[] vowelCount = new int[11];
        int[] countIndexMap = new int[128];
        char[] result = s.toCharArray();
        char[] charMap = "AEIOUaeiou".toCharArray();
        for (int i = 0; i < charMap.length; i++) {
            countIndexMap[charMap[i]] = i + 1;
        }
        for (char c : result) {
            vowelCount[countIndexMap[c]]++;
        }
        int j = 1;
        int i = 0;
        while (j < vowelCount.length) {
            if (vowelCount[j] > 0) {
                while (i < result.length) {
                    if (countIndexMap[result[i]] == 0) {
                        i++;
                    } else {
                        vowelCount[j]--;
                        result[i++] = charMap[j - 1];
                        break;
                    }
                }
            } else {
                j++;
            }
        }
        return new String(result);
    }
}