package g1301_1400.s1399_count_largest_group;

// #Easy #Hash_Table #Math #2022_03_17_Time_3_ms_(98.21%)_Space_40.9_MB_(60.71%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is an integer.*);
//@ ensures(*`n` is greater than or equal to 1 and less than or equal to 10^4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of groups that have the largest size.*);
//@ ensures(*The groups are formed based on the sum of digits of numbers from 1 to `n`.*);
//@ ensures(*Each group contains numbers with the same sum of digits.*);
//@ ensures(*The method correctly counts the number of groups with the largest size.*);
    public int countLargestGroup(int n) {
        int largest = 0;
        int[] map = new int[37];
        int sumOfDigit = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 10 == 0) {
                // reset and start a new sum
                sumOfDigit = getSumOfDigits(i);
            } else {
                sumOfDigit++;
            }
            int val = ++map[sumOfDigit];
            largest = val > largest ? val : largest;
        }
        return countLargestGroup(largest, map);
    }

    private int countLargestGroup(int largest, int[] arr) {
        int count = 0;
        for (int val : arr) {
            if (val == largest) {
                count++;
            }
        }
        return count;
    }

    private int getSumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}