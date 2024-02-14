package g1701_1800.s1769_minimum_number_of_operations_to_move_all_balls_to_each_box;

// #Medium #Array #String #2022_04_30_Time_3_ms_(91.66%)_Space_47.8_MB_(35.95%)

public class Solution {
	//@ requires(*The input `boxes` is a binary string of length `n`.*);
	//@ requires(*Each character in `boxes` is either `'0'` or `'1'`.*);
	//@ requires(*The length of `boxes` is between 1 and*);
	//@ ensures(*The output is an array `answer` of size `n`.*);
	//@ ensures(*Each element in `answer` represents the minimum number of operations needed to move all the balls to the corresponding box.*);
	//@ ensures(*The order of elements in `answer` corresponds to the order of boxes in the input `boxes`.*);
    public int[] minOperations(String boxes) {
        int countFromLeft = 0;
        int countFromRight = 0;
        int moves = 0;
        int[] result = new int[boxes.length()];
        for (char c : boxes.toCharArray()) {
            moves += countFromLeft;
            if (c == '1') {
                countFromLeft++;
            }
        }
        for (int i = boxes.length() - 1; i >= 0; i--) {
            char c = boxes.charAt(i);
            result[i] = moves;
            if (c == '1') {
                countFromLeft--;
                countFromRight++;
            }
            moves -= countFromLeft;
            moves += countFromRight;
        }
        return result;
    }
}