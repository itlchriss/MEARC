package g1201_1300.s1255_maximum_score_words_formed_by_letters;

// #Hard #Array #String #Dynamic_Programming #Bit_Manipulation #Backtracking #Bitmask
// #2022_03_12_Time_1_ms_(98.36%)_Space_39.9_MB_(69.95%)

public class Solution {
    private int[] score;

    private int updateArr(int[] arr, String s, int add) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            int ind = c - 'a';
            arr[ind] += add;
            if (arr[ind] < 0) {
                sum = -1;
            }
            if (sum != -1) {
                sum += this.score[ind];
            }
        }
        return sum;
    }

    private int findMaxScore(String[] words, int ind, int[] arr) {
        if (ind == words.length) {
            return 0;
        }
        int excl = findMaxScore(words, ind + 1, arr);
        int incl = 0;
        int cscore = updateArr(arr, words[ind], -1);
        if (cscore != -1) {
            incl = cscore + findMaxScore(words, ind + 1, arr);
        }
        updateArr(arr, words[ind], 1);
        return Math.max(incl, excl);
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The length of the `words` array is greater than or equal to 1.*);
//@ ensures(*The length of each word in the `words` array is greater than or equal to 1 and less than or equal to 15.*);
//@ ensures(*The length of the `letters` array is greater than or equal to 1 and less than or equal to 100.*);
//@ ensures(*Each element in the `letters` array is a single lowercase English letter.*);
//@ ensures(*The length of the `score` array is 26.*);
//@ ensures(*Each element in the `score` array is an integer between 0 and 10 (inclusive).*);
//@ ensures(*Each word in the `words` array can only be used once.*);
//@ ensures(*Each letter in the `letters` array can only be used once.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum score of any valid set of words formed using the given letters.*);
//@ ensures(*The maximum score is calculated by summing the scores of the letters in each word.*);
//@ ensures(*The score of each letter is determined by the corresponding element in the `score` array.*);
//@ ensures(*If no valid set of words can be formed using the given letters, the method returns 0.*);

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] arr = new int[26];
        for (char c : letters) {
            arr[c - 'a']++;
        }
        this.score = score;
        return findMaxScore(words, 0, arr);
    }
}