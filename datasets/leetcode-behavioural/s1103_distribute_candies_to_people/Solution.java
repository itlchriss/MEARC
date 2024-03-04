package g1101_1200.s1103_distribute_candies_to_people;

// #Easy #Math #Simulation #2023_06_01_Time_1_ms_(97.48%)_Space_40.8_MB_(8.63%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `candies` is a positive integer.*);
//@ ensures(*The input `num_people` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an array of length `num_people`.*);
//@ ensures(*The sum of all elements in the output array is equal to `candies`.*);
//@ ensures(*The first element in the output array is the sum of all candies given on the first turn.*);
//@ ensures(*The second element in the output array is the sum of all candies given on the second turn.*);
//@ ensures(*The third element in the output array is the sum of all candies given on the third turn.*);
//@ ensures(*The last element in the output array is the sum of all remaining candies.*);
    public int[] distributeCandies(int candies, int numPeople) {
        int[] candiesDistribution = new int[numPeople];
        int count = 1;
        while (candies > 0) {
            for (int i = 0; i < numPeople; i++) {
                if (candies >= count) {
                    candiesDistribution[i] += count;
                    candies -= count;
                    count++;
                } else if (candies > 0) {
                    candiesDistribution[i] += candies;
                    candies -= candies;
                }
                if (candies == 0) {
                    return candiesDistribution;
                }
            }
        }
        return candiesDistribution;
    }
}