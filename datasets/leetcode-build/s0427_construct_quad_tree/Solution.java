package g0401_0500.s0427_construct_quad_tree;

// #Medium #Array #Tree #Matrix #Divide_and_Conquer
// #2022_07_16_Time_0_ms_(100.00%)_Space_42.6_MB_(89.45%)

/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
	//@ requires(*The input `grid` is a square matrix of size `n * n`.*);
	//@ requires(*The values in the `grid` are either `0` or `1`.*);
	//@ requires(*The size of the `grid` is a power of 2, i.e., `n` is equal to `2^x` where `x` is an integer between 0 and 6.*);
	//@ ensures(*The method returns the root of a Quad-Tree representing the `grid`.*);
	//@ ensures(*The Quad-Tree is constructed according to the following rules:*);
	//@ ensures(*- If the current grid has the same value (i.e., all `1's` or all `0's`), the `isLeaf` attribute of the node is set to `True`, the `val` attribute is set to the value of the grid, and the four children of the node are set to `Null`.*);
	//@ ensures(*- If the current grid has different values, the `isLeaf` attribute of the node is set to `False`, the `val` attribute is set to any value, and the current grid is divided into four sub-grids.*);
	//@ ensures(*- The method recursively constructs the Quad-Tree for each of the children with the proper sub-grid.*);
	//@ ensures(*The Quad-Tree is represented in the serialized format using level order traversal, where `null` signifies a path terminator where no node exists below.*);
	//@ ensures(*The serialized format of the Quad-Tree is similar to the serialization of a binary tree, where each node is represented as a list `[isLeaf, val]`.*);
	//@ ensures(*If the value of `isLeaf` or `val` is `True`, it is represented as `1` in the list `[isLeaf, val]`. If the value of `isLeaf` or `val` is `False`, it is represented as `0`.*);

    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};
*/
public class Solution {
    public Node construct(int[][] grid) {
        return optimizedDfs(grid, 0, 0, grid.length);
    }

    private Node optimizedDfs(int[][] grid, int rowStart, int colStart, int len) {
        int zeroCount = 0;
        int oneCount = 0;
        for (int row = rowStart; row < rowStart + len; row++) {
            boolean isBreak = false;
            for (int col = colStart; col < colStart + len; col++) {
                if (grid[row][col] == 0) {
                    zeroCount++;
                } else {
                    oneCount++;
                }
                if (oneCount > 0 && zeroCount > 0) {
                    // We really no need to scan all.
                    // Once we know there are both 0 and 1 then we can break.
                    isBreak = true;
                    break;
                }
            }
            if (isBreak) {
                break;
            }
        }
        if (oneCount > 0 && zeroCount > 0) {
            int midLen = len / 2;
            Node topLeft = optimizedDfs(grid, rowStart, colStart, midLen);
            Node topRight = optimizedDfs(grid, rowStart, colStart + midLen, midLen);
            Node bottomLeft = optimizedDfs(grid, rowStart + midLen, colStart, midLen);
            Node bottomRight = optimizedDfs(grid, rowStart + midLen, colStart + midLen, midLen);
            boolean isLeaf = false;
            return new Node(true, isLeaf, topLeft, topRight, bottomLeft, bottomRight);
        } else {
            boolean resultVal = oneCount > 0;
            boolean isLeaf = true;
            return new Node(resultVal, isLeaf);
        }
    }
}