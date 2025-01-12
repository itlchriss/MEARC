package g0301_0400.s0331_verify_preorder_serialization_of_a_binary_tree;

// #Medium #String #Tree #Binary_Tree #Stack #2022_07_10_Time_2_ms_(99.12%)_Space_42.5_MB_(79.33%)

public class Solution {
//@ requires(*The string parameter `preorder` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `preorder` consists of comma-separated values that are either integers in the range [0, 100] or a character '#' representing null pointer.*);
//@ ensures(*The integer result is either 1 or 0.*);
//@ ensures(*If the string parameter `preorder` is equal to "9,3,4,#,#,1,#,#,2,#,6,#,#", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `preorder` is equal to "1,#", the integer result is equal to 0.*);
//@ ensures(*If the string parameter `preorder` is equal to "9,#,#,1", the integer result is equal to 0.*);
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
        return count == 0;
    }
}