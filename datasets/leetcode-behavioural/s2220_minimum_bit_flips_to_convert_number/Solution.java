package g2201_2300.s2220_minimum_bit_flips_to_convert_number;

// #Easy #Bit_Manipulation #2022_06_12_Time_1_ms_(67.86%)_Space_41.4_MB_(25.22%)

public class Solution {
    private int decToBinary(int n) {
        int[] binaryNum = new int[32];
        int i = 0;
        while (n > 0) {
            binaryNum[i] = n % 2;
            n = n / 2;
            i++;
        }

        int answer = 0;
        for (int j = i - 1; j >= 0; j--) {
            if (binaryNum[j] == 1) {
                answer++;
            }
        }

        return answer;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `start` and `goal` must be non-negative.*);
//@ ensures(*The binary representation of `start` and `goal` must have the same length.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of bit flips required to convert `start` to `goal`.*);

    public int minBitFlips(int start, int goal) {
        int answer = start ^ goal;
        return decToBinary(answer);
    }
}