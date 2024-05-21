package g0401_0500.s0461_hamming_distance;

// #Easy #Bit_Manipulation #Udemy_Bit_Manipulation
// #2022_07_19_Time_0_ms_(100.00%)_Space_40.9_MB_(60.77%)

public class Solution {
//@ requires(*The [Hamming distance](https://en.wikipedia.org/wiki/Hammingdistance) between two integers is the number of positions at which the corresponding bits are different.*);
//@ requires(*Example 1:*);
//@ requires(*Input: x = 1, y = 4*);
//@ requires(*Output: 2*);
//@ requires(*Explanation: 1 (0 0 0 1) 4 (0 1 0 0) ↑ ↑ The above arrows point to positions where the corresponding bits are different.*);
//@ requires(*Example 2:*);
//@ requires(*Input: x = 3, y = 1*);
//@ requires(*Output: 1*);
//@ requires(*Constraints:*);
//@ requires(*<code>0 <= x, y <= 2<sup>31</sup> - 1</code>*);
//@ ensures(*Given two integers param_x and param_y, the result is the Hamming distance between them.*);
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}