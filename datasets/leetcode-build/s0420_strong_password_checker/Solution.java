package g0401_0500.s0420_strong_password_checker;

// #Hard #String #Greedy #Heap_Priority_Queue #2022_07_16_Time_0_ms_(100.00%)_Space_40_MB_(90.09%)

public class Solution {
	//@ requires(*The input string `password` is not null.*);
	//@ requires(*The length of `password` is between 1 and 50 (inclusive).*);
	//@ ensures(*The output is an integer representing the minimum number of steps required to make `password` strong.*);
	//@ ensures(*If `password` is already strong, the output is 0.*);
	//@ ensures(*Additional behavioural requirements:*);
	//@ ensures(*The method should check if `password` meets the conditions for a strong password.*);
	//@ ensures(*If `password` does not meet the conditions, the method should determine the minimum number of steps required to make it strong.*);
	//@ ensures(*The method should consider the following steps to make `password` strong: inserting one character, deleting one character, or replacing one character with another character.*);
	//@ ensures(*The method should return the minimum number of steps required to make `password` strong.*);
    public int strongPasswordChecker(String s) {
        int res = 0;
        int a1 = 1;
        int a2 = 1;
        int d = 1;
        char[] carr = s.toCharArray();
        int[] arr = new int[carr.length];
        int i1 = 0;
        while (i1 < arr.length) {
            if (Character.isLowerCase(carr[i1])) {
                a1 = 0;
            }
            if (Character.isUpperCase(carr[i1])) {
                a2 = 0;
            }
            if (Character.isDigit(carr[i1])) {
                d = 0;
            }
            int j = i1;
            while (i1 < carr.length && carr[i1] == carr[j]) {
                i1++;
            }
            arr[j] = i1 - j;
        }
        int totalMissing = (a1 + a2 + d);
        if (arr.length < 6) {
            res += totalMissing + Math.max(0, 6 - (arr.length + totalMissing));
        } else {
            int overLen = Math.max(arr.length - 20, 0);
            int leftOver = 0;
            res += overLen;
            for (int k = 1; k < 3; k++) {
                for (int i = 0; i < arr.length && overLen > 0; i++) {
                    if (arr[i] < 3 || arr[i] % 3 != (k - 1)) {
                        continue;
                    }
                    arr[i] -= Math.min(overLen, k);
                    overLen -= k;
                }
            }
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= 3 && overLen > 0) {
                    int need = arr[i] - 2;
                    arr[i] -= overLen;
                    overLen -= need;
                }
                if (arr[i] >= 3) {
                    leftOver += arr[i] / 3;
                }
            }
            res += Math.max(totalMissing, leftOver);
        }
        return res;
    }
}