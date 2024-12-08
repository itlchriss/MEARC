package g0301_0400.s0331_verify_preorder_serialization_of_a_binary_tree;

// #Medium #String #Tree #Binary_Tree #Stack #2022_07_10_Time_2_ms_(99.12%)_Space_42.5_MB_(79.33%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((preorder.length() <= 10000) && (preorder.length() >= 1));
//@ ensures((preorder.equals("9,3,4,#,#,1,#,#,2,#,6,#,#")) ==> (\result == true));
//@ ensures((preorder.equals("9,#,#,1")) ==> (\result == false));
//@ ensures((preorder.equals("1,#")) ==> (\result == false));
    public boolean isValidSerialization(String preorder) {
        int count = 1;
        int length = preorder.length();
        for (int i = 1; i <= length; i++) {
            if (i == length || preorder.charAt(i) == ',') {
                --count;
                if (count < 0) {
                    return false;
                }
                count += preorder.charAt(i - 1) == '#' ? 0 : 2;
            }
        }
        return count >= 0;
    }
}
