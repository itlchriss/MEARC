package g1101_1200.s1131_maximum_of_absolute_value_expression;

// #Medium #Array #Math #2023_06_01_Time_13_ms_(24.81%)_Space_52.7_MB_(5.43%)

public class Solution {
	//@ requires(*The lengths of arr1 and arr2 must be equal.*);
	//@ requires(*The length of arr1 and arr2 must be at least - The elements in arr1 and arr2 must be integers.*);
	//@ requires(*The elements in arr1 and arr2 must be within the range of -10^6 to 10^*);
	//@ ensures(*The method should return an integer value.*);
	//@ ensures(*The returned value should be the maximum value of the expression |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|.*);
	//@ ensures(*The maximum value should be taken over all valid combinations of i and j, where 0 <= i, j < arr1.length.*);
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return 0;
        }
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int max4 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;
        int min4 = Integer.MAX_VALUE;
        for (int i = 0; i < arr1.length; i++) {
            max1 = Math.max(arr1[i] + arr2[i] + i, max1);
            min1 = Math.min(arr1[i] + arr2[i] + i, min1);
            max2 = Math.max(i - arr1[i] - arr2[i], max2);
            min2 = Math.min(i - arr1[i] - arr2[i], min2);
            max3 = Math.max(arr1[i] - arr2[i] + i, max3);
            min3 = Math.min(arr1[i] - arr2[i] + i, min3);
            max4 = Math.max(arr2[i] - arr1[i] + i, max4);
            min4 = Math.min(arr2[i] - arr1[i] + i, min4);
        }
        return Math.max(Math.max(max1 - min1, max2 - min2), Math.max(max3 - min3, max4 - min4));
    }
}