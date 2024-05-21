package g0301_0400.s0331_verify_preorder_serialization_of_a_binary_tree;

// #Medium #String #Tree #Binary_Tree #Stack #2022_07_10_Time_2_ms_(99.12%)_Space_42.5_MB_(79.33%)

public class Solution {
//@ requires(*One way to serialize a binary tree is to use preorder traversal.*);
//@ requires(*When we encounter a non-null node, we record the node's value.*);
//@ requires(*If it is a null node, we record using a sentinel value such as `'#'`.*);
//@ requires(*![](*);
//@ requires(*https://assets.leetcode.com/uploads/2021/03/12/pre-tree.jpg)*);
//@ requires(*For example, the above binary tree can be serialized to the string `"9,3,4,#,#,1,#,#,2,#,6,#,#"`, where `'#'` represents a null node.*);
//@ requires(*It is guaranteed that each comma-separated value in the string must be either an integer or a character `'#'` representing null pointer.*);
//@ requires(*You may assume that the input format is always valid.*);
//@ requires(*For example, it could never contain two consecutive commas, such as `"1,,3"`.*);
//@ requires(*Note: You are not allowed to reconstruct the tree.*);
//@ requires(*Example 1:*);
//@ requires(*Input: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"*);
//@ requires(*Output: true*);
//@ requires(*Example 2:*);
//@ requires(*Input: preorder = "1,#"*);
//@ requires(*Output: false*);
//@ requires(*Example 3:*);
//@ requires(*Input: preorder = "9,#,#,1"*);
//@ requires(*Output: false*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= preorder.length <= 10<sup>4</sup></code>*);
//@ requires(*param_preorder consist of integers in the range `[0, 100]` and `'#'` separated by commas `','`.*);
//@ ensures(*Given a string of comma-separated values param_preorder, the result is `true` if it is a correct preorder traversal serialization of a binary tree.*);
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