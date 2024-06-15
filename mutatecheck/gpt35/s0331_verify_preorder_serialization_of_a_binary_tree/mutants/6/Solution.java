package g0301_0400.s0331_verify_preorder_serialization_of_a_binary_tree;

// #Medium #String #Tree #Binary_Tree #Stack #2022_07_10_Time_2_ms_(99.12%)_Space_42.5_MB_(79.33%)

public class Solution {
// requires public boolean isValidSerialization(String preorder)
// ensures \result == true <==> each comma-separated value in the string is either an integer or '#'
// ensures \result == true <==> input format is always valid
// ensures \result == true || \result == false
// ensures \result == false <==> input format is not valid
// ensures \result == false <==> preorder is not a correct preorder traversal serialization of a binary tree
// ensures \result == true <==> preorder is a correct preorder traversal serialization of a binary tree
// requires preorder != null
// ensures \result == false <==> preorder does not represent a correct preorder traversal serialization of a binary tree
    public boolean isValidSerialization(String preorder) {
        int count = 1;
        int length = preorder.length();
        for (int i = 1; i <= length; i++) {
            if (i == length || preorder.charAt(i) <= ',') {
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
