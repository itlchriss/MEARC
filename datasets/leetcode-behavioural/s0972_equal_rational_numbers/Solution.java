package g0901_1000.s0972_equal_rational_numbers;

// #Hard #String #Math #2022_03_31_Time_4_ms_(56.67%)_Space_43.5_MB_(6.67%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input strings `s` and `t` are non-null.*);
//@ ensures(*The input strings `s` and `t` represent non-negative rational numbers.*);
//@ ensures(*The input strings `s` and `t` follow the specified format for representing rational numbers.*);
//@ ensures(*The `<IntegerPart>` of `s` and `t` does not have leading zeros (except for the zero itself).*);
//@ ensures(*The length of the `<IntegerPart>` of `s` and `t` is between 1 and 4 (inclusive).*);
//@ ensures(*The length of the `<NonRepeatingPart>` of `s` and `t` is between 0 and 4 (inclusive).*);
//@ ensures(*The length of the `<RepeatingPart>` of `s` and `t` is between 1 and 4 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if and only if `s` and `t` represent the same rational number.*);
//@ ensures(*The method correctly identifies when the repeating part of the rational number is denoted within a pair of round brackets.*);
//@ ensures(*The method correctly compares the non-repeating parts of `s` and `t` when they are present.*);
//@ ensures(*The method correctly compares the repeating parts of `s` and `t` when they are present.*);
//@ ensures(*The method correctly handles cases where the non-repeating part of `s` or `t` is missing.*);
//@ ensures(*The method correctly handles cases where the repeating part of `s` or `t` is missing.*);
//@ ensures(*The method correctly handles cases where the non-repeating part of `s` or `t` is longer than the specified maximum length of 4.*);
//@ ensures(*The method correctly handles cases where the repeating part of `s` or `t` is longer than the specified maximum length of 4.*);
    public boolean isRationalEqual(String s, String t) {
        int sLeftIndex = s.indexOf("(");
        int tLeftIndex = t.indexOf("(");
        // if they are integer or only with non repeating part
        if (sLeftIndex < 0 && tLeftIndex < 0) {
            return Double.valueOf(s).equals(Double.valueOf(t));
        }
        // get the first 100 (or so) digits of s and t
        double sDouble;
        double tDouble;
        if (sLeftIndex >= 0) {
            s = s.substring(0, sLeftIndex) + repeat(s.substring(sLeftIndex + 1, s.length() - 1));
            sDouble = Double.parseDouble(s.substring(0, 100));
        } else {
            sDouble = Double.parseDouble(s);
        }
        if (tLeftIndex >= 0) {
            t = t.substring(0, tLeftIndex) + repeat(t.substring(tLeftIndex + 1, t.length() - 1));
            tDouble = Double.parseDouble(t.substring(0, 100));
        } else {
            tDouble = Double.parseDouble(t);
        }
        return sDouble == tDouble;
    }

    private String repeat(String a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append(a);
        }
        return sb.toString();
    }
}