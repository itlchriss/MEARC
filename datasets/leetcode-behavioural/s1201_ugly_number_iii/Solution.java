package g1201_1300.s1201_ugly_number_iii;

// #Medium #Math #Binary_Search #Number_Theory #Binary_Search_II_Day_20
// #2022_03_08_Time_0_ms_(100.00%)_Space_41.8_MB_(7.39%)

public class Solution {

    private long getLcm(long a, long b) {
        long mx = a;
        long mn = b;
        if (a < b) {
            mx = b;
            mn = a;
        }
        while (mn != 0) {
            long tmp = mn;
            mn = mx % mn;
            mx = tmp;
        }
        return (a * b) / mx;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `n`, `a`, `b`, and `c` must be positive.*);
//@ ensures(*The input integers `a`, `b`, and `c` must be greater than or equal to 1.*);
//@ ensures(*The input integers `a`, `b`, and `c` must be less than or equal to 10^9.*);
//@ ensures(*The product of `a`, `b`, and `c` must be greater than or equal to 1.*);
//@ ensures(*The product of `a`, `b`, and `c` must be less than or equal to 10^18.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer that is the `n`th ugly number.*);
//@ ensures(*The returned ugly number should be divisible by `a`, `b`, or `c`.*);
//@ ensures(*The returned ugly number should be in the range [1, 2 * 10^9].*);

    public int nthUglyNumber(int n, int a, int b, int c) {
        long ab = getLcm(a, b);
        long ac = getLcm(a, c);
        long bc = getLcm(b, c);
        long abc = getLcm(a, bc);

        long left = 1;
        long right = 2000000001;
        if (a != 0 && b != 0 && c != 0 && bc != 0) {
            while (left < right) {
                long mid = left + (right - left) / 2;
                if (mid / a + mid / b + mid / c - mid / ab - mid / ac - mid / bc + mid / abc >= n) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return (int) left;
    }
}