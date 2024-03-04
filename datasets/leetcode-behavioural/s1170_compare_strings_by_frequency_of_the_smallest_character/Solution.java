package g1101_1200.s1170_compare_strings_by_frequency_of_the_smallest_character;

// #Medium #Array #String #Hash_Table #Sorting #Binary_Search
// #2022_03_05_Time_7_ms_(57.89%)_Space_46.5_MB_(35.67%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `queries` and `words` are not null.*);
//@ ensures(*The length of `queries` is greater than or equal to - The length of `words` is greater than or equal to - The length of each string in `queries` is greater than or equal to 1 and less than or equal to - The length of each string in `words` is greater than or equal to 1 and less than or equal to - Each character in `queries` and `words` is a lowercase English letter.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array `answer` is not null.*);
//@ ensures(*The length of `answer` is equal to the length of `queries`.*);
//@ ensures(*Each element in `answer` is an integer.*);
//@ ensures(*The order of elements in `answer` corresponds to the order of queries in `queries`.*);
//@ ensures(*For each query `queries[i]`, `answer[i]` is the number of words in `words` such that `f(queries[i])` < `f(W)` for each `W` in `words`.*);
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] queriesMinFrequecies = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            queriesMinFrequecies[i] = computeLowestFrequency(queries[i]);
        }
        int[] wordsMinFrequecies = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            wordsMinFrequecies[i] = computeLowestFrequency(words[i]);
        }
        Arrays.sort(wordsMinFrequecies);
        int[] result = new int[queries.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = search(wordsMinFrequecies, queriesMinFrequecies[i]);
        }
        return result;
    }

    private int search(int[] nums, int target) {
        int count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > target) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int computeLowestFrequency(String string) {
        char[] str = string.toCharArray();
        Arrays.sort(str);
        String sortedString = new String(str);
        int frequency = 1;
        for (int i = 1; i < sortedString.length(); i++) {
            if (sortedString.charAt(i) == sortedString.charAt(0)) {
                frequency++;
            } else {
                break;
            }
        }
        return frequency;
    }
}