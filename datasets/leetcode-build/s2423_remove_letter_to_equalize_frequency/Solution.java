package g2401_2500.s2423_remove_letter_to_equalize_frequency;

// #Easy #String #Hash_Table #Counting #2022_11_18_Time_1_ms_(75.41%)_Space_41.9_MB_(50.90%)

@SuppressWarnings("java:S135")
public class Solution {
    private boolean isValidWord(int[] arr) {
        int temp = 0;
        for (int j = 0; j < 26; j++) {
            if (arr[j] == 0) {
                continue;
            }
            if (temp == 0) {
                temp = arr[j];
                continue;
            }
            if (arr[j] != temp) {
                return false;
            }
        }
        return true;
    }
	//@ requires(*The input string `word` must have a length greater than or equal to - The input string `word` must consist of lowercase English letters only.*);
	//@ ensures(*The method should return `true` if it is possible to remove one letter from `word` so that the frequency of all letters in `word` are equal.*);
	//@ ensures(*The method should return `false` if it is not possible to make all present letters have equal frequency after removing one letter.*);

    public boolean equalFrequency(String word) {
        boolean ans = false;
        // frequency array
        int[] arr = new int[26];
        for (int i = 0; i < word.length(); i++) {
            arr[word.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                arr[i] -= 1;
                if (isValidWord(arr)) {
                    ans = true;
                    break;
                }
                arr[i] += 1;
            }
        }
        return ans;
    }
}