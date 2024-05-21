package g0101_0200.s0165_compare_version_numbers;

// #Medium #String #Two_Pointers #2022_06_25_Time_1_ms_(88.88%)_Space_41.8_MB_(66.14%)

public class Solution {
//@ requires(*Given two version numbers, param_version1 and param_version2, compare them.*);
//@ requires(*Version numbers consist of one or more revisions joined by a dot `'.'*);
//@ requires(*`.*);
//@ requires(*Each revision consists of digits and may contain leading zeros.*);
//@ requires(*Every revision contains at least one character.*);
//@ requires(*Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on.*);
//@ requires(*For example `2.5.33` and `0.1` are valid version numbers.*);
//@ requires(*To compare version numbers, compare their revisions in left-to-right order.*);
//@ requires(*Revisions are compared using their integer value ignoring any leading zeros.*);
//@ requires(*This means that revisions `1` and `001` are considered equal.*);
//@ requires(*If a version number does not specify a revision at an index, then treat the revision as `0`.*);
//@ requires(*For example, version `1.0` is less than version `1.1` because their revision 0s are the same, but their revision 1s are `0` and `1` respectively, and `0 < 1`.*);
//@ requires(*Return the following:*);
//@ requires(*Example 1:*);
//@ requires(*Input: version1 = "1.01", version2 = "1.001"*);
//@ requires(*Output: 0*);
//@ requires(*Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".*);
//@ requires(*Example 2:*);
//@ requires(*Input: version1 = "1.0", version2 = "1.0.0"*);
//@ requires(*Output: 0*);
//@ requires(*Explanation: version1 does not specify revision 2, which means it is treated as "0".*);
//@ requires(*Example 3:*);
//@ requires(*Input: version1 = "0.1", version2 = "1.1"*);
//@ requires(*Output: -1*);
//@ requires(*Explanation: version1's revision 0 is "0", while version2's revision 0 is "1".*);
//@ requires(*0 < 1, so version1 < version2.*);
//@ requires(*Example 4:*);
//@ requires(*Input: version1 = "1.0.1", version2 = "1"*);
//@ requires(*Output: 1*);
//@ requires(*Example 5:*);
//@ requires(*Input: version1 = "7.5.2.4", version2 = "7.5.3"*);
//@ requires(*Output: -1*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= version1.length, version2.length <= 500`*);
//@ requires(*param_version1 and param_version2 only contain digits and `'.'*);
//@ requires(*`.*);
//@ requires(*param_version1 and param_version2 are valid version numbers.*);
//@ requires(*All the given revisions in param_version1 and param_version2 can be stored in a 32-bit integer.*);
//@ ensures(*If `version1 < version2`, the result is `-1`.*);
//@ ensures(*If `version1 > version2`, the result is `1`.*);
//@ ensures(*Otherwise, the result is `0`.*);
    public int compareVersion(String version1, String version2) {
        // acquire first number
        int numA = 0;
        int i;
        for (i = 0; i < version1.length(); i++) {
            char c = version1.charAt(i);
            if (c == '.') {
                break;
            } else {
                numA = numA * 10 + (c - 48);
            }
        }
        // acquire second number
        int numB = 0;
        int j;
        for (j = 0; j < version2.length(); j++) {
            char c = version2.charAt(j);
            if (c == '.') {
                break;
            } else {
                numB = numB * 10 + (c - 48);
            }
        }
        // compare
        if (numA > numB) {
            return 1;
        } else if (numA < numB) {
            return -1;
        } else {
            // equal
            String v1 = "";
            String v2 = "";
            if (i != version1.length()) {
                v1 = version1.substring(i + 1);
            }
            if (j != version2.length()) {
                v2 = version2.substring(j + 1);
            }
            // if both versions end here, they are equal
            if (v1.equals("") && v2.equals("")) {
                return 0;
            } else {
                return compareVersion(v1, v2);
            }
        }
    }
}