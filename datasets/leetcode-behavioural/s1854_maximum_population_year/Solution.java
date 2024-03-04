package g1801_1900.s1854_maximum_population_year;

// #Easy #Array #Counting #2022_05_10_Time_0_ms_(100.00%)_Space_42.7_MB_(24.42%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `logs` is not null.*);
//@ ensures(*The length of `logs` is greater than or equal to 1 and less than or equal to 100.*);
//@ ensures(*Each element in `logs` is an array of length 2.*);
//@ ensures(*The birth year `birth_i` is greater than or equal to 1950 and less than the death year `death_i`.*);
//@ ensures(*The death year `death_i` is greater than the birth year `birth_i` and less than or equal to 2050.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the earliest year with the maximum population.*);
//@ ensures(*The maximum population is the number of people alive during the earliest year.*);
//@ ensures(*The earliest year is within the inclusive range of birth and death years for each person in `logs`.*);
//@ ensures(*The person is not counted in the year that they die.*);
    public int maximumPopulation(int[][] logs) {
        int[] arr = new int[101];
        for (int[] log : logs) {
            arr[log[0] - 1950]++;
            arr[log[1] - 1950]--;
        }
        for (int i = 1; i < 101; i++) {
            arr[i] += arr[i - 1];
        }
        int maxyear = 1950;
        int max = 0;
        for (int i = 0; i < 101; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxyear = i + 1950;
            }
        }
        return maxyear;
    }
}