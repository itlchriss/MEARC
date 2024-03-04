package g2001_2100.s2024_maximize_the_confusion_of_an_exam;

// #Medium #String #Binary_Search #Prefix_Sum #Sliding_Window
// #2022_05_25_Time_21_ms_(44.78%)_Space_48.4_MB_(29.41%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `answerKey` should not be null.*);
//@ ensures(*The length of `answerKey` should be greater than or equal to - The value of `k` should be greater than or equal to - The value of `k` should be less than or equal to the length of `answerKey`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer value representing the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most `k` times.*);
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int max;
        int right = 0;
        int originalK = k;
        while (k > 0 && right < answerKey.length()) {
            if (answerKey.charAt(right) == 'T') {
                k--;
            }
            right++;
        }
        max = right;
        int left = 0;
        while (right < answerKey.length() && left < answerKey.length()) {
            if (answerKey.charAt(right) == 'F') {
                right++;
                max = Math.max(max, right - left);
            } else {
                while (left < right && answerKey.charAt(left) == 'F') {
                    left++;
                }
                left++;
                right++;
            }
        }
        right = 0;
        k = originalK;
        while (k > 0 && right < answerKey.length()) {
            if (answerKey.charAt(right) == 'F') {
                k--;
            }
            right++;
        }
        max = Math.max(max, right);
        left = 0;
        while (right < answerKey.length() && left < answerKey.length()) {
            if (answerKey.charAt(right) == 'T') {
                right++;
                max = Math.max(max, right - left);
            } else {
                while (left < right && answerKey.charAt(left) == 'T') {
                    left++;
                }
                left++;
                right++;
            }
        }
        return max;
    }
}