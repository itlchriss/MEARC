package g1201_1300.s1276_number_of_burgers_with_no_waste_of_ingredients;

// #Medium #Math #2022_03_12_Time_2_ms_(87.88%)_Space_42.9_MB_(41.21%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The values of `tomatoSlices` and `cheeseSlices` must be non-negative integers.*);
//@ ensures(*The values of `tomatoSlices` and `cheeseSlices` must not exceed 10^7.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*If it is possible to make the remaining `tomatoSlices` and `cheeseSlices` equal to 0, the method should return a list `[total_jumbo, total_small]` where `total_jumbo` represents the number of jumbo burgers and `total_small` represents the number of small burgers.*);
//@ ensures(*If it is not possible to make the remaining `tomatoSlices` and `cheeseSlices` equal to 0, the method should return an empty list `[]`.*);
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> numbers = new ArrayList<>();
        int numberOfCheese = cheeseSlices * 4;
        int remaining = numberOfCheese - tomatoSlices;
        if (remaining >= 0 && remaining % 2 != 1) {
            int numberOfSmall = remaining / 2;
            int numberOfLarge = cheeseSlices - numberOfSmall;
            if (numberOfLarge < 0) {
                return numbers;
            }
            numbers.add(numberOfLarge);
            numbers.add(numberOfSmall);
        }
        return numbers;
    }
}