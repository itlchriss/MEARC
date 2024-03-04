package g2001_2100.s2079_watering_plants;

// #Medium #Array #2022_05_29_Time_0_ms_(100.00%)_Space_43.3_MB_(26.31%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `plants` is not null.*);
//@ ensures(*The input array `plants` has at least one element.*);
//@ ensures(*The input array `plants` contains only positive integers.*);
//@ ensures(*The input integer `capacity` is greater than or equal to the maximum value in the `plants` array.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the number of steps needed to water all the plants.*);
//@ ensures(*The output is greater than or equal to zero.*);
//@ ensures(*The output is the sum of the number of steps taken to water each plant and the number of steps taken to refill the watering can.*);
//@ ensures(*The output is the minimum number of steps needed to water all the plants.*);
    public int wateringPlants(int[] plants, int capacity) {
        int initial = capacity;
        int ans = 0;
        for (int i = 0; i < plants.length; i++) {
            if (plants[i] <= capacity) {
                ++ans;
                capacity -= plants[i];
            } else {
                ans += i;
                capacity = initial;
                ans += i + 1;
                capacity -= plants[i];
            }
        }
        return ans;
    }
}