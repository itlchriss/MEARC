package g2701_2800.s2767_partition_string_into_minimum_beautiful_substrings;

// #Medium #String #Hash_Table #Dynamic_Programming #Backtracking
// #2023_09_24_Time_1_ms_(100.00%)_Space_40.8_MB_(89.80%)

public class Solution {
    private boolean ispower(int num) {
        int pow = 1;
        while (pow < num) {
            pow = pow * 5;
        }
        return pow == num;
    }

    private int backtrack(int ind, String s) {
        if (ind >= s.length()) {
            return 0;
        }
        if (s.charAt(ind) == '0') {
            return 999;
        }
        int temp = 999;
        int runCount = 0;
        for (int i = ind; i < s.length(); i++) {
            runCount = runCount * 2;
            if (s.charAt(i) == '1') {
                runCount++;
            }
            if (this.ispower(runCount)) {
                temp = Math.min(temp, 1 + this.backtrack(i + 1, s));
            }
        }
        return temp;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is between 1 and 15 (inclusive).*);
//@ ensures(*Each character in the input string `s` is either '0' or '1'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of beautiful substrings that `s` can be partitioned into.*);
//@ ensures(*If it is impossible to partition the string `s` into beautiful substrings, the method returns -1.*);

    public int minimumBeautifulSubstrings(String s) {
        int res = this.backtrack(0, s);
        if (res < 999) {
            return res;
        }
        return -1;
    }
}