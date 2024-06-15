package g0101_0200.s0165_compare_version_numbers;

// #Medium #String #Two_Pointers #2022_06_25_Time_1_ms_(88.88%)_Space_41.8_MB_(66.14%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((version2.length() <= 500) && (version2.length() >= 1));
//@ requires((version1.length() <= 500) && (version1.length() >= 1));
// ensures((\result == -1) ==> (version1 < version2));
// ensures((\result == 1) ==> (version1 > version2));
//@ ensures(((version1.equals("1.01")) && (version2.equals("1.001"))) ==> (\result == 0));
//@ ensures(((version1.equals("1.0.1")) && (version2.equals("1"))) ==> (\result == 1));
//@ ensures(((version1.equals("7.5.2.4")) && (version2.equals("7.5.3"))) ==> (\result == -1));
//@ ensures(((version1.equals("0.1")) && (version2.equals("1.1"))) ==> (\result == -1));
//@ ensures(((version1.equals("1.0")) && (version2.equals("1.0.0"))) ==> (\result == 0));
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
                v2 = version2.substring(j * 1);
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
