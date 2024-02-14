package g0901_1000.s0909_snakes_and_ladders;

// #Medium #Array #Breadth_First_Search #Matrix
// #2022_03_28_Time_7_ms_(79.52%)_Space_47.7_MB_(58.43%)

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private int size;
	//@ requires(*The input matrix `board` is not null.*);
	//@ requires(*The input matrix `board` has a length of `n`.*);
	//@ requires(*Each row of the input matrix `board` has a length of `n`.*);
	//@ requires(*The input matrix `board` contains integers in the range `[1, n^2]` and `-1`.*);
	//@ requires(*The input matrix `board` does not contain any ladders or snakes on squares `1` and `n^2`.*);
	//@ ensures(*The method returns an integer representing the least number of moves required to reach square `n^2`.*);
	//@ ensures(*If it is not possible to reach square `n^2`, the method returns `-1`.*);
	//@ ensures(*The method does not modify the input matrix `board`.*);

    public int snakesAndLadders(int[][] board) {
        Queue<Integer> queue = new LinkedList<>();
        size = board.length;
        int target = size * size;
        boolean[] visited = new boolean[target];
        queue.add(1);
        visited[0] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int previousLabel = queue.poll();
                if (previousLabel == target) {
                    return step;
                }
                for (int currentLabel = previousLabel + 1;
                        currentLabel <= Math.min(target, previousLabel + 6);
                        currentLabel++) {
                    if (visited[currentLabel - 1]) {
                        continue;
                    }
                    visited[currentLabel - 1] = true;
                    int[] position = indexToPosition(currentLabel);
                    if (board[position[0]][position[1]] == -1) {
                        queue.add(currentLabel);
                    } else {
                        queue.add(board[position[0]][position[1]]);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private int[] indexToPosition(int index) {
        int vertical = this.size - 1 - (index - 1) / this.size;
        int horizontal;
        if ((this.size - vertical) % 2 == 1) {
            horizontal = (index - 1) % this.size;
        } else {
            horizontal = this.size - 1 - (index - 1) % this.size;
        }
        return new int[] {vertical, horizontal};
    }
}