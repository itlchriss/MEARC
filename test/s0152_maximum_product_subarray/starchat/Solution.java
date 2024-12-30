package g0101_0200.s0152_maximum_product_subarray;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Dynamic_Programming_I_Day_6 #Level_2_Day_13_Dynamic_Programming #Udemy_Dynamic_Programming
// #Big_O_Time_O(N)_Space_O(1) #2022_06_25_Time_0_ms_(100.00%)_Space_42.7_MB_(82.46%)

public class Solution {
//@ requires(*The length of the integer array parameter `arr` is less than or equal to 20000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `arr` are less than or equal to 10 and is greater than or equal to -10.*);
//@ requires(*If the integer array parameter `arr` is equal*);
//@ ensures(*The integer result is greater than or equal to -1000000000 and is less than or equal to 1000000000.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,3,-2,4], the integer result is equal to 6.*);
//@ ensures(*If the integer array parameter `arr` is equal to [-2,0,-1], the integer result is equal to 0.*);
//@ ensures(*If the integer array parameter `arr` is equal to [-2,-3,-4], the integer result is equal to 24.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5], the integer result is equal to 120.*);
//@ ensures(*If the integer array parameter `arr` is equal to [-2,-3,0,-4], the integer result is equal to 0.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6], the integer result is equal to 4320.*);
//@ ensures(*If the integer array parameter `arr` is equal to [-2,-3,4,-5,6,-7], the integer result is equal to 10080.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8], the integer result is equal to 362880.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8,-9], the integer result is equal to 3628800.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8,-9,10], the integer result is equal to 36288000.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8,-9,10,-11], the integer result is equal to 399168000.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8,-9,10,-11,12], the integer result is equal to 4790016000.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8,-9,10,-11,12,-13], the integer result is equal to 62270208000.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8,-9,10,-11,12,-13,14], the integer result is equal to 871782912000.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8,-9,10,-11,12,-13,14,-15], the integer result is equal to 13076743680000.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8,-9,10,-11,12,-13,14,-15,16], the integer result is equal to 209227898880000.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8,-9,10,-11,12,-13,14,-15,16,-17], the integer result is equal to 3355443200000000.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,-3,4,-5,6,-7,8,-9,10,-11,12,-13,14,-15,16,-17,18], the integer result is equal to 53782400000000000.*);
    public int maxProduct(int[] arr) {
        int ans = Integer.MIN_VALUE;
        int cprod = 1;
        for (int j : arr) {
            cprod = cprod * j;
            ans = Math.max(ans, cprod);
            if (cprod == 0) {
                cprod = 1;
            }
        }
        cprod = 1;
        //@ maintaining 0 <= i <= arr.length - 1 || i == -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            cprod = cprod * arr[i];
            ans = Math.max(ans, cprod);
            if (cprod == 0) {
                cprod = 1;
            }
        }
        return ans;
    }
}