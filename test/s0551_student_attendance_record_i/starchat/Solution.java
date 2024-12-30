package g0501_0600.s0551_student_attendance_record_i;

// #Easy #String #2022_08_02_Time_0_ms_(100.00%)_Space_40.2_MB_(96.36%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*All characters in the string parameter `s` are either 'A', 'L', or 'P'.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1004\. Max Consecutive Ones III*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given an array `A` of 0s and 1s, we may change up to `K` values from 0 to 1.*);
//@ requires(**);
//@ requires(*Return the length of the longest (contiguous) subarray that contains only 1s. *);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** A = \[1,1,1,0,0,0,1,1,1,1,0\], K = 2*);
//@ requires(**);
//@ requires(***Output:** 6*);
//@ requires(**);
//@ requires(***Explanation:** *);
//@ requires(*\[1,1,1,0,0,**1**,1,1,1,**1**,0\]*);
//@ requires(*Bolded numbers were flipped from 0 to 1.  The longest subarray of 1s is 6.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** A = \[0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1\], K = 3*);
//@ requires(**);
//@ requires(***Output:** 10*);
//@ requires(**);
//@ requires(***Explanation:** *);
//@ requires(*\[0,0,**1**,1,0,0,**1**,1,**1**,0,**1**,1,0,0,0,**1**,1,**1**,1\]*);
//@ requires(*Bolded numbers were flipped from 0 to 1.  The longest subarray of 1s is 10.*);
//@ requires(**);
//@ requires(***Note:***);
//@ requires(**);
//@ requires(*`1 <= A.length <= 20000`*);
//@ requires(*`0 <= K <= A.length`*);
//@ requires(*`A[i]` is `0` or `1` *);
//@ requires(**);
//@ requires(*Method signature: public int longestOnes(int[] A, int K)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(*The length of the integer array parameter `A` is less than or equal to 20000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `A` are either 0 or 1.*);
//@ requires(*The integer parameter `K` is less than or equal to the length of the integer array parameter `A` and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 1043\. Partition Array Into Three Subarrays*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given an array `A` of integers, return any index `i` such that `0 <= i < A.length` and the sum of the first `i` elements of `A` is equal to the sum of the last `A.length - i` elements of `A`.*);
//@ requires(**);
//@ requires(*Additionally, the sum of the first `i` elements of `A` should be greater than or equal to the sum of the next `j` elements of `A` (where `j` is a positive integer), and the sum of the last `A.length - i` elements of `A` should be less than or equal to the sum of the previous `k` elements of `A` (where `k` is a positive integer).*);
//@ requires(**);
//@ requires(*If no such index exists, then return `-1`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** \[0,2,1,-6,6,-7,9,1,2,0,1\]*);
//@ ensures(*The boolean result is true if the student is eligible for an attendance award, or false otherwise.*);
//@ ensures(*If the string parameter `s` is equal to "PPALLP", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "PPALLL", the boolean result is false.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the integer array parameter `A`.*);
//@ ensures(*If the integer array parameter `A` is equal to \[1,1,1,0,0,0,1,1,1,1,0\] and the integer parameter `K` is equal to 2, the integer result is equal to 6.*);
//@ ensures(*If the integer array parameter `A` is equal to \[0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1\] and the integer parameter `K` is equal to 3, the integer result is equal to 10.*);
    public boolean checkRecord(String s) {
        int aCount = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == 'A') {
                aCount++;
                if (aCount > 1) {
                    return false;
                }
            } else if (s.charAt(i) == 'L') {
                int continuousLCount = 0;
                while (i < s.length() && s.charAt(i) == 'L') {
                    i++;
                    continuousLCount++;
                    if (continuousLCount > 2) {
                        return false;
                    }
                }
                i--;
            }
            i++;
        }
        return true;
    }
}