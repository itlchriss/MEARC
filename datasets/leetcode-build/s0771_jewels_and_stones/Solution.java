package g0701_0800.s0771_jewels_and_stones;

// #Easy #String #Hash_Table #2022_03_26_Time_1_ms_(91.74%)_Space_41.7_MB_(74.24%)

public class Solution {
	//@ requires(*1. The input strings `jewels` and `stones` must not be null.*);
	//@ requires(*2. The length of `jewels` and `stones` must be between 1 and 50.*);
	//@ requires(*3. The characters in `jewels` must be unique.*);
	//@ ensures(*1. The method returns an integer representing the number of stones that are also jewels.*);
	//@ ensures(*2. The returned value is greater than or equal to 0.*);
	//@ ensures(*3. The returned value is less than or equal to the length of `stones`.*);
	//@ ensures(*4. The method is case-sensitive, so stones with the same characters but different cases are considered different types.*);
    public int numJewelsInStones(String jewels, String stones) {
        int[] x = new int[60];
        int count = 0;
        int len = jewels.length();
        int len2 = stones.length();
        for (int i = 0; i < len; i++) {
            x[jewels.charAt(i) - 65]++;
        }
        for (int i = 0; i < len2; i++) {
            if (x[stones.charAt(i) - 65] == 1) {
                count++;
            }
        }
        return count;
    }
}