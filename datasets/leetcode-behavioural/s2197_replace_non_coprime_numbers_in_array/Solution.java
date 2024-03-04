package g2101_2200.s2197_replace_non_coprime_numbers_in_array;

// #Hard #Array #Math #Stack #Number_Theory #2022_06_06_Time_60_ms_(85.52%)_Space_88.9_MB_(80.09%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S2234")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 2.*);
//@ ensures(*The values in the input array `nums` are positive integers.*);
//@ ensures(*The values in the input array `nums` are less than or equal to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array is not null.*);
//@ ensures(*The length of the output array is less than or equal to the length of the input array.*);
//@ ensures(*The values in the output array are positive integers.*);
//@ ensures(*The values in the output array are less than or equal to 10^8.*);
//@ ensures(*The output array is obtained by replacing adjacent non-coprime numbers with their LCM.*);
//@ ensures(*The output array is obtained by repeating the process of replacing adjacent non-coprime numbers until no more such numbers are found.*);
//@ ensures(*The output array can be obtained in any arbitrary order of replacing adjacent non-coprime numbers.*);
    public List<Integer> replaceNonCoprimes(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        int i = 1;
        res.add(nums[0]);
        while (i < nums.length) {
            int first = res.get(res.size() - 1);
            int second = nums[i];
            int gcd = gcd(first, second);
            if (gcd > 1) {
                long lcm = ((long) first * (long) second) / gcd;
                if (!res.isEmpty()) {
                    res.remove(res.size() - 1);
                }
                res.add((int) lcm);
                recursivelyCheck(res);
            } else {
                res.add(second);
            }
            i++;
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (a > b) {
            return gcd(b, a);
        }
        if (b % a == 0) {
            return a;
        }
        return gcd(b % a, a);
    }

    private void recursivelyCheck(ArrayList<Integer> list) {
        if (list.size() < 2) {
            return;
        }
        int a = list.remove(list.size() - 1);
        int b = list.remove(list.size() - 1);
        int gcd = gcd(a, b);
        if (gcd > 1) {
            long lcm = ((long) (a) * (long) (b)) / gcd;
            list.add((int) lcm);
            recursivelyCheck(list);
        } else {
            list.add(b);
            list.add(a);
        }
    }
}