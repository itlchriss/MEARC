package g0501_0600.s0575_distribute_candies;

// #Easy #Array #Hash_Table #2022_08_10_Time_67_ms_(42.81%)_Space_111.2_MB_(25.92%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
	//@ requires(*The input array `candyType` is not null.*);
	//@ requires(*The length of the input array `candyType` is even.*);
	//@ requires(*The length of the input array `candyType` is greater than or equal to 2.*);
	//@ requires(*The elements in the input array `candyType` are integers.*);
	//@ requires(*The elements in the input array `candyType` are within the range of -10^5 to 10^5.*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value represents the maximum number of different types of candies Alice can eat.*);
	//@ ensures(*Alice can only eat `n / 2` candies, where `n` is the length of the input array `candyType`.*);
	//@ ensures(*Alice can eat at most one candy of each type.*);
	//@ ensures(*The return value is less than or equal to the number of unique types of candies in the input array `candyType`.*);
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        for (int i : candyType) {
            set.add(i);
        }
        return Math.min(set.size(), candyType.length / 2);
    }
}