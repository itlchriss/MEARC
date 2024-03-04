package g0901_1000.s0978_longest_turbulent_subarray;

// #Medium #Array #Dynamic_Programming #Sliding_Window
// #2022_03_31_Time_7_ms_(48.10%)_Space_67.3_MB_(35.67%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `arr` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer representing the length of the maximum size turbulent subarray of `arr`.*);
//@ ensures(*The returned value is greater than or equal to 1.*);
//@ ensures(*The returned value is less than or equal to the length of the input array `arr`.*);
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ans = 1;
        int l;
        int r;
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return arr[0] == arr[1] ? 1 : 2;
        }
        for (l = 0, r = 1; r < n - 1; r++) {
            int difL = arr[r] - arr[r - 1];
            int difR = arr[r] - arr[r + 1];
            if (difL == 0 && difR == 0) {
                l = r + 1;
            } else if (difL == 0) {
                ans = Math.max(ans, r - l);
                l = r;
            } else if (!((difL < 0 && difR < 0) || (difL > 0 && difR > 0))) {
                ans = Math.max(ans, r - l + 1);
                l = r;
            }
        }
        return Math.max(ans, r - l + 1);
    }
}