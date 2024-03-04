package g0401_0500.s0420_strong_password_checker;

// #Hard #String #Greedy #Heap_Priority_Queue #2022_07_16_Time_0_ms_(100.00%)_Space_40_MB_(90.09%)

public class Solution {
//@ ensures(*If the string parameter `s` has less than 6 characters, the integer result is the number of characters needed to make the string parameter `s` have at least 6 characters.*);
//@ ensures(*If the string parameter `s` has more than 20 characters, the integer result is the number of characters needed to make the string parameter `s` have at most 20 characters.*);
//@ ensures(*If the string parameter `s` does not contain at least one lowercase letter, at least one uppercase letter, and at least one digit, the integer result is the number of characters needed to fulfill each of these conditions.*);
//@ ensures(*If the string parameter `s` contains three repeating characters in a row, the integer result is the number of characters needed to remove the repeating characters to satisfy the condition.*);
//@ ensures(*If the string parameter `s` is already strong, the integer result is 0.*);
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