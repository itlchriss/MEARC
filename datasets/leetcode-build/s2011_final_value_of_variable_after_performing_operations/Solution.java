package g2001_2100.s2011_final_value_of_variable_after_performing_operations;

// #Easy #Array #String #Simulation #2022_05_23_Time_1_ms_(94.40%)_Space_43.6_MB_(26.15%)

public class Solution {
	//@ requires(*The input array `operations` is not null.*);
	//@ requires(*The length of the input array `operations` is between 1 and 100.*);
	//@ requires(*Each element in the input array `operations` is either `"++X"`, `"X++"`, `"--X"`, or `"X--"`.*);
	//@ ensures(*The return value is an integer representing the final value of `X` after performing all the operations.*);
	//@ ensures(*The value of `X` is incremented by 1 for each occurrence of `"++X"` or `"X++"`.*);
	//@ ensures(*The value of `X` is decremented by 1 for each occurrence of `"--X"` or `"X--"`.*);
	//@ ensures(*The final value of `X` is the sum of all the increments and decrements performed on `X`.*);
    public int finalValueAfterOperations(String[] operations) {
        int xValue = 0;
        for (String word : operations) {
            if (word.contains("+")) {
                xValue++;
            } else {
                xValue--;
            }
        }
        return xValue;
    }
}