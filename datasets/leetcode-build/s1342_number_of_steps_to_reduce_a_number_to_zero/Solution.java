package g1301_1400.s1342_number_of_steps_to_reduce_a_number_to_zero;

// #Easy #Math #Bit_Manipulation #2022_03_19_Time_1_ms_(58.49%)_Space_41_MB_(43.29%)

public class Solution {
	//@ requires(*The input `num` is an integer.*);
	//@ requires(*`num` is greater than or equal to 0.*);
	//@ requires(*`num` is less than or equal to 10^6.*);
	//@ ensures(*The output is an integer representing the number of steps required to reduce `num` to zero.*);
	//@ ensures(*The output is greater than or equal to 0.*);
    public int numberOfSteps(int num) {
        int steps = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num--;
            }
            steps++;
        }
        return steps;
    }
}