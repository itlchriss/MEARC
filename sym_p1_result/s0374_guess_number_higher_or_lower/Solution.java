package g0301_0400.s0374_guess_number_higher_or_lower;

// #Easy #Binary_Search #Interactive #Binary_Search_I_Day_1
// #2022_07_12_Time_0_ms_(100.00%)_Space_40.4_MB_(74.20%)

/*
 * Forward declaration of guess API.
 *
 * @param num your guess
 * @return -1 if num is lower than the guess number 1 if num is higher than the guess number
 *     otherwise return 0 int guess(int num);
 */
public class Solution {
//@ requires(*The integer parameter `n` is greater than or equal to 1 and is less than or equal to 2^31 - 1.*);
//@ requires(*The integer parameter `pick` is greater than or equal to 1 and is less than or equal to the integer parameter `n`.*);
//@ requires(*If the `guess(int num)` API returns -1, the number picked is lower than the guessed number.*);
//@ requires(*If the `guess(int num)` API returns 1, the number picked is higher than the guessed number.*);
//@ requires(*If the `guess(int num)` API returns 0, the number picked is equal to the guessed number.*);
//@ ensures(*The integer result is equal to the number that is picked.*);
//@ ensures(*The result is determined by calling the `guess(int num)` API with the guessed number as the parameter.*);
    public int guessNumber(int n) {
        int start = 0;
        int end = n;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == -1) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    // Assume we pick 7
    private int guess(int num) {
        return Integer.compare(7, num);
    }
}