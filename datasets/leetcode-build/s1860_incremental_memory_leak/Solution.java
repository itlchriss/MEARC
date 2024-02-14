package g1801_1900.s1860_incremental_memory_leak;

// #Medium #Simulation #2022_05_08_Time_5_ms_(78.57%)_Space_41.9_MB_(34.69%)

public class Solution {
	//@ requires(*The method takes two integer parameters `memory1` and `memory2` representing the available memory in bits on two memory sticks.*);
	//@ requires(*The values of `memory1` and `memory2` are non-negative integers.*);
	//@ ensures(*The method returns an array containing `[crashTime, memory1_crash, memory2_crash]`, where `crashTime` is the time (in seconds) when the program crashed and `memory1_crash` and `memory2_crash` are the available bits of memory in the first and second sticks respectively.*);
	//@ ensures(*The values of `crashTime`, `memory1_crash`, and `memory2_crash` are non-negative integers.*);
	//@ ensures(*The program crashes when neither stick has at least `i` bits of available memory, where `i` is the current second.*);
	//@ ensures(*The memory allocation happens every second, starting from the 1st second.*);
	//@ ensures(*At the `i`th second, `i` bits of memory are allocated to the stick with more available memory (or from the first memory stick if both have the same available memory).*);
	//@ ensures(*The available memory in each stick is updated after each memory allocation.*);
	//@ ensures(*The available memory in each stick is non-negative and does not exceed the maximum limit of `2^31 - 1`.*);
    public int[] memLeak(int memory1, int memory2) {
        int time = 1;
        while (memory1 >= time || memory2 >= time) {
            if (memory1 >= memory2) {
                memory1 -= time;
            } else {
                memory2 -= time;
            }
            time++;
        }
        return new int[] {time, memory1, memory2};
    }
}