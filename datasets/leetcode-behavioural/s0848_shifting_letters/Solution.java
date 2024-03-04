package g0801_0900.s0848_shifting_letters;

// #Medium #Array #String #2022_03_24_Time_11_ms_(88.11%)_Space_55.9_MB_(79.10%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of the string `s` is greater than or equal to 1.*);
//@ ensures(*The length of the integer array `shifts` is equal to the length of the string `s`.*);
//@ ensures(*Each element in the integer array `shifts` is greater than or equal to 0.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string is the final string after all shifts are applied.*);
//@ ensures(*The length of the returned string is equal to the length of the input string `s`.*);
//@ ensures(*Each character in the returned string is a lowercase English letter.*);
    public String shiftingLetters(String s, int[] shifts) {
        int n = shifts.length;
        int runningSum = 0;
        for (int i = n - 1; i >= 0; i--) {
            shifts[i] = (shifts[i] + runningSum) % 26;
            runningSum = shifts[i];
        }
        StringBuilder str = new StringBuilder();
        int i = 0;
        for (char c : s.toCharArray()) {
            int correctShift = (c - 'a' + shifts[i]) % 26;
            str.append((char) ('a' + correctShift));
            i++;
        }
        return str.toString();
    }
}