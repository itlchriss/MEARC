package g1001_1100.s1036_escape_a_large_maze;

// #Hard #Array #Hash_Table #Depth_First_Search #Breadth_First_Search
// #2022_02_27_Time_115_ms_(73.46%)_Space_79.4_MB_(60.66%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
	//@ requires(*1. The `blocked` array is not null.*);
	//@ requires(*2. The `source` array is not null.*);
	//@ requires(*3. The `target` array is not null.*);
	//@ requires(*4. The `blocked` array does not contain any null elements.*);
	//@ requires(*5. The `source` array contains exactly two elements.*);
	//@ requires(*6. The `target` array contains exactly two elements.*);
	//@ requires(*7. The `blocked` array does not contain any duplicate elements.*);
	//@ requires(*8. The `source` and `target` arrays do not contain any duplicate elements.*);
	//@ requires(*9. The `blocked` array does not contain the `source` or `target` elements.*);
	//@ requires(*10. The `blocked` array contains at most 200 elements.*);
	//@ requires(*11. The coordinates in the `blocked` array are within the range of 0 to 1 million.*);
	//@ requires(*12. The coordinates in the `source` and `target` arrays are within the range of 0 to 1 million.*);
	//@ requires(*13. The `source` and `target` arrays are not equal.*);
	//@ ensures(*1. The method returns a boolean value indicating whether it is possible to reach the `target` square from the `source` square through a sequence of valid moves.*);
	//@ ensures(*2. If the method returns true, it means it is possible to reach the `target` square.*);
	//@ ensures(*3. If the method returns false, it means it is not possible to reach the `target` square.*);
	//@ ensures(*4. The method does not modify the `blocked`, `source`, or `target` arrays.*);
	//@ ensures(*5. The method does not modify the state of the grid.*);
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length == 0) {
            return true;
        }
        Set<Integer> blocks = new HashSet<>();
        for (int[] b : blocked) {
            if (target[0] * 1000000 + target[1] != b[0] * 1000000 + b[1]) {
                blocks.add(b[0] * 1000000 + b[1]);
            }
        }
        return dfs(blocks, source, source[0], source[1], new HashSet<>(), target)
                && dfs(blocks, target, target[0], target[1], new HashSet<>(), source);
    }

    private boolean dfs(
            Set<Integer> blocks, int[] start, int i, int j, Set<Integer> visited, int[] target) {
        if (i < 0
                || j < 0
                || i > 999999
                || j > 999999
                || blocks.contains(i * 1000000 + j)
                || visited.contains(i * 1000000 + j)) {
            return false;
        }
        if (i == target[0] && j == target[1]) {
            return true;
        }
        visited.add(i * 1000000 + j);
        if (visited.size() > blocks.size() * (blocks.size() + 1)) {
            return true;
        }
        return dfs(blocks, start, i + 1, j, visited, target)
                || dfs(blocks, start, i - 1, j, visited, target)
                || dfs(blocks, start, i, j + 1, visited, target)
                || dfs(blocks, start, i, j - 1, visited, target);
    }
}