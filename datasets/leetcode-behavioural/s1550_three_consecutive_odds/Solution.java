package g1501_1600.s1550_three_consecutive_odds;

// #Easy #Array #2022_04_11_Time_0_ms_(100.00%)_Space_43.3_MB_(21.06%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 3.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if there are three consecutive odd numbers in the array.*);
//@ ensures(*The method returns `false` if there are no three consecutive odd numbers in the array.*);
    public boolean threeConsecutiveOdds(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] % 2 == 1 && arr[i + 1] % 2 == 1 && arr[i + 2] % 2 == 1) {
                return true;
            }
        }
        return false;
    }
}