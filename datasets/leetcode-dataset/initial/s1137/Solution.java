package g1101_1200.s1137_n_th_tribonacci_number;

// #Easy #Dynamic_Programming #Math #Memoization #Dynamic_Programming_I_Day_1
// #2023_06_01_Time_0_ms_(100.00%)_Space_39.6_MB_(48.37%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given `n`, return the value of T<sub>n</sub>.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        } else {
            int tn = 0;
            int tn1 = 1;
            int tn2 = 1;
            int tmp = 0;
            for (int i = 3; i <= n; i++) {
                tmp = tn + tn1 + tn2;
                tn = tn1;
                tn1 = tn2;
                tn2 = tmp;
            }
            return tmp;
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
