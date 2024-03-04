package g0701_0800.s0739_daily_temperatures;

// #Medium #Top_100_Liked_Questions #Array #Stack #Monotonic_Stack #Programming_Skills_II_Day_6
// #Big_O_Time_O(n)_Space_O(n) #2022_03_25_Time_10_ms_(94.99%)_Space_118.3_MB_(70.21%)

@SuppressWarnings("java:S135")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `temperatures` is not null.*);
//@ ensures(*The length of the input array `temperatures` is greater than or equal to - Each element in the input array `temperatures` is an integer.*);
//@ ensures(*Each element in the input array `temperatures` is between 30 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `answer` is not null.*);
//@ ensures(*The length of the output array `answer` is the same as the length of the input array `temperatures`.*);
//@ ensures(*Each element in the output array `answer` is an integer.*);
//@ ensures(*Each element in the output array `answer` is greater than or equal to - The value of `answer[i]` represents the number of days you have to wait after the `i`th day to get a warmer temperature.*);
//@ ensures(*If there is no future day for which this is possible, `answer[i]` is equal to 0.*);
    public int[] dailyTemperatures(int[] temperatures) {
        int[] sol = new int[temperatures.length];
        sol[temperatures.length - 1] = 0;
        for (int i = sol.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (j <= sol.length) {
                if (temperatures[i] < temperatures[j]) {
                    sol[i] = j - i;
                    break;
                } else {
                    if (sol[j] == 0) {
                        break;
                    }
                    j = j + sol[j];
                }
            }
        }
        return sol;
    }
}