package g0301_0400.s0331_verify_preorder_serialization_of_a_binary_tree;

// #Medium #String #Tree #Binary_Tree #Stack #2022_07_10_Time_2_ms_(99.12%)_Space_42.5_MB_(79.33%)

public class Solution {
//@ requires(*The length of the string parameter `preorder` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `preorder` consists of integers in the range [0, 100] and '#' separated by commas ','.*);
//@ ensures(*The boolean result is true if the string parameter `preorder` is a correct preorder traversal serialization of a binary tree, otherwise false.*);
//@ ensures(*If the string parameter `preorder` is equal to "9,3,4,#,#,1,#,#,2,#,6,#,#", the boolean result is true.*);
//@ ensures(*If the string parameter `preorder` is equal to "1,#", the boolean result is false.*);
//@ ensures(*If the string parameter `preorder` is equal to "9,#,#,1", the boolean result is false.*);
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