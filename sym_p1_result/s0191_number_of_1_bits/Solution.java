package g0101_0200.s0191_number_of_1_bits;

// #Easy #Top_Interview_Questions #Bit_Manipulation #Algorithm_I_Day_13_Bit_Manipulation
// #Programming_Skills_I_Day_2_Operator #Udemy_Bit_Manipulation
// #2022_06_28_Time_1_ms_(84.87%)_Space_41.8_MB_(10.40%)

public class Solution {
//@ requires(*Note:*);
//@ requires(*Note that in some languages, such as Java, there is no unsigned integer type.*);
//@ requires(*In this case, the input will be given as a signed integer type.*);
//@ requires(*It should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.*);
//@ requires(*In Java, the compiler represents the signed integers using [2's complement notation](https://en.wikipedia.org/wiki/Two%27scomplement).*);
//@ requires(*Therefore, in Example 3, the input represents the signed integer.*);
//@ requires(*`-3`.*);
//@ requires(*Example 1:*);
//@ requires(*Input: n = 00000000000000000000000000001011*);
//@ requires(*Output: 3*);
//@ requires(*Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 00000000000000000000000010000000*);
//@ requires(*Output: 1*);
//@ requires(*Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.*);
//@ requires(*Example 3:*);
//@ requires(*Input: n = 11111111111111111111111111111101*);
//@ requires(*Output: 31*);
//@ requires(*Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.*);
//@ requires(*Constraints:*);
//@ requires(*The input must be a binary string of length `32`.*);
//@ requires(*Follow up: If this function is called many times, how would you optimize it?*);
//@ ensures(*Write a function that takes an unsigned integer and the result iss the number of '1' bits it has (also known as the [Hamming weight](http://en.wikipedia.org/wiki/Hammingweight)).*);
    public int hammingWeight(int n) {
        int sum = 0;
        boolean flag = false;
        if (n < 0) {
            flag = true;
            n = n - Integer.MIN_VALUE;
        }
        while (n > 0) {
            int k = n % 2;
            sum += k;
            n /= 2;
        }
        return flag ? sum + 1 : sum;
    }
}