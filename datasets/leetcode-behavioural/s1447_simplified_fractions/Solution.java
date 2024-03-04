package g1401_1500.s1447_simplified_fractions;

// #Medium #String #Math #Number_Theory #2022_03_28_Time_33_ms_(69.60%)_Space_67.7_MB_(81.94%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S2234")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` must be an integer greater than or equal to 1 and less than or equal to 100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a list of strings representing simplified fractions.*);
//@ ensures(*The list contains all simplified fractions between 0 and 1 (exclusive) where the denominator is less than or equal to `n`.*);
//@ ensures(*The order of the fractions in the list can be arbitrary.*);
    public List<String> simplifiedFractions(int n) {
        List<String> result = new ArrayList<>();
        if (n == 1) {
            return result;
        }
        StringBuilder str = new StringBuilder();
        for (int denom = 2; denom <= n; denom++) {
            for (int num = 1; num < denom; num++) {
                if (checkGCD(num, denom) == 1) {
                    result.add(str.append(num).append("/").append(denom).toString());
                }
                str.setLength(0);
            }
        }
        return result;
    }

    private int checkGCD(int a, int b) {
        if (a < b) {
            return checkGCD(b, a);
        }
        if (a == b || a % b == 0 || b == 1) {
            return b;
        }
        return checkGCD(a % b, b);
    }
}