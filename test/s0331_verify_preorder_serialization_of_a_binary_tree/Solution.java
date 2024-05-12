package g0301_0400.s0331_verify_preorder_serialization_of_a_binary_tree;

// #Medium #String #Tree #Binary_Tree #Stack #2022_07_10_Time_2_ms_(99.12%)_Space_42.5_MB_(79.33%)

public class Solution {
//@ requires(*The length of the string parameter `preorder` is greater than or equal to 1 and is less than or equal to 10000.*);
//@ requires(*Each value in the string parameter `preorder` is either an integer in the range [0, 100] or the character `'#'`.*);
//@ requires(*The values in the string parameter `preorder` are separated by commas ','.*);
//@ ensures(*If the string parameter `preorder` is a correct preorder traversal serialization of a binary tree, the boolean result is equal to the true literal.*);
//@ ensures(*If the string parameter `preorder` is not a correct preorder traversal serialization of a binary tree, the boolean result is equal to the false literal.*);
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