package g0101_0200.s0165_compare_version_numbers;

// #Medium #String #Two_Pointers #2022_06_25_Time_1_ms_(88.88%)_Space_41.8_MB_(66.14%)

public class Solution {
//@ requires(*The integer parameter `version1` and the integer parameter `version2` are valid version numbers.*);
//@ requires(*The integer parameter `version1` and the integer parameter `version2` consist of one or more revisions joined by a dot.*);
//@ requires(*Each revision in the integer parameter `version1` and the integer parameter `version2` consists of digits and may contain leading zeros.*);
//@ requires(*Every revision in the integer parameter `version1` and the integer parameter `version2` contains at least one character.*);
//@ requires(*Revisions in the integer parameter `version1` and the integer parameter `version2` are 0-indexed from left to right.*);
//@ requires(*Revisions in the integer parameter `version1` and the integer parameter `version2` are compared in left-to-right order using their integer value ignoring any leading zeros.*);
//@ requires(*If a version number does not specify a revision at an index, then treat the revision as 0.*);
//@ ensures(*If the integer parameter `version1` is less than the integer parameter `version2`, the integer result is equal to -1.*);
//@ ensures(*If the integer parameter `version1` is greater than the integer parameter `version2`, the integer result is equal to 1.*);
//@ ensures(*If the integer parameter `version1` is equal to the integer parameter `version2`, the integer result is equal to 0.*);
//@ ensures(*The integer result is less than or equal to 1 and is greater than or equal to -1.*);
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