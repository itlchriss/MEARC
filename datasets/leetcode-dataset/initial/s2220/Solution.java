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
<<<<<<< HEAD
=======
//@ ensures(*Given two integers `start` and `goal`, return _the **minimum** number of **bit flips** to convert_ `start` _to_ `goal`. It can be shown we cannot convert 10 to 7 in less than 3 steps. Hence, we return 3. It can be shown we cannot convert 3 to 4 in less than 3 steps. Hence, we return 3.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03

    public int minBitFlips(int start, int goal) {
        int answer = start ^ goal;
        return decToBinary(answer);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
