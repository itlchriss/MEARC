package g2101_2200.s2126_destroying_asteroids;

// #Medium #Array #Sorting #Greedy #2022_06_08_Time_6_ms_(99.27%)_Space_54.1_MB_(97.81%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `mass` must be a positive integer greater than or equal to - The input `asteroids` must be a non-empty array of positive integers.*);
//@ ensures(*The elements in the `asteroids` array must be positive integers greater than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return a boolean value indicating whether all asteroids can be destroyed.*);
//@ ensures(*If all asteroids can be destroyed, the method should return `true`.*);
//@ ensures(*If not all asteroids can be destroyed, the method should return `false`.*);
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        return helper(mass, 0, asteroids);
    }

    private boolean helper(long mass, int startIndex, int[] asteroids) {
        int smallOrEqualIndex = partition(mass, startIndex, asteroids);
        if (smallOrEqualIndex < startIndex) {
            return false;
        }
        if (smallOrEqualIndex >= asteroids.length - 1) {
            return true;
        }
        for (int i = startIndex; i <= smallOrEqualIndex; ++i) {
            mass += asteroids[i];
        }
        return helper(mass, ++smallOrEqualIndex, asteroids);
    }

    private int partition(long mass, int startIndex, int[] asteroids) {
        int length = asteroids.length;
        int smallOrEqualIndex = startIndex - 1;
        for (int i = startIndex; i < length; ++i) {
            if (asteroids[i] <= mass) {
                smallOrEqualIndex++;
                swap(asteroids, i, smallOrEqualIndex);
            }
        }
        return smallOrEqualIndex;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}