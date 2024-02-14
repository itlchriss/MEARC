package g2101_2200.s2120_execution_of_all_suffix_instructions_staying_in_a_grid;

// #Medium #String #Simulation #2022_06_02_Time_31_ms_(88.00%)_Space_42.6_MB_(85.71%)

@SuppressWarnings("java:S135")
public class Solution {
	//@ requires(*The input integer `n` is greater than or equal to 1.*);
	//@ requires(*The length of the input integer array `startPos` is 2.*);
	//@ requires(*The values of `startPos[0]` and `startPos[1]` are both greater than or equal to 0 and less than `n`.*);
	//@ requires(*The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*The characters in the input string `s` are either 'L', 'R', 'U', or 'D'.*);
	//@ ensures(*The length of the output integer array `answer` is equal to the length of the input string `s`.*);
	//@ ensures(*Each element in the output integer array `answer` is a non-negative integer.*);
	//@ ensures(*The sum of all elements in the output integer array `answer` is less than or equal to the length of the input string `s`.*);
	//@ ensures(*The robot can execute the instructions starting from any index in the input string `s`.*);
	//@ ensures(*The robot stops executing the instructions if either of the following conditions is met:*);
	//@ ensures(*- The next instruction will move the robot off the grid.*);
	//@ ensures(*- There are no more instructions left to execute.*);
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int[] answer = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            int currX = startPos[0];
            int currY = startPos[1];
            for (int j = i; j < s.length(); j++) {
                char mv = s.charAt(j);
                if (mv == 'R') {
                    currY++;
                    if (currY > n - 1) {
                        break;
                    }
                } else if (mv == 'D') {
                    currX++;
                    if (currX > n - 1) {
                        break;
                    }
                } else if (mv == 'L') {
                    currY--;
                    if (currY < 0) {
                        break;
                    }
                } else if (mv == 'U') {
                    currX--;
                    if (currX < 0) {
                        break;
                    }
                }
                count++;
            }
            answer[i] = count;
        }
        return answer;
    }
}