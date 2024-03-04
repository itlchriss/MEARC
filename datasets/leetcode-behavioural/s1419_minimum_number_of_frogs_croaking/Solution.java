package g1401_1500.s1419_minimum_number_of_frogs_croaking;

// #Medium #String #Counting #2022_03_26_Time_19_ms_(32.16%)_Space_42.6_MB_(86.27%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is between 1 and 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of different frogs required to finish all the croaks in the given string.*);
//@ ensures(*If the given string is not a combination of a valid "croak", the method returns -1.*);
    public int minNumberOfFrogs(String s) {
        int ans = 0;
        int[] f = new int[26];
        for (int i = 0; i < s.length(); i++) {
            f[s.charAt(i) - 'a']++;
            if (s.charAt(i) == 'k' && checkEnough(f)) {
                reduce(f);
            }
            if (!isValid(f)) {
                return -1;
            }
            ans = Math.max(ans, getMax(f));
        }
        return isEmpty(f) ? ans : -1;
    }

    private boolean checkEnough(int[] f) {
        return f['c' - 'a'] > 0
                && f['r' - 'a'] > 0
                && f['o' - 'a'] > 0
                && f[0] > 0
                && f['k' - 'a'] > 0;
    }

    public void reduce(int[] f) {

        f['c' - 'a']--;
        f['r' - 'a']--;
        f['o' - 'a']--;
        f[0]--;
        f['k' - 'a']--;
    }

    private int getMax(int[] f) {
        int max = 0;
        for (int v : f) {
            max = Math.max(max, v);
        }
        return max;
    }

    private boolean isEmpty(int[] f) {
        for (int v : f) {
            if (v > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(int[] f) {
        if (f['r' - 'a'] > f['c' - 'a']) {
            return false;
        }
        if (f['o' - 'a'] > f['r' - 'a']) {
            return false;
        }
        if (f[0] > f['o' - 'a']) {
            return false;
        }
        return f['k' - 'a'] <= f[0];
    }
}