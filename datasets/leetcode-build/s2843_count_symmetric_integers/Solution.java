package g2801_2900.s2843_count_symmetric_integers;

// #Easy #Math #Enumeration #2023_12_13_Time_26_ms_(80.87%)_Space_43.9_MB_(8.49%)

public class Solution {
	//@ requires(*1. The input integers `low` and `high` must be positive.*);
	//@ requires(*2. The value of `low` must be less than or equal to the value of `high`.*);
	//@ ensures(*1. The method should return an integer representing the number of symmetric integers in the range `[low, high]`.*);
	//@ ensures(*2. The returned value should be greater than or equal to 0.*);
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (isSymmetric(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isSymmetric(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        if (n % 2 != 0) {
            return false;
        }
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            leftSum += str.charAt(i) - '0';
            rightSum += str.charAt(j) - '0';
        }
        return leftSum == rightSum;
    }
}