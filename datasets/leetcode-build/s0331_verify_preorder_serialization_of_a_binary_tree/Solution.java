package g0301_0400.s0331_verify_preorder_serialization_of_a_binary_tree;

// #Medium #String #Tree #Binary_Tree #Stack #2022_07_10_Time_2_ms_(99.12%)_Space_42.5_MB_(79.33%)

public class Solution {
	//@ requires(*The input `preorder` is a non-null string.*);
	//@ requires(*The input `preorder` is a valid comma-separated string.*);
	//@ requires(*The input `preorder` does not contain two consecutive commas.*);
	//@ ensures(*The method returns a boolean value indicating whether the `preorder` string is a correct preorder traversal serialization of a binary tree.*);
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